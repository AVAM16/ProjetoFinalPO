package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show balance.
 */
class DoShowTerminalBalance extends TerminalCommand {

  DoShowTerminalBalance(Network context, Terminal terminal) {
    super(Label.SHOW_BALANCE, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  
    _display.popup(Message.terminalPaymentsAndDebts(_receiver.getID(),(long)_receiver.getValue(_receiver.getPayments()),
    (long)_receiver.getValue(_receiver.getDepts())));
   
  }
}
