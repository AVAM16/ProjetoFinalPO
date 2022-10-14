package prr.app.client;

import java.util.logging.Level;

public class Client {
  private String _key;
  private String _name;
  private int _taxNumber;
  private ClientLevel _level;
  private boolean _receiveNotifications;

  public Client(String key, String name, int taxNumber){
    this._key = key;
    this._name = name;
    this._taxNumber = taxNumber;
    _level = ClientLevel.NORMAL;
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
