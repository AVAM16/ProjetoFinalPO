package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Silence the terminal.
 */
class DoSilenceTerminal extends TerminalCommand {

  DoSilenceTerminal(Network context, Terminal terminal) {
    super(Label.MUTE_TERMINAL, context, terminal);
    addStringField("id", Message.terminalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    String id = stringField("id");
    Terminal terminal = _network.findTerminal(id);
    if(terminal.getModeDisplay().equals("SILENCE")){
      _display.popup(Message.alreadySilent());
      _display.display();
    }
    else{
      terminal.turnSilence();
    }

  }

}
