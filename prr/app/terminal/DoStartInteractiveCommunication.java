package prr.app.terminal;

import prr.core.BasicTerminal;
import prr.core.CommunicationType;
import prr.core.InteractiveCommunication;
import prr.core.Network;
import prr.core.Terminal;
import prr.core.VideoCommunication;
import prr.core.VoiceCommunication;

import javax.swing.text.AbstractDocument.Content;

import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.forms.Form;
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
    //FIXME implement command
    String id = stringField("key");
    String type = optionField("type");
    Terminal terminalDestiny = _network.findTerminal(id);
    String idOrigin = _receiver.getID();
    if (terminalDestiny.isOff()) {
      _display.popup(Message.destinationIsOff(id));
    } else if (terminalDestiny.isBusy()) {
      _display.popup(Message.destinationIsBusy(id));
    } else if (terminalDestiny.isSilent()) {
      _display.popup(Message.destinationIsSilent(id));
    } else if (_receiver instanceof BasicTerminal && type.equals("VIDEO")) {
      _display.popup(Message.unsupportedAtOrigin(idOrigin, type));
    } else if (terminalDestiny instanceof BasicTerminal && type.equals("VIDEO")){
      _display.popup(Message.unsupportedAtDestination(id, type));
    } else {
      InteractiveCommunication interactiveComm = null;
      if (type.equals("VOICE")) {
        interactiveComm = new VoiceCommunication(terminalDestiny, terminalDestiny);
      } else {
        interactiveComm = new VideoCommunication(terminalDestiny, terminalDestiny);
      }
      _receiver.setOngoingCommunication(interactiveComm);
      interactiveComm.setOngoing(true);
    }
  }
}
