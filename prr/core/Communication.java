package prr.core;

import java.io.Serializable;

abstract public class Communication implements Serializable{
    private static final long serialVersionUID = 202208091753L;
    private int _id;
    private Terminal _terminalOrigin;
    private Terminal _terminalDestination;
    private double _cost;
    private boolean _isOngoing;
    private int _units; //units e o os minutos os a lenght da mensagem

    public Communication(Terminal terminalOrigin,Terminal terminalDestination){
        this._terminalOrigin = terminalOrigin;
        this._terminalDestination = terminalDestination;
        this._cost=0;
        this._units=0;
    }

    //gets

    public int getID(){
        return this._id;
    }

    public Terminal getOrigin(){
        return this._terminalOrigin;
    }

    public Terminal getDestination(){
        return this._terminalDestination;
    }

    public double getCost(){
        return this._cost;
    }

    public int getCostDisplay(){
        return (int)Math.round(_cost);
    }

    public String getType(){
        if (this instanceof TextCommunication){
            return "TEXT";
        } else if (this instanceof VoiceCommunication){
            return "VOICE";
        } else {
            return "VIDEO";
        }
    }

    public int getUnits(){
        return this._units;
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

    public void setId(int id){
        this._id = id;
    }

    public void setCost(Double cost){
        this._cost = cost;
    }

    public void setUnits(String n){
        _units=n.length();
    }

    public void setOngoing(boolean bool){
        _isOngoing = bool;
    }

    //show type|idCommunication|idSender|idReceiver|units|price|status
    abstract public String showCommunication();
}