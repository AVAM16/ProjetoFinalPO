package prr.core;

public class TextCommunication extends Communication{
  
  private String _message;

  public TextCommunication(Terminal terminalOrigin, Terminal terminalDestination, String message){
    super(terminalOrigin, terminalDestination, CommunicationType.TEXT);
    this._message = message;
    setUnits(message.length());
    setOngoing(false);
  }

  protected double getCost(TariffPlan plan){
    //para a versao intermedia ainda nao e necessario
    return 0;
  }

  protected int getSize(){
    return _message.length();
  }
}
