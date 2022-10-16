package prr.core;

abstract public class InteractiveCommunication extends Communication{
  
  private int _duration;

  public InteractiveCommunication(String id,Terminal terminalOrigin,Terminal terminalDestination, int duration){
    super(id, terminalOrigin, terminalDestination);
    this._duration = duration;
  }

  protected int getSize(){
    return _duration;
  }
}
