package prr.app.terminals;

import java.util.List;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all terminals.
 */
class DoShowAllTerminals extends Command<Network> {

  DoShowAllTerminals(Network receiver) {
    super(Label.SHOW_ALL_TERMINALS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    List<Terminal> terminals = _receiver.getTerminals();
    for(Terminal t: terminals){
      _display.addLine(t.showTerminal());
    }
    _display.display();
  }
}
