package prr.core;

abstract public class Communication {
    private String _id;
    private Terminal _terminalOrigin;
    private Terminal _terminalDestination;
    private double _cost;
    private boolean _isOngoing;

    public Communication(String id,Terminal terminalOrigin,Terminal terminalDestination){
        this._id = id;
        this._terminalOrigin = terminalOrigin;
        this._terminalDestination = terminalDestination;
    }

    //gets

    public String getID(){
        return this._id;
    }

    public Terminal origin(){
        return this._terminalOrigin;
    }

    public Terminal destination(){
        return this._terminalDestination;
    }

    public boolean isOngoing(){
        return this._isOngoing;
    }

    //sets

    public void setCost(Double cost){
        this._cost = cost;
    }
}