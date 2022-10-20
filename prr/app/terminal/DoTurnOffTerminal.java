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
    addStringField("id", Message.terminalKey());
    _network=context;
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    String id = stringField("id");
    Terminal terminal = _network.findTerminal(id);
    if(terminal.getMode().equals("OFF")){
      _display.popup(Message.alreadyOff());
    }
    else{
      terminal.turnOff();
    }

  }
  
}
