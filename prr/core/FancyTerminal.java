package prr.core;

public class FancyTerminal extends Terminal{
  
  public FancyTerminal(String id){
    super(id);
  }

    //terminalType|terminalId|clientId|terminalStatus|balance-paid|balance-debts|friend1,...,friend
    public String showTerminal(){
      if(getClient()==null){
      String terminal = String.format("FANCY|%s|%s|%s|%d|%d|%s",getID(),"NONE",getMode().toString(),
        getValue(getPayments()),getValue(getDepts()),getFriends());
        return terminal;
      }
      String terminal = String.format("FANCY|%s|%s|%s|%d|%d|%s",getID(),getClient().getKey(),getMode().toString(),
      getValue(getPayments()),getValue(getDepts()),getFriends());

      return terminal;
    }
}
