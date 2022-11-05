package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Turn on the terminal.
 */
class DoTurnOnTerminal extends TerminalCommand {
  Network _network;

  DoTurnOnTerminal(Network context, Terminal terminal) {
    super(Label.POWER_ON, context, terminal);
  }

  @Override
  protected final void execute() throws CommandException {
    // FIXME implement command
    Terminal terminal = _receiver;
    if (terminal.getMode().equals("ON")) {
      _display.popup(Message.alreadyOn());

    } else {
      terminal.turnOn();
    }

  }
}
