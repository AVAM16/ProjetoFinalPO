package prr.core;

abstract public class TariffPlan {
  private String _name;
  private double _cost;
  private Client _client;
  private Communication _communication;

  //
  public TariffPlan(String name, Communication communication, Client client){
    this._name = name;
    this._client = client;
    this._communication = communication;
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

  public double computeCost(){
    return _cost;
  }

  // isto secalhar tambem devia adicionar o custo ao cliente e aos terminais
  public void setCost(double cost){
    _cost=cost;
  }
}
