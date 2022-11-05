package prr.app.client;

import prr.core.Client;
import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Disable client notifications.
 */
class DoDisableClientNotifications extends Command<Network> {

  DoDisableClientNotifications(Network receiver) {
    super(Label.DISABLE_CLIENT_NOTIFICATIONS, receiver);
    // FIXME add command fields
    addStringField("id", Message.key());
  }

  @Override
  protected final void execute() throws CommandException {
    // FIXME implement command
    String id = stringField("id");
    Client client = _receiver.findClient(id);
    if (client.getReceiveNotifications()) {
      client.disableNotifications();
    } else {
      _display.popup(Message.clientNotificationsAlreadyDisabled());
    }
  }
}
