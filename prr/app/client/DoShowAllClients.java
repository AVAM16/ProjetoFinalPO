package prr.app.client;

import prr.core.Network;

import prr.core.Client;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all clients.
 */
class DoShowAllClients extends Command<Network> {

  DoShowAllClients(Network receiver) {
    super(Label.SHOW_ALL_CLIENTS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    // FIXME implement command
    for (Client c : _receiver.getClients()) {
      _display.addLine(c.showClient());
    }
    _display.display();
  }
}
