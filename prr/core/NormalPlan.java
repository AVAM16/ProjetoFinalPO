package prr.core;

public class NormalPlan extends TariffPlan{
 

    public NormalPlan(String name, Communication communication, Client client) {
        super(name, communication, client);
    }

    @Override
    public double getCostVideo() {
        getCommunication().setCost((double)getCommunication().getUnits()*30);
        getClient().addCommunicationsDept(getCommunication());
        return getCommunication().getUnits()*30;
    }

    @Override
    public double getCostCall() {
        getCommunication().setCost((double)getCommunication().getUnits()*20);
        getClient().addCommunicationsDept(getCommunication());
        return getCommunication().getUnits()*20;
    }

    @Override
    public double getCostText() {
        if(getCommunication().getUnits() < 50){
            getCommunication().setCost((double)10);
            getClient().addCommunicationsDept(getCommunication());
            return 10;
        }
        else if(50 <= getCommunication().getUnits() && getCommunication().getUnits() < 100 ){
            getCommunication().setCost((double)16);
            getClient().addCommunicationsDept(getCommunication());
            return 16;
        }
        else{
            getCommunication().setCost((double)getCommunication().getUnits()*2);
            getClient().addCommunicationsDept(getCommunication());
            return getCommunication().getUnits()*2;
        }
    }

}
