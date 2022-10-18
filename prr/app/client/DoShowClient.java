package prr.app.client;

import prr.core.Client;
import prr.core.Network;

import java.util.List;

import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.Notifications;
//FIXME add more imports if needed

/**
 * Show specific client: also show previous notifications.
 */
class DoShowClient extends Command<Network> {

  DoShowClient(Network receiver) {
    super(Label.SHOW_CLIENT, receiver);
    //FIXME add command fields
    addStringField("key", Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    String key = stringField("key");
    Client client = _receiver.findClient(key);
    _display.addLine(client.showClient());
    for(Notifications notification : client.getNotifications()){
      _display.addLine(String.format("%d|%d",notification.getType().toString(),notification.getTeminalOrigin()));
  }
  client.clearNotifications();;
  _display.display();
  }
}
