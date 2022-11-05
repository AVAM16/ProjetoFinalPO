package prr.core;

abstract public class InteractiveCommunication extends Communication {

  private int _duration;

  public InteractiveCommunication(Terminal terminalOrigin, Terminal terminalDestination) {
    super(terminalOrigin, terminalDestination);
  }

  protected int getSize() {
    return _duration;
  }

  public int getDuration() {
    return _duration;
  }

  public void setDuration(int duration) {
    this._duration = duration;
  }
}
