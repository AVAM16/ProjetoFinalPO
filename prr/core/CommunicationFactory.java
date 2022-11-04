package prr.core;

public class CommunicationFactory {
  public InteractiveCommunication createCommunication(String communicationType, Terminal origin, Terminal destiny){   
    if(communicationType.equals("VOICE")) {  
      return new VoiceCommunication(origin, destiny);  
    } else {  
      return new VideoCommunication(origin, destiny);  
    }    
  }
}
