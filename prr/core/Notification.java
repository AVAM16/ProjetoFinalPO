package prr.core;

import java.io.Serializable;

public class Notification implements Serializable{
    private static final long serialVersionUID = 202208091753L;
    private Client _client;
    private Communication _communication;
    private NotificationType _type;
    private Terminal _notifyingTerminal;
    
    public Notification(Communication communication,Client client,NotificationType type){
        this._communication = communication;
        this._client = client;
        this._type = type;
    }

    //gets
    public Client getClient(){
        return _client;
    }

    public String getType(){
        return _type.toString();
    }

    public Communication getCommunication(){
        return _communication;
    }

    public String getTeminalOrigin(){
        return _communication.getOrigin();
    }

    public String getTeminalDestination(){
        return _communication.getDestination();
    }

    public void addNotification(){
        _client.addNotification(_communication, _client,_type);    
    }
}