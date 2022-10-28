package prr.app.lookup;

import java.util.ArrayList;
import java.util.List;

import prr.core.Client;
import prr.core.Communication;
import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME more imports if needed

/**
 * Command for showing all communications.
 */
class DoShowAllCommunications extends Command<Network> {

  DoShowAllCommunications(Network receiver) {
    super(Label.SHOW_ALL_COMMUNICATIONS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    List<Communication> comms = _receiver.getComms();
    for(Communication c: comms){
      _display.addLine(c.showCommunication()); 
    }
    _display.display();
  }
  
}
