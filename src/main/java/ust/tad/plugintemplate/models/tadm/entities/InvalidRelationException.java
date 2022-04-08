package ust.tad.plugintemplate.models.tadm.entities;

public class InvalidRelationException extends Exception{
    public InvalidRelationException(String errorMessage) {
        super(errorMessage);
    }
}
