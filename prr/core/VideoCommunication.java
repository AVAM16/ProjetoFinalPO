package prr.core;

public class VideoCommunication extends InteractiveCommunication{
  
  public VideoCommunication(String id,Terminal terminalOrigin,Terminal terminalDestination, int duration){
    super(id, terminalOrigin, terminalDestination, duration);
  }

  protected double getCost(TariffPlan plan){
    
  }
}
