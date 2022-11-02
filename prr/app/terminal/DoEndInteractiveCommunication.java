package prr.app.terminal;

import prr.core.Client;
import prr.core.InteractiveCommunication;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for ending communication.
 */
class DoEndInteractiveCommunication extends TerminalCommand {

  DoEndInteractiveCommunication(Network context, Terminal terminal) {
    super(Label.END_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canEndCurrentCommunication());
    addIntegerField("duration", Message.duration());
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    int duration = integerField("duration");
    InteractiveCommunication ongoingComm = _receiver.getOngoingCommunication();
    if (ongoingComm != null) {
      ongoingComm.setDuration(duration);
      Terminal t = ongoingComm.getDestination();
      _receiver.setOngoingCommunication(null);
      t.setOngoingCommunication(null);
      ongoingComm.setOngoing(false);
      ongoingComm.setId(_network.getCommunicationID());
      _network.addComunication(ongoingComm);
      _receiver.addCommunicationMade(ongoingComm);
      t.addCommunicationRecieved(ongoingComm);
      Client cMade = _receiver.getClient();
      Client cReceived = t.getClient();
      cMade.addCommunicationsMade(ongoingComm);
      cReceived.addCommunicationsRecived(ongoingComm);
    }

  }
}
