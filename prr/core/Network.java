package prr.core;

import java.io.Serializable;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
  private List<Communication> _communications;
  private TreeMap<String, Client> _clients;
  private TreeMap<String, Terminal> _terminals;
  private List<TariffPlan> _tariffPlans;
  static Network _ocurrence;
  
  // FIXME define contructor(s)
  public Network(){
    _communications = new ArrayList<>();
    _clients = new TreeMap<>(new IdComparator());
    _terminals = new TreeMap<>(new IdComparator());
    _tariffPlans = new ArrayList<>();
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

  public static class IdComparator implements Comparator<String>, Serializable{
    public int compare (String id1, String id2) {
        return id1.compareToIgnoreCase(id2);
    }
  }

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
    if (_clients.containsKey(key)){
      return _clients.get(key);
    } else{
      throw new UnknownClientKeyException(key);
    }
  }

  public Client getClient(String key){
    if (_clients.containsKey(key)){
      return _clients.get(key);
    }
    return null;
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
    if (_clients.containsKey(key)){
      throw new DuplicateClientKeyException(key);
    }
    _clients.put(key, newClient);
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

    if (_terminals.containsKey(id)){
      throw new DuplicateTerminalKeyException(id);
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
    _terminals.put(id, terminalNovo);
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
    Iterator<Terminal> iter = _terminals.values().iterator();
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
    if (_terminals.containsKey(id)){
      return _terminals.get(id);
    } else{
      throw new UnknownTerminalKeyException(id);
    }
  }

  public Terminal findTerminalNull(String id) throws UnknownTerminalKeyException{
    if (_terminals.containsKey(id)){
      return _terminals.get(id);
    } else{
      return null;
    }
  }

   /**
   * Returns a Collection of all the clients.
   * @returns the clients
   */

  public List<Client> getClients(){
    Collection<Client> orderedclients = _clients.values();
    ArrayList<Client> orderedlist = new ArrayList<>(orderedclients);
    return Collections.unmodifiableList(orderedlist);
  }

  public List<Communication> getComms(){
    return Collections.unmodifiableList(_communications);
  }



  

  /**
   * Returns a Collection of all the terminals.
   * @returns the terminals
   */
  public List<Terminal> getTerminals(){
    Collection<Terminal> orderedterminals = _terminals.values();
    ArrayList<Terminal> orderedlist = new ArrayList<>(orderedterminals);
    return Collections.unmodifiableList(orderedlist);
  }

  //private  TreeMap<String, Client> _clients; 
  // isto e capaz de estar errado no compare
  public ArrayList<Client> getClientsWithDepts(){
    ArrayList<Client> clients = new ArrayList<>();
    for(Map.Entry<String, Client> entry : _clients.entrySet()){
      if(entry.getValue().isDebtor()){
        clients.add(entry.getValue());
      }
    }
    Collections.sort(clients,new Comparator<Client>(){
      public int compare(Client a, Client b){
        if(a.getDeptsSum()>b.getDeptsSum()){
          return -1;
        }
        else{
          return 1;
        }
      }
    });
    
    return clients;
  }

}

