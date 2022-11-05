package prr.app.terminal;

import prr.core.InteractiveCommunication;
import prr.core.Network;
import prr.core.TariffPlan;
import prr.core.Terminal;
import prr.core.TerminalMode;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for ending communication.
 */
class DoEndInteractiveCommunication extends TerminalCommand {

  DoEndInteractiveCommunication(Network context, Terminal terminal) {
    super(Label.END_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canEndCurrentCommunication());
    addIntegerField("duration", Message.duration());
  }

  @Override
  protected final void execute() throws CommandException {
    // FIXME implement command
    int duration = integerField("duration");
    InteractiveCommunication ongoingComm = _receiver.getOngoingCommunication();
    if (ongoingComm != null) {
      ongoingComm.setUnits(duration);
      Terminal terminalDestiny = ongoingComm.getDestination();
      _receiver.setOngoingCommunication(null);
      terminalDestiny.setOngoingCommunication(null);
      ongoingComm.setOngoing(false);
      TerminalMode terminalmode = _receiver.getPreviousMode();
      TerminalMode terminalmodeDestiny = terminalDestiny.getPreviousMode();
      _receiver.setMode(terminalmode);
      terminalDestiny.setMode(terminalmodeDestiny);
      TariffPlan plan = _network.tariffPlan(ongoingComm, _receiver.getClient());
      int cost;
      if (ongoingComm.getType().equals("VOICE")) {
        cost = (int) Math.round(plan.getCostCall());
      } else {
        cost = (int) Math.round(plan.getCostVideo());
      }
      _display.popup(Message.communicationCost(cost));
      _receiver.getClient().updateClientLevel();
    }
    return;

  }
}
