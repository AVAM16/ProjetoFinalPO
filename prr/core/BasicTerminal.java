package prr.core;

public class BasicTerminal extends Terminal{

  public BasicTerminal(String id){
    super(id);
  }
  public String showTerminal(){
    String terminal = String.format("BASIC|%d|%d|%d|%d|%d|%d|%d|%d",getID(),getClient(),getMode(),getClient().getValue(getPayments())
    ,getClient().getValue(getDepts()),getFriends());
    return terminal;
  }
  
}
