package prr.app.terminals;

import prr.core.Network;
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
    addStringField("clientId", Message.clientKey());
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    String type = optionField("type");
    String id = stringField("id");
    String clientId = stringField("clientId");
    _receiver.registerTerminal(type, id, clientId);
    
  }
}
