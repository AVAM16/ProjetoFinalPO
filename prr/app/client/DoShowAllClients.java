package prr.app.client;

import prr.core.Network;

import java.util.List;

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
    //FIXME implement command
    List<Client> clients = _receiver.getClients();
    for(Client c: clients){
      _display.addLine(c.showClient());
    }
    _display.display();
  }
}
