package prr.app.lookup;

import java.util.List;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show terminals with positive balance.
 */
class DoShowTerminalsWithPositiveBalance extends Command<Network> {

  DoShowTerminalsWithPositiveBalance(Network receiver) {
    super(Label.SHOW_TERMINALS_WITH_POSITIVE_BALANCE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    List<Terminal> terminals = _receiver.getTerminals();
    for (Terminal t : terminals){
      if (t.getBalance()>0){
        _display.addLine(t.showTerminal());
      }
    }
    _display.display();
  }
  
}
