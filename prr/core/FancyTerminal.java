package prr.core;

public class FancyTerminal extends Terminal{
  
  public FancyTerminal(String id){
    super(id);
  }

    //terminalType|terminalId|clientId|terminalStatus|balance-paid|balance-debts|friend1,...,friend
    public String showTerminal(){
      String terminal = String.format("FANCY|%d|%d|%d|%d|%d|%d|%d|%d",getID(),getClient(),getMode(),getClient().getValue(getPayments())
      ,getClient().getValue(getDepts()),getFriends());
      return terminal;
    }
}
