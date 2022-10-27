package prr.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;

import prr.app.exception.DuplicateClientKeyException;
import prr.app.exception.DuplicateTerminalKeyException;
import prr.app.exception.InvalidTerminalKeyException;
import prr.app.exception.UnknownClientKeyException;
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
  private  ArrayList<Client> _clients;
  private  ArrayList<Terminal> _terminals;
  static Network _ocurrence;
  
  // FIXME define contructor(s)
  public Network(){
    _clients = new ArrayList<>();
    _terminals = new ArrayList<>();
  }
  // FIXME define methods
  
  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   * @throws InvalidTerminalKeyException if there is an invalid terminal key
   * @throws DuplicateClientKeyException if there client key already exists
   * @throws UnknownClientKeyException if the client does not exist
   * @throws DuplicateTerminalKeyException
   */

  void importFile(String filename) throws UnrecognizedEntryException, IOException , InvalidTerminalKeyException, UnknownClientKeyException  { /* FIXME maybe other exceptions */
    //FIXME implement method
    Parser parser = new Parser(this);
    parser.parseFile(filename);
   
  }

  /*-------METODOS DOS CLIENTES------*/

  /**
   * Finds and returns the client.
   * 
   * @param key id of the client
   * @returns c the correspondant client
   * @throws UnknownClientKeyException if the client does not exist
   */

  public Client findClient(String key) throws UnknownClientKeyException{
    for(Client c: _clients){
      if(key.equals(c.getKey())){
        return c;
      }
    }
    throw new UnknownClientKeyException(key);
  }

  /**
   * Registers the client.
   * 
   * @param key id of the client
   * @param name name of the client
   * @param taxNumber taxNumber of the client
   * @throws DuplicateClientKeyException
   */

  public void registerClient(String key, String name, int taxNumber) throws DuplicateClientKeyException {
    Client newClient = new Client(key, name, taxNumber);
    Iterator<Client> iter = _clients.iterator();
    while(iter.hasNext()){
      Client client = iter.next();
      if(key.equals(client.getKey())){
        throw new DuplicateClientKeyException(key);
      }
    }
    _clients.add(newClient);
  }


  /*-------METODOS DOS TERMINAIS------*/

  /**
   * Evaluates if the string is numeric
   * 
   * @param key id of the client
   * @param comprimento lenght of the string
   * @returns a boolean
   */

  public boolean onlyDigits(String key, int comprimento)
    {
        for (int i = 0; i < comprimento; i++) {
            if (!Character.isDigit(key.charAt(i))) {
                return false;
            }
        }
        return true;
    }
  
  /**
   * Registers the terminal.
   * 
   * @param type the type of the terminal (BASIC or FANCY)
   * @param id id of the terminal
   * @param clientID the id of the owner of the terminal
   * @returns the created terminal
   * @throws InvalidTerminalKeyException if the created terminal has an invalid id
   * @throws UnknownClientKeyException if the client assigned to the terminal does not exist
   * @throws DuplicateTerminalKeyException
   */

  public Terminal registerTerminal(String type,String id,String clientID) throws InvalidTerminalKeyException, UnknownClientKeyException, DuplicateTerminalKeyException{

    Iterator<Terminal> iter = _terminals.iterator();
    while(iter.hasNext()){
      Terminal terminal = iter.next();
      if(id.equals(terminal.getID())){
        throw new DuplicateTerminalKeyException(id);
      }
    }
    
    if(id.length() != 6 || !onlyDigits(id,6)){
      throw new InvalidTerminalKeyException(id);

    }
    Terminal terminalNovo = null;
    Client client = findClient(clientID);
    
   
    if(type.equals("FANCY")){
      terminalNovo = new FancyTerminal(id);   
    }

    if(type.equals("BASIC")){
      terminalNovo = new BasicTerminal(id);  
    }
    

    client.addTerminal(terminalNovo);
    _terminals.add(terminalNovo);
    terminalNovo.setClient(client);

    return terminalNovo;
  }
  
  //InvalidTerminalKeyException(String key)
    /**
   * Adds a friend to a specific terminal.
   * 
   * @param terminal the id of terminal we want to add a friend to
   * @param friend id of the terminal's future friend (hopefully)
   * @throws UnknownClientKeyException if the terminal or the friend does not exist
   */
  public void addFriend(String terminal, String friend) throws UnknownTerminalKeyException{
    Terminal terminalWantsFriend=null;
    Terminal terminalIsFriend=null;
    Iterator<Terminal> iter = _terminals.iterator();
    while(iter.hasNext() && (terminalWantsFriend==null || terminalIsFriend==null)){
      Terminal currentTerminal = iter.next();
      if(currentTerminal.getID().equals(terminal)){
        terminalWantsFriend = currentTerminal;
      }
      if(currentTerminal.getID().equals(friend)){
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

    /**
   * Finds a specific terminal.
   * 
   * @param idl the id of terminal we want to find
   * @param friend id of the terminal's future friend (hopefully)
   * @throws UnknownClientKeyException if the terminal does not exist
   */
  public Terminal findTerminal(String id) throws UnknownTerminalKeyException{
    for(Terminal t: _terminals){
      if(id.equals(t.getID())){
        return t;
      }
    }
    throw new UnknownTerminalKeyException(id);
  }

   /**
   * Returns a Collection of all the clients.
   * @returns the clients
   */

  public List<Client> getClients(){
    return Collections.unmodifiableList(_clients);
  }

  /**
   * Returns a Collection of all the terminals.
   * @returns the terminals
   */
  public List<Terminal> getTerminals(){
    return Collections.unmodifiableList(_terminals);
  }

}

