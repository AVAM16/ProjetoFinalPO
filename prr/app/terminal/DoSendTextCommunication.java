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
    String id = stringField("receiverId");
    String message = stringField("receiverId");
    Terminal terminal = _network.findTerminalNull(id);
    Communication communication = new TextCommunication(_receiver, terminal, message);
    //communication.setUnits(message.length());
    
    
    if(terminal==null){
      throw new UnknownTerminalKeyException(id);
    }
    //os terminais quando sao amigos existe um desconto de 50% esueci-me disso
    if(_receiver.canStartCommunication() && !terminal.getMode().equals("OFF")){
      TariffPlan plan = _network.tariffPlan(message, communication, _receiver.getClient());
      //plan = _network.tariffPlan(message, communication, _receiver.getClient());
      plan.getCostText();
      //double cost = plan.getCostText();
      //communication.setCost(cost);
      communication.setId(_network.getCommunicationID());
      _receiver.addCommunicationMade(communication);
      terminal.addCommunicationRecieved(communication);
      _network.addComunication(communication);
      _receiver.getClient().addCommunicationsMade(communication);
      _receiver.getClient().updateClientLevel();
    }
    
    if(terminal.getMode().equals("OFF")){
      _display.popup(Message.destinationIsOff(id));
    }
    _display.display();

    if(_receiver.getClient().getReceiveNotifications()){
    prr.core.Notification notification = _network.createNotification(communication, terminal.getClient(), terminal);
    _receiver.addPendingNotification(notification);
    }
    
    
  }
} 
