package prr.core;

import java.io.Serializable;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable /* FIXME maybe addd more interfaces */{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;
  // FIXME define attributes
  private String _id;
  private Client _client;
  private List<Terminal> _friends;
  private List<Communication> _payments;
  private List<Communication> _depts;
  private List<Communication> _communicationsReceived;
  private List<Communication> _communicationsMade;
  private TerminalMode _mode;
  
  // FIXME define contructor(s)
  public Terminal(String  id){
    this._id = id;
    this._client = null;
    this._payments = new ArrayList<>();
    this._depts = new ArrayList<>();
    this._communicationsReceived = new ArrayList<>();
    this._communicationsMade = new ArrayList<>();
    this._friends = new ArrayList<>();
    this._mode = TerminalMode.ON;
  }
  // FIXME define methods

  //gets

  public String getID(){
    return this._id;
  }

  public Client getClient(){
    return this._client;
  }

  public String getMode(){
    return _mode.toString();
  }

  public List<Communication> getPayments(){
    return this._payments;
  }

  public List<Communication> getDepts(){
    return this._depts;
  }

  public int getValue(List<Communication> list){
    double sum=0;
    Iterator<Communication> iter = list.iterator();
    while(iter.hasNext()){
        Communication communication = iter.next();
        sum = sum + communication.getCost();
    }
    return (int)Math.round(sum);
  }

  public String getFriends(){
    String friends="";
    if(_friends.size()>0){
      Iterator<Terminal> iter = _friends.iterator();
      while(iter.hasNext()){
        Terminal friend = iter.next();
        friends=friends + friend+",";
    }
    return friends.substring(0, friends.length()-1);
  }
  return friends;
  }

  //sets
  public void setClient(Client client){
    this._client=client;
  }

  // isto nao esta void porque e assim que a clase parser quer estes metodos 
  public TerminalMode setOnSilent() {
    return TerminalMode.SILENCE;
  }

  public TerminalMode turnOff() {
    return TerminalMode.OFF;
  } 

  //add
  public void addFriend(Terminal terminal){
    _friends.add(terminal);
  }

  // Is
  public boolean isUnused(){
    return _communicationsMade.size()==0 && _communicationsReceived.size()==0;
  }

  //other methods
  //terminalType|terminalId|clientId|terminalStatus|balance-paid|balance-debts|friend1,...,friend
  abstract public String showTerminal();

  
  /**
   * Checks if this terminal can end the current interactive communication.
   *
   * @return true if this terminal is busy (i.e., it has an active interactive communication) and
   *          it was the originator of this communication.
   **/
  public boolean canEndCurrentCommunication() {
    // FIXME add implementation code
    return true;
  }
  
  /**
   * Checks if this terminal can start a new communication.
   *
   * @return true if this terminal is neither off neither busy, false otherwise.
   **/
  public boolean canStartCommunication() {
    // FIXME add implementation code
    return true;
  }

 
}
