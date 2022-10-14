package prr.app.client;

import java.util.LinkedList;
import java.util.logging.Level;

import prr.core.Terminal;

public class Client {
  private String _key;
  private String _name;
  private int _taxNumber;
  private ClientLevel _level;
  private boolean _receiveNotifications;
  private LinkedList<Terminal> _terminals;
  private TariffPlan _plan;

  public Client(String key, String name, int taxNumber){
    this._key = key;
    this._name = name;
    this._taxNumber = taxNumber;
    _level = ClientLevel.NORMAL;
    _terminals = new LinkedList<>();
  }

  public String showName(){
    return this._name;
  }

  public int showTaxID(){
    return this._taxNumber;
  }

  public ClientLevel showClientLevel(){
    return this._level;
  }
}
