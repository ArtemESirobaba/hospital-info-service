package net.kickit.hospitalinfoservice.exception;

public class FacilityNotFoundException extends RuntimeException {
    public FacilityNotFoundException(String message){
        super(message);
    }
}
