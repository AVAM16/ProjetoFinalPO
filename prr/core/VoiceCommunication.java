package prr.core;

public class VoiceCommunication extends InteractiveCommunication{
  
<<<<<<< HEAD
  public VoiceCommunication(Terminal terminalOrigin,Terminal terminalDestination){
    super(terminalOrigin, terminalDestination);
=======
  public VoiceCommunication(Terminal terminalOrigin,Terminal terminalDestination,CommunicationType type){
    super(terminalOrigin, terminalDestination, CommunicationType.VOICE);
>>>>>>> 56f543194df8df75d465acd9d1e88ccfda18462f
  }

  protected double getCost(TariffPlan plan){
    //para a versao intermedia ainda nao e necessario
    return 0;
  }
}
