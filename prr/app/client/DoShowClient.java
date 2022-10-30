package prr.app.client;

import prr.core.Client;
import prr.core.Network;

import java.util.ArrayList;
import java.util.List;

import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.Notification;
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
  protected final void execute() throws CommandException,UnknownClientKeyException{
    //FIXME implement command
    String key = stringField("key");
    Client client = _receiver.findClient(key);
    _display.addLine(client.showClient());
    if(client.getNotifications().size()==0){
    List<String> notifications = client.showClientNotifications();
    for(String s: notifications){
      _display.addLine(s);
    }
    _display.display();
    }
  }
}
