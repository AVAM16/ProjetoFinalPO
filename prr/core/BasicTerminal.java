package prr.core;

public class BasicTerminal extends Terminal {

  public BasicTerminal(String id) {
    super(id);
  }

  public String showTerminal() {
    String terminal = String.format("BASIC|%s|%s|%s|%d|%d%s", getID(), getClient().getKey(), getModeDisplay(),
        getValue(getPayments()), getValue(getDepts()), getFriends());
    return terminal;
  }

}
