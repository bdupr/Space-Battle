package entities.ressources ;

import util.Coordinates ;
import entities.Entity ;


public abstract class Ressource extends Entity{
    
    private static int ressource_number = 0 ;
    private final int r_id ;
    
    public int quantity ;
    public boolean is_visible ;
    
    public Ressource(Coordinates coordinates, int sprite, int quantity){
        super(coordinates,sprite) ;

        ressource_number++ ;
        r_id = ressource_number ;

        this.quantity = quantity ;
        is_visible = false ; 
    }

    @Override
    public String toString(){ return super.toString() + " Ressource:" + r_id ; }
    
    public int getRid(){ return r_id ; }
    public boolean getVisibility(){ return is_visible ; }
    public void setVisibility(boolean b){ is_visible = b ; }
}
