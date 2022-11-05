package prr.core;

public class TextCommunication extends Communication {

  private String _message;

  public TextCommunication(Terminal terminalOrigin, Terminal terminalDestination, String message) {
    super(terminalOrigin, terminalDestination);
    setUnits(message.length());
    this._message = message;
    setOngoing(false);
  }

  protected double getCost(TariffPlan plan) {
    // para a versao intermedia ainda nao e necessario
    return 0;
  }

  protected int getSize() {
    return _message.length();
  }

  public String showCommunication() {
    String com = String.format("%s|%d|%s|%s|%d|%d|%s", "TEXT", getID(), getOrigin().getID(),
        getDestination().getID(), getUnits(), getCostDisplay(), getStatus());

    return com;
  }

  @Override
  public String getType() {
    return "TEXT";
  }
}
