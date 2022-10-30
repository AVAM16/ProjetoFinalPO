package prr.app.terminal;

import java.util.List;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Remove friend.
 */
class DoRemoveFriend extends TerminalCommand {

  DoRemoveFriend(Network context, Terminal terminal) {
    super(Label.REMOVE_FRIEND, context, terminal);
    //FIXME add command fields
    addStringField("id", Message.terminalKey());
    addStringField("friend", Message.terminalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    String id = stringField("id");
    String friendId = stringField("friend");
    Terminal terminal = _network.findTerminalNull(id);
    Terminal friend = _network.findTerminalNull(friendId);
    List<Terminal> friends = terminal.getFriendslist();
    if(friends.contains(friend)){
      _receiver.removeFriend(friend);
    }
  }
}
