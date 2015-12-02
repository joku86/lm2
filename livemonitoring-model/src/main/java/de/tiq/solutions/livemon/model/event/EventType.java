package de.tiq.solutions.livemon.model.event;

public enum EventType {
    INFO("INFO"), WARNUNG("WARNUNG"), ALARM("ALARM");
    
    
    private EventType(String value){
    	this.value=value;
    }
    
    private final String value;

    public String getValue(){return value;}
    
   
    
   }