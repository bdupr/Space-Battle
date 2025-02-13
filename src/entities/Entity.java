package entities ;
import util.* ;

public abstract class Entity{

    private static int entity_number = 0 ;
    private final int e_id ;
    
    //Utilisation de protected à la place de private pour la simplicité
    protected Coordinates coordinates ; 
    
    public Entity(Coordinates _coordinates){
        entity_number++ ;
        e_id = entity_number ;
        
        coordinates = _coordinates ;
    }

    @Override
    public String toString(){ return "Entity:"+e_id ;}

    public Coordinates getCoordinates(){ return coordinates ; }
    public int getEid(){ return e_id ; }
}