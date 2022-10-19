package prr.core;

public class FancyTerminal extends Terminal{
  
  public FancyTerminal(String id){
    super(id);
  }

    //terminalType|terminalId|clientId|terminalStatus|balance-paid|balance-debts|friend1,...,friend
    public String showTerminal(){
      String terminal = String.format("FANCY|%s|%s|%s|%,.2f|%,.2f|%d",getID(),getClient().getKey(),getMode().toString(),
      getClient().getValue(getPayments()),getClient().getValue(getDepts()),getFriends());
      return terminal;
    }
}
