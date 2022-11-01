package prr.app.main;

import java.util.List;

import prr.core.Client;
import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show global balance.
 */
class DoShowGlobalBalance extends Command<Network> {

  DoShowGlobalBalance(Network receiver) {
    super(Label.SHOW_GLOBAL_BALANCE, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    int payments = 0;
    int debts = 0;
    List<Client> clients = _receiver.getClients();
    for (Client c : clients) {
      payments += c.getPaymentsSum();
      debts += c.getDeptsSum();
    }
    _display.popup(Message.globalPaymentsAndDebts(payments, debts));
  }
}
