package prr.app.lookup;

import java.util.List;

import prr.core.Client;
import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME more imports if needed

/**
 * Show clients with negative balance.
 */
class DoShowClientsWithDebts extends Command<Network> {

  DoShowClientsWithDebts(Network receiver) {
    super(Label.SHOW_CLIENTS_WITH_DEBTS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    // FIXME implement command
    List<Client> clients = _receiver.getClients();
    for (Client c : clients) {
      if (c.isDebtor()) {
        _display.addLine(c.showClient());

      }
    }
    _display.display();
  }

}
