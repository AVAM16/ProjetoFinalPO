package prr.core;

public class BasicTerminal extends Terminal{

  public BasicTerminal(String id){
    super(id);
  }
  public String showTerminal(){
    if(getClient()==null){
      String terminal = String.format("BASIC|%s|%s|%s|%d|%d%s",getID(),"NONE",getMode().toString(),
        getValue(getPayments()),getValue(getDepts()),getFriends());
        return terminal;
      }
      String terminal = String.format("BASIC|%s|%s|%s|%d|%d%s",getID(),getClient().getKey(),getMode().toString(),
      getValue(getPayments()),getValue(getDepts()),getFriends());

      return terminal;
    }
  
}
