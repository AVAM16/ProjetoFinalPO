package prr.core;

public class VoiceCommunication extends InteractiveCommunication {

  public VoiceCommunication(Terminal terminalOrigin, Terminal terminalDestination) {
    super(terminalOrigin, terminalDestination);
  }

  protected double getCost(TariffPlan plan) {
    // para a versao intermedia ainda nao e necessario
    return 0;
  }

  public String showCommunication() {
    String com = String.format("%s|%d|%s|%s|%d|%d|%s", "VOICE", getID(), getOrigin().getID(),
        getDestination().getID(), getUnits(), getCostDisplay(), getStatus());

    return com;
  }

  @Override
  public String getType() {
    return "VOICE";
  }
}
