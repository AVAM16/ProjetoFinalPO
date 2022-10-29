package prr.core;

abstract public class TariffPlan {
  private double _cost;
  private Client _client;
  private Communication _communication;

  //
  public TariffPlan(Communication communication, Client client){
    this._client=client;
    this._communication=communication;
  }

  protected abstract double getCostVideo();

  protected abstract double getCostCall();

  protected abstract double getCostText();

  public Client getClient(){
    return _client;
  }

  public Communication getCommunication(){
    return _communication;
  }

  public double getCost(){
    return _cost;
  }

  // isto secalhar tambem devia adicionar o custo ao cliente e aos terminais
  public void setCost(double cost){
    _cost=cost;
  }
}
