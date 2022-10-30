package prr.core;

public class PlatinumPlan extends TariffPlan{
    private Client _client;
    private Communication _communication;

    public PlatinumPlan(String name, Communication communication, Client client) {
        super(name, communication, client);
    }

    @Override
    protected double getCostVideo() {
        _communication.setCost((double)_communication.getUnits()*10);
        return _communication.getUnits()*10;
    }

    @Override
    protected double getCostCall() {
        _communication.setCost((double)_communication.getUnits()*10);
        return _communication.getUnits()*10;
    }

    @Override
    protected double getCostText() {
        if(_communication.getUnits() < 50){
            _communication.setCost((double)0);
            return 0;
        }
        else if(50 <= _communication.getUnits() && _communication.getUnits() < 100 ){
            _communication.setCost((double)4);
            return 4;
        }
        else{
            _communication.setCost((double)4);
            return 4;
        }
    }
    
}
