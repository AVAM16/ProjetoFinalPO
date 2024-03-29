package prr.app.terminal;

import prr.core.Communication;
import prr.core.Network;
import prr.core.Notification;
import prr.core.TariffPlan;
import prr.core.Terminal;
import prr.core.TextCommunication;

import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for sending a text communication.
 */
class DoSendTextCommunication extends TerminalCommand {

  DoSendTextCommunication(Network context, Terminal terminal) {
    super(Label.SEND_TEXT_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
    addStringField("receiverId", Message.terminalKey());
    addStringField("message", Message.textMessage());
  }

  @Override
  protected final void execute() throws CommandException {
    // FIXME implement command
    String id = stringField("receiverId");
    String message = stringField("message");
    Terminal terminal = _network.findTerminalNull(id);
    Communication communication = new TextCommunication(_receiver, terminal, message);
    // _display.popup(communication);

    if (terminal == null) {
      throw new UnknownTerminalKeyException(id);
    }

    if (!terminal.getMode().equals("OFF")) {
      TariffPlan plan = _network.tariffPlan(communication, _receiver.getClient());
      plan.getCostText();
      communication.setId(_network.getCommunicationID());
      _receiver.addCommunicationMade(communication);
      terminal.addCommunicationRecieved(communication);
      _network.addComunication(communication);
      _receiver.getClient().addCommunicationsMade(communication);
      _receiver.getClient().updateClientLevel();
    }

    if (terminal.getMode().equals("OFF")) {
      _display.popup(Message.destinationIsOff(id));

      if (_receiver.getClient().getReceiveNotifications()) {
        Notification notification = _network.createNotification(communication, _receiver.getClient());
        if (!terminal.isNotificationDuplicate(notification)) {
          terminal.addPendingNotification(notification);
        }
      }
    }

  }
}
