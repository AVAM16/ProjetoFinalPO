package prr.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Client implements Serializable {
  private static final long serialVersionUID = 202208091753L;
  private String _key;
  private String _name;
  private int _nif;
  private ClientLevel _level;
  private boolean _receiveNotifications;
  private List<Communication> _communicationsReceived;
  private List<Communication> _communicationsMade;
  private List<Communication> _communicationsPaid;
  private List<Communication> _communicationsDept;
  private List<Notification> _notifications;
  private TariffPlan _plan;
  private List<Terminal> _terminals;

  public Client(String key, String name, int nif) {
    this._key = key;
    this._name = name;
    this._nif = nif;
    this._level = ClientLevel.NORMAL;
    this._terminals = new ArrayList<>();
    this._notifications = new ArrayList<>();
    this._communicationsDept = new ArrayList<>();
    this._communicationsPaid = new ArrayList<>();
    this._communicationsReceived = new ArrayList<>();
    this._communicationsMade = new ArrayList<>();
    this._receiveNotifications = true;
  }

  // gets

  public String getKey() {
    return this._key;
  }

  public String getName() {
    return this._name;
  }

  public int getNIF() {
    return this._nif;
  }

  public String getClientLevel() {
    return this._level.toString();
  }

  public List<Communication> getComsMade(){
    return _communicationsMade;
  }

  public List<Communication> getComsReceived(){
    return _communicationsReceived;
  }

  
  public int getValue(List<Communication> list) {
    double sum = 0;
    Iterator<Communication> iter = list.iterator();
    while (iter.hasNext()) {
      Communication communication = iter.next();
      sum = sum + communication.getCost();
    }
    return (int) Math.round(sum);
  }

  //este e para ser usado quando nao queres arredondamentos
  public double getRealValue(List<Communication> list) {
    double sum = 0;
    Iterator<Communication> iter = list.iterator();
    while (iter.hasNext()) {
      Communication communication = iter.next();
      sum = sum + communication.getCost();
    }
    return sum;
  }

  //isto e o saldo
  public double getBalance(){
    return getRealValue(_communicationsPaid) - getRealValue(_communicationsDept);
  }
  
  public int getDeptsSum(){
    return getValue(_communicationsDept);
  }

  public int getPaymentsSum(){
    return getValue(_communicationsPaid);
  }

  public int getTerminals() {
    return _terminals.size();
  }

  public Boolean getReceiveNotifications(){
    return _receiveNotifications;
  }
  
  public String getNotificationsString() {
    if (_receiveNotifications) {
      return "YES";
    } else {
      return "NO";
    }
  }
  
  public List<Notification> getNotifications() {
    return _notifications;
  }
  // CLIENT|key|name|taxId|type|notifications|terminals|payments|debts
  // tipo-de-notificação|idTerminal

  // NOTA IMPORTANTE : para a DoShowAllClients tens de usar a showClient e a
  // showClientNotifications para todos os clientes.

  public String showClient() {
    return String.format("CLIENT|%s|%s|%s|%s|%s|%d|%d|%d", _key, _name, _nif, _level, getNotificationsString(),
        getTerminals(), getValue(_communicationsPaid), getValue(_communicationsDept));

  }

  public List<String> showClientNotifications() {
    LinkedList<String> notifications = new LinkedList<>();
    for (Notification notification : _notifications) {
      notifications.add(String.format("%s|%s", notification.getType().toString(), notification.getTeminalOrigin()));
    }
    _notifications.clear();
    return notifications;
  }

  public void disableNotifications() {
    _receiveNotifications = false;
  }

  public void enableNotifications() {
    _receiveNotifications = true;
  }

private void upgradeClient(){
  if(_level.equals(ClientLevel.NORMAL)){
    _level = ClientLevel.GOLD;
  }

  if(_level.equals(ClientLevel.GOLD)){
    _level = ClientLevel.PLATINUM;
  }
}

private void downgradeClient(){
  if(_level.equals(ClientLevel.GOLD)){
    _level = ClientLevel.NORMAL;
  }

  if(_level.equals(ClientLevel.PLATINUM)){
    _level = ClientLevel.GOLD;
  }
}

private void resetClient(){
    _level=ClientLevel.NORMAL;
}

private boolean fiveVideoCalls(){
  int size = _communicationsMade.size();
  int i;
  for(i = 1; i <= 5;i++){
  if(!_communicationsMade.get(size-i).getType().equals("VIDEO")){
    return false;
    }
  }
  return true;
}

private boolean twoText(){
  int size = _communicationsMade.size();
  int i;
  for(i = 1; i <= 2;i++){
  if(!_communicationsMade.get(size-i).getType().equals("TEXT")){
    return false;
    }
  }
  return true;
}

//tierCliente: 0 -> normal | 1 -> gold | 2 -> premium
public void updateClientLevel(){
    if(getBalance()<0){
      resetClient();
    }
    else if(_level.equals(ClientLevel.NORMAL) && getBalance()>=500){ //normal -> gold
      upgradeClient();    
    }

    else if(_level.equals(ClientLevel.GOLD) && fiveVideoCalls() && getBalance()>=0){ //gold -> premium
        upgradeClient(); 
    }

    else if(_level.equals(ClientLevel.PLATINUM) && twoText() && getBalance()>=0){
      downgradeClient();
    }
 
}


  // adds
  public void addTerminal(Terminal terminal) {
    this._terminals.add(terminal);
  }

  public void addNotification(Communication communication, Client client, NotificationType type) {
    Notification notification = new Notification(communication, client, type);
    _notifications.add(notification);
  }

  public void clearNotifications() {
    _notifications.clear();
  }

  public void addCommunicationsDept(Communication communication){
    _communicationsDept.add(communication);
  }

  public void addCommunicationsPaid(Communication communication){
    _communicationsPaid.add(communication);
  }

  public void addCommunicationsMade(Communication communication){
    _communicationsMade.add(communication);
  }

  public void addCommunicationsRecived(Communication communication){
    _communicationsReceived.add(communication);
  }

  //is

  public boolean isDebtor(){
    return _communicationsDept.size()>0;
  }

}
