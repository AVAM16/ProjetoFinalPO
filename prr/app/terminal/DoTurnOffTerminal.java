package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Turn off the terminal.
 */
class DoTurnOffTerminal extends TerminalCommand {
  Network _network;

  DoTurnOffTerminal(Network context, Terminal terminal) {
    super(Label.POWER_OFF, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    Terminal terminal = _receiver;
    if(terminal.getMode().equals("OFF")){
      _display.popup(Message.alreadyOff());
    }
    else{
      terminal.turnOff();
    }

  }
  
}
