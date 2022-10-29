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
    addStringField("id", Message.terminalKey());
    _network=context;
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    String id = stringField("id");
    Terminal terminal = _network.findTerminal(id);
    if(terminal.getModeDisplay().equals("ON")){
      _display.popup(Message.alreadyOn());
      _display.display();
    }
    else{
      terminal.turnOn();
    }

  }
}
