package prr.core;

public class PlatinumPlan extends TariffPlan{

    public PlatinumPlan(Communication communication, Client client) {
        super(communication, client);
    }

    @Override
    public double getCostVideo() {
        double cost = getCommunication().getUnits()*10;
        if(terminalsAreFriends(getCommunication().getOrigin(), getCommunication().getDestination())){
            cost=cost*0.5;
        }
        getCommunication().setCost((double)cost);
        getClient().addCommunicationsDept(getCommunication());
        return cost;
    }

    @Override
    public double getCostCall() {
        double cost = getCommunication().getUnits()*10;
        if(terminalsAreFriends(getCommunication().getOrigin(), getCommunication().getDestination())){
            cost=cost*0.5;
        }
        getCommunication().setCost((double)cost);
        getClient().addCommunicationsDept(getCommunication());
        return cost;
    }

    @Override
    public double getCostText() {
        double cost;
        if(getCommunication().getUnits() < 50){
            getCommunication().setCost((double)0);
            getClient().addCommunicationsDept(getCommunication());
            return 0;
        }
        else if(50 <= getCommunication().getUnits() && getCommunication().getUnits() < 100 ){
            cost = (double)4;
            if(terminalsAreFriends(getCommunication().getOrigin(), getCommunication().getDestination())){
                cost=cost*0.5;
            }
            getCommunication().setCost((double)cost);
            getClient().addCommunicationsDept(getCommunication());
            return cost;
        }
        else{
            cost = (double)4;
            if(terminalsAreFriends(getCommunication().getOrigin(), getCommunication().getDestination())){
                cost=cost*0.5;
            }
            getCommunication().setCost((double)cost);
            getClient().addCommunicationsDept(getCommunication());
            return cost;
        }
    }
    
}
