package prr.app.terminal;

import prr.core.Communication;
import prr.core.Network;
import prr.core.TariffPlan;
import prr.core.Terminal;
import prr.core.TextCommunication;

import javax.management.Notification;

import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.forms.Form;
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
    //FIXME implement command
    String receiverId = stringField("receiverId");
    String message = stringField("receiverId");
    Terminal terminal = _network.findTerminalNull(receiverId);
    Communication communication = new TextCommunication(_receiver, terminal, message);
    communication.setUnits(message.length());
    TariffPlan plan = null;
    Notification notification = null;

    if(terminal.equals(null)){
      throw new UnknownTerminalKeyException(receiverId);
    }

    if(_receiver.canStartCommunication() && terminal.canStartCommunication()){
      plan = _network.tariffPlan(message, communication, _receiver.getClient());
      double cost = plan.getCostText();
      communication.setCost(cost);
      communication.setId(_network.getCommunicationID());
      _receiver.addCommunicationMade(communication);
      terminal.addCommunicationRecieved(communication);
      _network.addComunication(communication);

    }

    else if(terminal.getClient().getReceiveNotifications()){
      notification = new Notification(message, notification, 0);

    }
    
  }
} 
