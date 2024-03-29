package prr.app.terminal;

import prr.core.Client;
import prr.core.CommunicationFactory;
import prr.core.InteractiveCommunication;
import prr.core.Network;
import prr.core.Notification;
import prr.core.Terminal;
import prr.core.TerminalMode;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for starting communication.
 */
class DoStartInteractiveCommunication extends TerminalCommand {

  DoStartInteractiveCommunication(Network context, Terminal terminal) {
    super(Label.START_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
    addStringField("key", Message.terminalKey());
    addOptionField("type", Message.commType(), "VIDEO", "VOICE");
  }

  @Override
  protected final void execute() throws CommandException {
    // FIXME implement command
    String id = stringField("key");
    String type = optionField("type");
    Terminal terminalDestiny = _network.findTerminal(id);
    String idOrigin = _receiver.getID();
    CommunicationFactory factory = new CommunicationFactory();
    InteractiveCommunication interactiveComm = factory.createCommunication(type, _receiver, terminalDestiny);
    if (terminalDestiny.isOff()) {
      _display.popup(Message.destinationIsOff(id));
      Notification notification = _network.createNotification(interactiveComm, _receiver.getClient());
      if (!terminalDestiny.isNotificationDuplicate(notification)) {
        terminalDestiny.addPendingNotification(notification);
      }
      return;
    } else if (terminalDestiny.isBusy()) {
      _display.popup(Message.destinationIsBusy(id));
      Notification notification = _network.createNotification(interactiveComm, _receiver.getClient());
      if (!terminalDestiny.isNotificationDuplicate(notification)) {
        terminalDestiny.addPendingNotification(notification);
      }
      return;
    } else if (terminalDestiny.isSilent()) {
      Notification notification = _network.createNotification(interactiveComm, _receiver.getClient());
      _display.popup(Message.destinationIsSilent(id));
      terminalDestiny.addPendingNotification(notification);
      return;
    } else if (_receiver.getType().equals("BASIC") && type.equals("VIDEO")) {
      _display.popup(Message.unsupportedAtOrigin(idOrigin, type));
      return;
    } else if (terminalDestiny.getType().equals("BASIC") && type.equals("VIDEO")) {
      _display.popup(Message.unsupportedAtDestination(id, type));
      return;
    } else {
      _receiver.setOngoingCommunication(interactiveComm);
      terminalDestiny.setOngoingCommunication(interactiveComm);
      interactiveComm.setOngoing(true);
      interactiveComm.setId(_network.getCommunicationID());
      _network.addComunication(interactiveComm);
      _receiver.addCommunicationMade(interactiveComm);
      terminalDestiny.addCommunicationRecieved(interactiveComm);
      Client cMade = _receiver.getClient();
      Client cReceived = terminalDestiny.getClient();
      cMade.addCommunicationsMade(interactiveComm);
      cReceived.addCommunicationsRecived(interactiveComm);
      TerminalMode terminalmode = _receiver.getModeNoString();
      TerminalMode terminalModeDestiny = terminalDestiny.getModeNoString();
      _receiver.setPreviousMode(terminalmode);
      terminalDestiny.setPreviousMode(terminalModeDestiny);
      _receiver.turnBusy(); // _receiver.isBusy(); acho que este isBusy e um erro
      terminalDestiny.turnBusy();
    }
  }
}
