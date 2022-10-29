package prr.core;

import java.io.Serializable;

abstract public class Communication implements Serializable{
    private static final long serialVersionUID = 202208091753L;
    private String _id;
    private Terminal _terminalOrigin;
    private Terminal _terminalDestination;
    private double _cost;
    private boolean _isOngoing;
    private CommunicationType _type;
    private int _units; //units e o os minutos os a lenght da mensagem

    public Communication(String id,Terminal terminalOrigin,Terminal terminalDestination){
        this._id = id;
        this._terminalOrigin = terminalOrigin;
        this._terminalDestination = terminalDestination;
        this._cost=0;
        this._units=0;
    }

    //gets

    public String getID(){
        return this._id;
    }

    public String getOrigin(){
        return this._terminalOrigin.getID();
    }

    public String getDestination(){
        return this._terminalDestination.getID();
    }

    public double getCost(){
        return this._cost;
    }

    public String getType(){
        return _type.toString();
    }

    public int getUnits(){
        return _units;
    }

    public String getStatus(){
        if(_isOngoing){
            return "ONGOING";
        }
        return "FINISHED";
    }

    public boolean isOngoing(){
        return this._isOngoing;
    }



    //sets

    public void setCost(Double cost){
        this._cost = cost;
    }

    //show type|idCommunication|idSender|idReceiver|units|price|status
    public String showCommunication(){
       String com = String.format("%s|%d|%s|%s|%d|%d|%s",getType(),getID(),_terminalOrigin.getID(),
       _terminalDestination.getID(),getUnits(),getCost(),getStatus());

        return com;
    }
}