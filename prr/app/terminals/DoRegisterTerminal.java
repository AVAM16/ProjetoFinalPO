package prr.app.terminals;

import prr.core.Client;
import prr.core.Terminal;
import prr.core.Network;
import prr.app.exception.DuplicateTerminalKeyException;
import prr.app.exception.InvalidTerminalKeyException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Register terminal.
 */
class DoRegisterTerminal extends Command<Network> {

  DoRegisterTerminal(Network receiver) {
    super(Label.REGISTER_TERMINAL, receiver);
    //FIXME add command fields
    addStringField("id", Message.terminalKey());
    addOptionField("type", Message.terminalType(), "BASIC", "FANCY");
    addStringField("clientID", Message.clientKey());
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    String type = optionField("type");
    String id = stringField("id");
    String clientID = stringField("clientID");
    //Client client = _receiver.findClient(clientID);
    //Terminal terminal = _receiver.findTerminalB(id);
    //terminal.setClient(client);
    _receiver.registerTerminal(id, type,clientID);
  }
}
