package world ;

public class UninitialisedElevationException extends Exception{
    public UninitialisedElevationException(){
        super("Elevation array is empty") ;
    }
}