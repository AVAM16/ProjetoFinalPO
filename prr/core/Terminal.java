package prr.core;

import java.io.Serializable;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable /* FIXME maybe addd more interfaces */ {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;
  // FIXME define attributes
  private String _id;
  private Client _client;
  private Set<Terminal> _friends;
  private List<Communication> _payments;
  private List<Communication> _depts;
  private List<Communication> _communicationsReceived;
  private List<Communication> _communicationsMade;
  private List<Notification> _pendingNotifications; // isto depois e adicionado as nots do cliente
  private TerminalMode _mode;
  private TerminalMode _previousMode;
  private InteractiveCommunication _ongoingCommunication;

  // FIXME define contructor(s)
  public Terminal(String id) {
    this._id = id;
    this._client = null;
    this._payments = new ArrayList<>();
    this._depts = new ArrayList<>();
    this._communicationsReceived = new ArrayList<>();
    this._communicationsMade = new ArrayList<>();
    this._friends = new TreeSet<>(new TerminalComparator());
    this._mode = TerminalMode.ON;
    this._pendingNotifications = new LinkedList<>();
  }
  // FIXME define methods

  // gets

  public static class TerminalComparator implements Comparator<Terminal>, Serializable{
    public int compare (Terminal t1, Terminal t2) {
      return t1.getID().compareToIgnoreCase(t2.getID());
    }
  }

  public String getID() {
    return this._id;
  }

  public Client getClient() {
    return this._client;
  }

  public double getBalance(){
    return getValue(_payments) - getValue(_depts);
  }

  public String getModeDisplay() {
    if (_mode == TerminalMode.ON) {
      return "IDLE";
    }

    return _mode.toString();
  }

  public String getMode() {
    return _mode.toString();
  }

  public TerminalMode getModeNoString(){
    return _mode;
  }

  public InteractiveCommunication getOngoingCommunication() {
    return this._ongoingCommunication;
  }

  public List<Communication> getPayments() {
    return this._payments;
  }

  public List<Communication> getDepts() {
    return this._depts;
  }

  public List<Notification> getPendingNotifications(){
    return _pendingNotifications;
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

  public String getFriends() {
    String friends = "|";
    if (_friends.size() > 0) {
      Iterator<Terminal> iter = _friends.iterator();
      while (iter.hasNext()) {
        Terminal friend = iter.next();
        friends = friends + friend.getID() + ",";
      }
      return friends.substring(0, friends.length() - 1);
    }
    friends = "";
    return friends;
  }

  public List<Terminal> getFriendslist(){
    List<Terminal> orderedlist = new ArrayList<>(_friends);
    return Collections.unmodifiableList(orderedlist);
  }

  public TerminalMode getPreviousMode(){
    return this._previousMode;
  }

  public Terminal findFriend(String id) {
    for(Terminal t : _friends) {
      if (t.getID().equals(id)) {
        return t;
      }
    }
    return null;
  }

  public Boolean isOff() {
    return _mode == TerminalMode.OFF;
  }

  public Boolean isBusy() {
    return _mode == TerminalMode.BUSY;
  }

  public Boolean isSilent() {
    return _mode == TerminalMode.SILENCE;
  }

  public Boolean isFriends(Terminal amigo){
    for(Terminal t : _friends){
      if(t.getID().equals(amigo.getID())){
        return true;
      }
    }
    return false;
  }

  // sets
  public void setClient(Client client) {
    this._client = client;
  }

  public void setPreviousMode(TerminalMode mode){
    this._previousMode = mode;
  }
  public void setOngoingCommunication(InteractiveCommunication interactiveCommunication) {
    this._ongoingCommunication = interactiveCommunication;
  }

  // off - to - silent
  public TerminalMode setOnSilent() {
    if(_mode.equals(TerminalMode.OFF)){
      Iterator<Notification> iter = _pendingNotifications.iterator();
      while(iter.hasNext()){
        Notification notification = iter.next();
        if(notification.getCommunication().getType().equals("TEXT")){
          notification.setNotificationType("O2S");
          notification.getClient().addNotification(notification);
          iter.remove();
        }
      }
    }
    _mode = TerminalMode.SILENCE;
    return _mode;
  }

  public TerminalMode turnOff() {
    _mode = TerminalMode.OFF;
    return _mode;
  }

  //off - to - idle || silent - to - idle || busy - to - idle
  public TerminalMode turnOn() {
    if(_mode.equals(TerminalMode.OFF)){
      for(Notification n : _pendingNotifications){
        n.setNotificationType("O2I");
        n.getClient().addNotification(n);
      }
    }
    else if(_mode.equals(TerminalMode.SILENCE)){
      for(Notification n : _pendingNotifications){
        n.setNotificationType("S2I");
        n.getClient().addNotification(n);
      }
    }
    else if(_mode.equals(TerminalMode.BUSY)){
      for(Notification n : _pendingNotifications){
        n.setNotificationType("B2I");
        n.getClient().addNotification(n);
      }
    }
    
    _pendingNotifications.clear();
    _mode = TerminalMode.ON;
    return _mode;
  }

  public TerminalMode turnSilence() {
    _mode = TerminalMode.SILENCE;
    return _mode;
  }

  public TerminalMode turnBusy() {
    _mode = TerminalMode.BUSY;
    return _mode;
  }


  public void setMode(TerminalMode mode){
    this._mode = mode;
  }
  
  // add
  public void addFriend(Terminal terminal) {
    _friends.add(terminal);
  }

  public void addCommunicationMade(Communication communication) {
    _communicationsMade.add(communication);
    _depts.add(communication);
  }

  public void addCommunicationRecieved(Communication communication){
    _communicationsReceived.add(communication);
  }

  public void addPendingNotification(Notification notification){
    _pendingNotifications.add(notification);
  }

  public void addPayments(Communication communication){
    _payments.add(communication);
  }

  public void removeFriend(Terminal terminal){
    _friends.remove(terminal);
  }

  // Is
  public boolean isUnused() {
    return _communicationsMade.size() == 0 && _communicationsReceived.size() == 0;
  }

  // other methods
  // terminalType|terminalId|clientId|terminalStatus|balance-paid|balance-debts|friend1,...,friend
  abstract public String showTerminal();

  /**
   * Checks if this terminal can end the current interactive communication.
   *
   * @return true if this terminal is busy (i.e., it has an active interactive
   *         communication) and
   *         it was the originator of this communication.
   **/
  public boolean canEndCurrentCommunication() {
    // FIXME add implementation code
    if (this._ongoingCommunication != null){
      Terminal t = _ongoingCommunication.getOrigin();
      return this.equals(t);
    } else {
      return false;
    }
    
    
  }

  /**
   * Checks if this terminal can start a new communication.
   *
   * @return true if this terminal is neither off neither busy, false otherwise.
   **/
  public boolean canStartCommunication() {
    // FIXME add implementation code
    if(_mode.equals(TerminalMode.OFF) || _mode.equals(TerminalMode.BUSY)){
      return false;
    }
    return true;
  }

  abstract protected boolean canMakeVideoCall();


  public boolean canMakeInterractiveCall(){
    if(_mode.equals(TerminalMode.ON) || _mode.equals(TerminalMode.SILENCE)){
      return true;
    }
    return false;
  }

  public boolean isNotificationDuplicate(Notification notification){
    Client c = notification.getClient();
    for (Notification n : _pendingNotifications) {
      if (c.equals(n.getClient())){
        return true;
      }
    }
    return false;
  }

  public Communication findCommunicationAndRemoveOrNull(int n){
    Iterator<Communication> iter = _depts.iterator();
    while(iter.hasNext()){
      Communication comm = iter.next();
      if(comm.getID()==n){
        addPayments(comm);
        iter.remove();
        return comm;
      }
    }
    return null;
  }

  public abstract String getType();
}
