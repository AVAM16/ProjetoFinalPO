package prr.core;

public class GoldPlan extends TariffPlan{
    private Client _client;
    private Communication _communication;

    public GoldPlan(String name, Communication communication, Client client) {
        super(name, communication, client);
    }

    @Override
    protected double getCostVideo() {
        _communication.setCost((double)_communication.getUnits()*20);
        return _communication.getUnits()*20;
    }

    @Override
    protected double getCostCall() {
        _communication.setCost((double)_communication.getUnits()*10);
        return _communication.getUnits()*10;
    }

    @Override
    protected double getCostText() {
        if(_communication.getUnits() < 50){
            _communication.setCost((double)10);
            return 10;
        }
        else if(50 <= _communication.getUnits() && _communication.getUnits() < 100 ){
            _communication.setCost((double)10);
            return 10;
        }
        else{
            _communication.setCost((double)_communication.getUnits()*2);
            return _communication.getUnits()*2;
        }
    }
}