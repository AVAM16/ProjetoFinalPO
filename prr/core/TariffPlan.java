package prr.core;

import java.util.List;

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

  public abstract double getCostVideo();

  public abstract double getCostCall();

  public abstract double getCostText();

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

  public boolean terminalsAreFriends(Terminal terminal, Terminal friend){
    List<Terminal> friendslist = terminal.getFriendslist();
    for(Terminal t : friendslist){
      if(friend.getID().equals(t.getID())){
        return true;
      }
    }
    return false;
  }
}
