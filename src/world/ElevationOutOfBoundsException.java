package world ;

public class ElevationOutOfBoundsException extends Exception{
    public ElevationOutOfBoundsException(int elevation_bound){
        super("Elevation boundary set above allowed limit of : "+ elevation_bound);
    }
}