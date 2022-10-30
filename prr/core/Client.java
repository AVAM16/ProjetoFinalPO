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
  
  public int getDeptsSum(){
    return getValue(_communicationsDept);
  }

  public int getTerminals() {
    return _terminals.size();
  }

  public String getNotificationsString() {
    if (_receiveNotifications) {
      return "YES";
    } else {
      return "NO";
    }
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

  public List<Notification> getNotifications() {
    return _notifications;
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

  //is

  public boolean isDebtor(){
    return _communicationsDept.size()>0;
  }

}
