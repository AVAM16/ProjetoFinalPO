package prr.app.client;

import prr.core.Client;
import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show the payments and debts of a client.
 */
class DoShowClientPaymentsAndDebts extends Command<Network> {

  DoShowClientPaymentsAndDebts(Network receiver) {
    super(Label.SHOW_CLIENT_BALANCE, receiver);
    // FIXME add command fields
    addStringField("id", Message.key());
  }

  @Override
  protected final void execute() throws CommandException {
    // FIXME implement command
    String id = stringField("id");
    Client client = _receiver.findClient(id);
    int payments = client.getPaymentsSum();
    int debts = client.getDeptsSum();
    _display.popup(Message.clientPaymentsAndDebts(id, payments, debts));
  }
}
