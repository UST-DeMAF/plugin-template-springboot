package ust.tad.plugintemplate.models.tadm.entities;

public class InvalidPropertyValueException extends Exception{
    public InvalidPropertyValueException(String errorMessage) {
        super(errorMessage);
    }    
}
