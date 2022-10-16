package prr.core;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Iterator;

import java.io.IOException;

import prr.app.exception.DuplicateClientKeyException;
import prr.app.exception.InvalidTerminalKeyException;
import prr.app.exception.UnknownTerminalKeyException;
import prr.core.exception.UnrecognizedEntryException;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

  /** Serial number for serialization. */
 
  // FIXME define attributes
  private static final long serialVersionUID = 202208091753L;
  private static ArrayList<Client> _clients;
  private static ArrayList<Terminal> _terminals;
  
  // FIXME define contructor(s)
  public Network(){
    Network._clients = new ArrayList<>();
    Network._terminals = new ArrayList<>();
  }
  // FIXME define methods
  
  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   */
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    //FIXME implement method
  }


  //eu não sei se isto está correto
  public void registerClient(String key, String name, int taxNumber) throws DuplicateClientKeyException {
    Client newClient = new Client(key, name, taxNumber);
    Iterator<Client> iter = _clients.iterator();
    while(iter.hasNext()){
      Client client = iter.next();
      if(key==client.getKey()){
        throw new DuplicateClientKeyException(key);
      }
    }
    _clients.add(newClient);
  }

  public Terminal registerTerminal(String string, String string2, String string3) {
      return null;
  }


  
  //InvalidTerminalKeyException(String key)
  public void addFriend(String terminal, String friend) throws UnknownTerminalKeyException{
    Terminal terminalWantsFriend=null;
    Terminal terminalIsFriend=null;
    Iterator<Terminal> iter = _terminals.iterator();
    while(iter.hasNext() && (terminalWantsFriend==null || terminalIsFriend==null)){
      Terminal currentTerminal = iter.next();
      if(currentTerminal.getID()==terminal){
        terminalWantsFriend = currentTerminal;
      }
      if(currentTerminal.getID()==friend){
        terminalIsFriend = currentTerminal;
      }
    }

    if(terminalWantsFriend!=null && terminalIsFriend!=null){
      terminalWantsFriend.addFriend(terminalIsFriend);
    }
    else if(terminalWantsFriend==null){
      throw new UnknownTerminalKeyException(terminal);
    }
    else if(terminalIsFriend==null){
      throw new UnknownTerminalKeyException(friend);
    }
    
  }

}

