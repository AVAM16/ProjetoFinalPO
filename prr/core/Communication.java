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

    public Communication(String id,Terminal terminalOrigin,Terminal terminalDestination){
        this._id = id;
        this._terminalOrigin = terminalOrigin;
        this._terminalDestination = terminalDestination;
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

    public boolean isOngoing(){
        return this._isOngoing;
    }


    //sets

    public void setCost(Double cost){
        this._cost = cost;
    }
}