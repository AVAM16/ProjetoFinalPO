package prr.core;

import java.io.Serializable;

public class Notification implements Serializable{
    private static final long serialVersionUID = 202208091753L;
    private Client _client;
    private Communication _communication;
    private NotificationType _type;
    private Terminal _notifyingTerminal;
    
    public Notification(Communication communication,Client client,Terminal notifyingTerminal){
        this._communication = communication;
        this._client = client;
        this._notifyingTerminal = notifyingTerminal;
  
    }

    //gets
    public Client getClient(){
        return _client;
    }

    public NotificationType getType(){
        return _type;
    }

    public Communication getCommunication(){
        return _communication;
    }

    public String getTeminalOrigin(){
        return _communication.getOrigin().getID();
    }

    public String getTeminalDestination(){
        return _communication.getDestination().getID();
    }

    //sets
    
    public void setNotificationType(String type){
        switch(type){
            case "O2S":
            _type = NotificationType.O2S;
            break;

            case "O2I":
            _type = NotificationType.O2I;
            break;

            case "S2I":
            _type = NotificationType.S2I;
            break;
            
            case "B2I":
            _type = NotificationType.B2I;
            break;

        }
        
    }

    public String showNotification() {
        String notification=String.format("%s|%s", getType(), _communication.getDestination().getID());
        
        //_notifications.clear();
        return notification;
      }

    
}
