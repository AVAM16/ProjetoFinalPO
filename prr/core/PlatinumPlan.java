package prr.core;

public class PlatinumPlan extends TariffPlan{

    public PlatinumPlan(String name, Communication communication, Client client) {
        super(name, communication, client);
    }

    @Override
    public double getCostVideo() {
        getCommunication().setCost((double)getCommunication().getUnits()*10);
        getClient().addCommunicationsDept(getCommunication());
        return getCommunication().getUnits()*10;
    }

    @Override
    public double getCostCall() {
        getCommunication().setCost((double)getCommunication().getUnits()*10);
        getClient().addCommunicationsDept(getCommunication());
        return getCommunication().getUnits()*10;
    }

    @Override
    public double getCostText() {
        if(getCommunication().getUnits() < 50){
            getCommunication().setCost((double)0);
            getClient().addCommunicationsDept(getCommunication());
            return 0;
        }
        else if(50 <= getCommunication().getUnits() && getCommunication().getUnits() < 100 ){
            getCommunication().setCost((double)4);
            getClient().addCommunicationsDept(getCommunication());
            return 4;
        }
        else{
            getCommunication().setCost((double)4);
            getClient().addCommunicationsDept(getCommunication());
            return 4;
        }
    }
    
}
