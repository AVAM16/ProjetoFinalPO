package prr.app.lookup;

import java.util.List;

import prr.app.exception.UnknownClientKeyException;
import prr.core.Client;
import prr.core.Communication;
import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show communications to a client.
 */
class DoShowCommunicationsToClient extends Command<Network> {

  DoShowCommunicationsToClient(Network receiver) {
    super(Label.SHOW_COMMUNICATIONS_TO_CLIENT, receiver);
    addStringField("key", Message.clientKey());
    // FIXME add command fields
  }

  @Override
  protected final void execute() throws CommandException {
    // FIXME implement command
    String key = stringField("key");
    Client client = _receiver.getClient(key);
    if (client == null) {
      throw new UnknownClientKeyException(key);
    }
    List<Communication> comms = client.getComsReceived();
    for (Communication c : comms) {
      _display.addLine(c.showCommunication());
    }
    _display.display();
    // ArrayList<Communication> comms = _re
  }

}
