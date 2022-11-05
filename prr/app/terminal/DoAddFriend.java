package prr.app.terminal;

import java.util.List;
import prr.app.exception.UnknownTerminalKeyException;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a friend.
 */
class DoAddFriend extends TerminalCommand {

  DoAddFriend(Network context, Terminal terminal) {
    super(Label.ADD_FRIEND, context, terminal);
    // FIXME add command fields
    addStringField("friend", Message.terminalKey());
  }

  @Override
  protected final void execute() throws CommandException {
    // FIXME implement command
    String friendId = stringField("friend");
    Terminal friend = _network.findTerminalNull(friendId);
    boolean alreadyFriend = false;

    if (friend == null) {
      throw new UnknownTerminalKeyException(friendId);
    }

    List<Terminal> friends = _receiver.getFriendslist();
    for (Terminal t : friends) {
      if (t.getID().equals(friendId)) {
        alreadyFriend = true;
        break;
      }
    }

    if (friend != null && !alreadyFriend && !_receiver.equals(friend)) {
      _receiver.addFriend(friend);
    }

  }
}