package prr.core;

import java.io.Serializable;
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
<<<<<<< HEAD
  private LinkedList<Integer> _payments;
  private LinkedList<Integer> _depts;
=======
  private List<Integer> _payments;
  private List<Integer> _depts;
  private List<Communication> _communicationsReceived;
  private List<Communication> _communicationsMade;
>>>>>>> eb20ca0da30d1feedd569f9073c1fb020e5470be
  /*Nota : falta por o estado do terminal */
  
  // FIXME define contructor(s)
  public Terminal(String  id){
    this._id = id;
    this._payments = new LinkedList<>();
    this._depts = new LinkedList<>();
    this._communicationsReceived = new LinkedList<>();
    this._communicationsMade = new LinkedList<>();
  }
  // FIXME define methods

  //gets

  public String getID(){
    return this._id;
  }

  public Client getClient(){
    return this._client;
  }

  //sets
  public void setClient(Client client){
    this._client=client;
  }

  // Is
  public boolean isNotActive(){
    return _communicationsMade.size()==0 && _communicationsReceived.size()==0;
  }
  
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
