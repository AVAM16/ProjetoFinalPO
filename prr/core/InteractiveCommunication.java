package prr.core;

abstract public class InteractiveCommunication extends Communication{
  
  private int _duration;

  public InteractiveCommunication(Terminal terminalOrigin,Terminal terminalDestination,CommunicationType type, int duration){
    super(terminalOrigin, terminalDestination, type);
    this._duration = duration;
  }

  protected int getSize(){
    return _duration;
  }

  public int getDuration(){
    return _duration;
  }
}
