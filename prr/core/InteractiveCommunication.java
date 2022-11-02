package prr.core;

abstract public class InteractiveCommunication extends Communication{
  
  private int _duration;

<<<<<<< HEAD
  public InteractiveCommunication(Terminal terminalOrigin,Terminal terminalDestination){
    super(terminalOrigin, terminalDestination);
=======
  public InteractiveCommunication(Terminal terminalOrigin,Terminal terminalDestination, CommunicationType type){
    super(terminalOrigin, terminalDestination, null);
>>>>>>> 56f543194df8df75d465acd9d1e88ccfda18462f
  }

  protected int getSize(){
    return _duration;
  }

  public int getDuration(){
    return _duration;
  }

  public void setDuration(int duration) {
    this._duration = duration;
  }
}
