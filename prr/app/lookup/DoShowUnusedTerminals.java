package prr.app.lookup;

import java.util.List;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show unused terminals (without communications).
 */
class DoShowUnusedTerminals extends Command<Network> {

  DoShowUnusedTerminals(Network receiver) {
    super(Label.SHOW_UNUSED_TERMINALS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    List<Terminal> terminals = _receiver.getTerminals();
    for (Terminal t : terminals){
      if (t.isUnused()){
        _display.addLine(t.showTerminal());
      }
    }
    _display.display();
  }
}
