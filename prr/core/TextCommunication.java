package prr.core;

public class TextCommunication extends Communication{
  
  private String _message;

  public TextCommunication(String id,Terminal terminalOrigin,Terminal terminalDestination, String message){
    super(id, terminalOrigin, terminalDestination);
    this._message = message;
  }

  protected double getCost(TariffPlan plan){

  }

  protected int getSize(){
    return _message.length();
  }
}
