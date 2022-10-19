package prr.app.terminals;

import prr.core.Network;
import prr.core.Terminal;
import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add mode import if needed

/**
 * Open a specific terminal's menu.
 */
class DoOpenMenuTerminalConsole extends Command<Network> {

  DoOpenMenuTerminalConsole(Network receiver) {
    super(Label.OPEN_MENU_TERMINAL, receiver);
    //FIXME add command fields
    addStringField("id", Message.terminalKey());
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    // create an instance of prr.app.terminal.Menu with the
    // selected Terminal and open it
    String id = stringField("id");
    Terminal terminal = _receiver.findTerminal(id);
    (new prr.app.terminal.Menu(_receiver, terminal)).open();
  }
}
