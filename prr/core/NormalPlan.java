package prr.core;

public class NormalPlan extends TariffPlan{
 

    public NormalPlan(Communication communication, Client client) {
        super(communication, client);
    }

    @Override
    public double getCostVideo() {
        double cost = getCommunication().getUnits()*30;
        if(terminalsAreFriends(getCommunication().getOrigin(), getCommunication().getDestination())){
            cost=cost*0.5;
        }
        getCommunication().setCost((double)cost);
        getClient().addCommunicationsDept(getCommunication());
        return cost;
    }

    @Override
    public double getCostCall() {
        double cost = getCommunication().getUnits()*20;
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
            cost = (double)10;
            if(terminalsAreFriends(getCommunication().getOrigin(), getCommunication().getDestination())){
                cost=cost*0.5;
            }
            getCommunication().setCost((double)cost);
            getClient().addCommunicationsDept(getCommunication());
            return cost;
        }
        else if(50 <= getCommunication().getUnits() && getCommunication().getUnits() < 100 ){
            cost = (double)16;
            if(terminalsAreFriends(getCommunication().getOrigin(), getCommunication().getDestination())){
                cost=cost*0.5;
            }
            getCommunication().setCost(cost);
            getClient().addCommunicationsDept(getCommunication());
            return cost;
        }
        else{
            cost = (double)getCommunication().getUnits()*2;
            if(terminalsAreFriends(getCommunication().getOrigin(), getCommunication().getDestination())){
                cost=cost*0.5;
            }
            getCommunication().setCost((double)cost);
            getClient().addCommunicationsDept(getCommunication());
            return cost;
        }
    }

}
