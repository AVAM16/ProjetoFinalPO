package prr.core;

import java.util.LinkedList;
import java.util.List;

public class Client {
    private String _key;
    private String _name;
    private int _nif;
    private List<Communication> _communicationsReceived;
    private List<Communication> _communicationsMade;
    private List<Communication> _communicationsPaid;
    private List<Communication> _communicationsDept;
    private List<Terminal> _terminals;

    public Client(String key, String name, int nif){
        this._key=key;
        this._name=name;
        this._nif=nif;
        this._terminals= new LinkedList<>();
        this._communicationsDept = new LinkedList<>();
        this._communicationsPaid = new LinkedList<>();
        this._communicationsReceived = new LinkedList<>();
        this._communicationsMade = new LinkedList<>();
    }

    //gets

    public String getKey(){
        return this._key;
    }

    public String getName(){
        return this._name;
    }

    public int getNIF(){
        return this._nif;
    }

    //outros metodos
    public void addTerminal(Terminal terminal){
        this._terminals.add(terminal);
    }

    //CLIENT|key|name|taxId|type|notifications|terminals|payments|debts
    public void showClient(){
        
    }
    
}
