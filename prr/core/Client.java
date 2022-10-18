package prr.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import prr.core.ClientLevel;
import prr.core.TariffPlan;
import prr.core.Terminal;

public class Client {
  private String _key;
  private String _name;
  private int _nif;
  private ClientLevel _level;
  private boolean _receiveNotifications;
  private List<Communication> _communicationsReceived;
  private List<Communication> _communicationsMade;
  private List<Communication> _communicationsPaid;
  private List<Communication> _communicationsDept;
  private List<Notifications> _notifications;
  private TariffPlan _plan;
  private List<Terminal> _terminals;

  public Client(String key, String name, int nif){
    this._key=key;
    this._name=name;
    this._nif=nif;
    this._level = ClientLevel.NORMAL;
    this._terminals= new LinkedList<>();
    this._communicationsDept = new LinkedList<>();
    this._communicationsPaid = new LinkedList<>();
    this._communicationsReceived = new LinkedList<>();
    this._communicationsMade = new LinkedList<>();
  }

    //gets

  public String getKey(){
    return this._key;
  }

  public String getName(){
    return this._name;
  }

  public int getNIF(){
    return this._nif;
  }

  public ClientLevel getClientLevel(){
    return this._level;
  }

  public double getDepts(){
    double sum=0;
    Iterator<Communication> iter = _communicationsDept.iterator();
    while(iter.hasNext()){
      Communication communication = iter.next();
      sum = sum + communication.getCost();
    }
    return sum;
  }

  public double getPayments(){
    double sum=0;
    Iterator<Communication> iter = _communicationsPaid.iterator();
    while(iter.hasNext()){
      Communication communication = iter.next();
      sum = sum + communication.getCost();
    }
    return sum;
  }

  public int getTerminals(){
    return _terminals.size();
  }

  public String getNotificationsString(){
    if(_receiveNotifications==true){
      return "YES";
    }
    else{
      return "NO";
    }
  }

  public List<Notifications> getNotifications(){
    return _notifications;
  }

  //adds
  public void addTerminal(Terminal terminal){
    this._terminals.add(terminal);
  }

  public void addNotification(Communication communication,Client client,NotificationType type){
    Notifications notification = new Notifications(communication, client, type);
    _notifications.add(notification);
  }

  public void clearNotifications(){
    _notifications.clear();
  }

  //CLIENT|key|name|taxId|type|notifications|terminals|payments|debts
  // tipo-de-notificação|idTerminal
  public String showClient(){
    return(String.format("CLIENT|%d|%d|%d|%d|%d|%d|%d|%d",_key,_name,_nif,_level,getNotificationsString(),
    getTerminals(),getPayments(),getDepts()));
  }
    
}
