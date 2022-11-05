package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
// Add more imports if needed

/**
 * Perform payment.
 */
class DoPerformPayment extends TerminalCommand {

  DoPerformPayment(Network context, Terminal terminal) {
    super(Label.PERFORM_PAYMENT, context, terminal);
    addIntegerField("id", Message.commKey());
    // FIXME add command fields
  }

  @Override
  protected final void execute() throws CommandException {
    // FIXME implement command
    int id = integerField("id");
    if (_receiver.getClient().findCommunicationAndRemoveOrNull(id) == null) {
      _display.popup(Message.invalidCommunication());
      return;
    }
    _receiver.findCommunicationAndRemoveOrNull(id);
    _receiver.getClient().updateClientLevel();
  }
}
