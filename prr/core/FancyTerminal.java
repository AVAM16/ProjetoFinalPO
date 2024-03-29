package prr.core;

public class FancyTerminal extends Terminal {

  public FancyTerminal(String id) {
    super(id);
  }

  // terminalType|terminalId|clientId|terminalStatus|balance-paid|balance-debts|friend1,...,friend
  public String showTerminal() {
    String terminal = String.format("FANCY|%s|%s|%s|%d|%d%s", getID(), getClient().getKey(), getModeDisplay(),
        getValue(getPayments()), getValue(getDepts()), getFriends());

    return terminal;
  }

  @Override
  protected boolean canMakeVideoCall() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public String getType() {
    return "FANCY";
  }
}
