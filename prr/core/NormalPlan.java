package prr.core;

public class NormalPlan extends TariffPlan{
 

    public NormalPlan(String name, Communication communication, Client client) {
        super(name, communication, client);
    }

    @Override
    public double getCostVideo() {
        double cost = getCommunication().getUnits()*30;
        if(getCommunication().isFriends()){
            cost=cost*0.5;
        }
        getCommunication().setCost((double)cost);
        getClient().addCommunicationsDept(getCommunication());
        return cost;
    }

    @Override
    public double getCostCall() {
        double cost = getCommunication().getUnits()*20;
        if(getCommunication().isFriends()){
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
            if(getCommunication().isFriends()){
                cost=cost*0.5;
            }
            getCommunication().setCost((double)10);
            getClient().addCommunicationsDept(getCommunication());
            return cost;
        }
        else if(50 <= getCommunication().getUnits() && getCommunication().getUnits() < 100 ){
            cost = (double)16;
            if(getCommunication().isFriends()){
                cost=cost*0.5;
            }
            getCommunication().setCost(cost);
            getClient().addCommunicationsDept(getCommunication());
            return cost;
        }
        else{
            cost = (double)getCommunication().getUnits()*2;
            if(getCommunication().isFriends()){
                cost=cost*0.5;
            }
            getCommunication().setCost((double)cost);
            getClient().addCommunicationsDept(getCommunication());
            return cost;
        }
    }

}
