package prr.core;

public class VideoCommunication extends InteractiveCommunication{
  
  public VideoCommunication(Terminal terminalOrigin,Terminal terminalDestination, int duration){
    super(terminalOrigin, terminalDestination, duration);
  }

  protected double getCost(TariffPlan plan){
    //para a versao intermedia ainda nao e necessario
    return 0;
  }

}
