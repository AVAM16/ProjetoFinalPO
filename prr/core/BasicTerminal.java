package prr.core;

public class BasicTerminal extends Terminal{

  public BasicTerminal(String id){
    super(id);
  }
  public String showTerminal(){
    String terminal = String.format("BASIC|%s|%s|%s|%,.2f|%,.2f|%d",getID(),getClient().getKey(),getMode().toString(),
    getClient().getValue(getPayments()),getClient().getValue(getDepts()),getFriends());
    return terminal;
  }
  
}
