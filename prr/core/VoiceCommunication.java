package prr.core;

public class VoiceCommunication extends InteractiveCommunication{
  
  public VoiceCommunication(Terminal terminalOrigin,Terminal terminalDestination){
    super(terminalOrigin, terminalDestination);
  }

  protected double getCost(TariffPlan plan){
    //para a versao intermedia ainda nao e necessario
    return 0;
  }
}
