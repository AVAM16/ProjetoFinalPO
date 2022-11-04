package prr.core;

public class TerminalFactory {
  public Terminal createTerminal(String terminalType, String id){   
    if(terminalType.equals("FANCY")) {  
      return new FancyTerminal(id);  
    } else {  
      return new BasicTerminal(id);  
    }    
  }
}
