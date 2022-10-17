package prr.core;

public class VoiceCommunication extends InteractiveCommunication{
  
  public VoiceCommunication(String id,Terminal terminalOrigin,Terminal terminalDestination, int duration){
    super(id, terminalOrigin, terminalDestination, duration);
  }

  protected double getCost(TariffPlan plan){
    //para a versao intermedia ainda nao e necessario
    return 0;
  }
}
