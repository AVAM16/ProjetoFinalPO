package prr.app.terminal;

import prr.app.exception.UnknownTerminalKeyException;
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
    addStringField("id", Message.terminalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    String id = stringField("id");
    Terminal terminal = _receiver;
    if(terminal==null){
      throw new UnknownTerminalKeyException(id);
    }
    _display.popup(Message.terminalPaymentsAndDebts(id,terminal.getValue(terminal.getPayments()),
    terminal.getValue(terminal.getDepts())));
    _display.display();
  }
}
