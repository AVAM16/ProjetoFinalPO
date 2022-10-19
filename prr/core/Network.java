package prr.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
  private static ArrayList<Client> _clients;
  private static ArrayList<Terminal> _terminals;
  static Network _ocurrence;
  
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
   * @throws InvalidTerminalKeyException
   * @throws DuplicateClientKeyException
   */
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */, DuplicateClientKeyException, InvalidTerminalKeyException  {
    //FIXME implement method
    Parser parser = new Parser(this);
    parser.parseFile(filename);
  }


  /*-------METODOS DOS CLIENTES------*/


  public Client findClient(String key) throws UnknownClientKeyException{
    for(Client c: _clients){
      if(key.equals(c.getKey())){
        return c;
      }
    }
    throw new UnknownClientKeyException(key);
  }

  //myStr1.equals(myStr3)
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



  public static boolean onlyDigits(String str, int n)
    {
        for (int i = 0; i < n; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

 
  public void registerTerminal(String id, String type) throws InvalidTerminalKeyException,DuplicateTerminalKeyException{
    if(id.length() != 6 || !onlyDigits(id,6)){
      throw new InvalidTerminalKeyException(id);
    }
    Terminal terminalNovo = null;
    /* 
    Terminal terminalNovo = null;
    switch(type){
      case "FANCY":
        terminalNovo = new FancyTerminal(id);
        break;
      case "BASIC":
        terminalNovo= new BasicTerminal(id);
        break;  

    }*/
   
    if(type.equals("FANCY")){
      terminalNovo = new FancyTerminal(id);   
    }

    if(type.equals("BASIC")){
      terminalNovo = new BasicTerminal(id);  
    }
    
    Iterator<Terminal> iter = _terminals.iterator();
    while(iter.hasNext()){
      Terminal terminal = iter.next();
      if(terminalNovo.getID().equals(terminal.getID())){
        throw new DuplicateTerminalKeyException(id);
      }
    }
    _terminals.add(terminalNovo);
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



//key.equals(c.getKey()
  public Terminal findTerminal(String id) throws UnknownTerminalKeyException{
    for(Terminal t: _terminals){
      if(id.equals(t.getID())){
        return t;
      }
    }
    throw new UnknownTerminalKeyException(id);
  }

  // isto deve ser para tirar acho eu
  public Terminal findTerminalB(String id) {
    for(Terminal t: _terminals){
      if(t.getID() == id){
        return t;
      }
    }
    return null;
  }

  public List<Client> getClients(){
    return Collections.unmodifiableList(_clients);
  }

  public List<Terminal> getTerminals(){
    return Collections.unmodifiableList(_terminals);
  }

}

