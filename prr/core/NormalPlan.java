package prr.core;

public class NormalPlan extends TariffPlan{
    private Client _client;
    private Communication _communication;

    public NormalPlan(String name, Communication communication, Client client) {
        super(name, communication, client);
    }

    @Override
    public double getCostVideo() {
        _communication.setCost((double)_communication.getUnits()*30);
        _client.addCommunicationsDept(_communication);
        return _communication.getUnits()*30;
    }

    @Override
    public double getCostCall() {
        _communication.setCost((double)_communication.getUnits()*20);
        _client.addCommunicationsDept(_communication);
        return _communication.getUnits()*20;
    }

    @Override
    public double getCostText() {
        if(_communication.getUnits() < 50){
            _communication.setCost((double)10);
            _client.addCommunicationsDept(_communication);
            return 10;
        }
        else if(50 <= _communication.getUnits() && _communication.getUnits() < 100 ){
            _communication.setCost((double)16);
            _client.addCommunicationsDept(_communication);
            return 16;
        }
        else{
            _communication.setCost((double)_communication.getUnits()*2);
            _client.addCommunicationsDept(_communication);
            return _communication.getUnits()*2;
        }
    }

}
