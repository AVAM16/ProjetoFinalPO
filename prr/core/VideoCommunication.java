package prr.core;

public class VideoCommunication extends InteractiveCommunication{
  
  public VideoCommunication(Terminal terminalOrigin,Terminal terminalDestination){
    super(terminalOrigin, terminalDestination);
  }

  protected double getCost(TariffPlan plan){
    //para a versao intermedia ainda nao e necessario
    return 0;
  }

  public String showCommunication(){
    String com = String.format("%s|%d|%s|%s|%d|%d|%s","VIDEO",getID(),getOrigin().getID(),
    getDestination().getID(),getUnits(),getCostDisplay(),getStatus());

     return com;
  }

}
