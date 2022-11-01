package prr.core;

import java.io.Serializable;

abstract public class Communication implements Serializable{
    private static final long serialVersionUID = 202208091753L;
    private int _id;
    private Terminal _terminalOrigin;
    private Terminal _terminalDestination;
    private double _cost;
    private boolean _isOngoing;
    private CommunicationType _type;
    private int _units; //units e o os minutos os a lenght da mensagem

    public Communication(Terminal terminalOrigin,Terminal terminalDestination,CommunicationType type){
        this._terminalOrigin = terminalOrigin;
        this._terminalDestination = terminalDestination;
        this._type=type;
        this._cost=0;
        this._units=0;
    }

    //gets

    public int getID(){
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

    public int getCostDisplay(){
        return (int)Math.round(_cost);
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

    public void setId(int id){
        this._id = id;
    }

    public void setCost(Double cost){
        this._cost = cost;
    }

    public void setUnits(int n){
        _units=n;
    }

    public void setOngoing(boolean bool){
        _isOngoing = bool;
    }

    //CommunicationType
    public CommunicationType setCommunicationType(){
        return _type;
    }

    //show type|idCommunication|idSender|idReceiver|units|price|status
    public String showCommunication(){
       String com = String.format("%s|%d|%s|%s|%d|%d|%s",getType(),getID(),_terminalOrigin.getID(),
       _terminalDestination.getID(),getUnits(),getCostDisplay(),getStatus());

        return com;
    }

    public void iterateID(){
        _id++;
    }
}