package entities.structure ;

import world.World ;
import entities.* ;
import util.* ; 

public abstract class Structure extends Entity{
    
    private static int structure_number = 0 ;
    protected final int s_id ;
    
    protected World world ;
    protected int team_id ;

    public int health ;
    public int crystal_cost ;
    public int energy_cost ;
    
    public Structure(Coordinates coordinates,World world,int team_id,int sprite, int health, int crystal_cost , int energy_cost){
        super(coordinates,sprite) ;

        structure_number++ ;
        s_id = structure_number ;

        this.world = world ;
        this.team_id = team_id ;

        this.health = health ;
        this.crystal_cost = crystal_cost ;
        this.energy_cost = energy_cost ;
    }

    @Override
    public String toString(){ return super.toString() + " Structure:" + s_id ; }
    public int getSid(){ return s_id ; }
    public int getTeamId(){ return team_id ; }

    public boolean isDestroyed(){
        return health <= 0 ;
    }

    public boolean autourDe(int dist, Coordinates coord){
        if( coord.getX() >= this.coordinates.getX() && coord.getX() <= this.coordinates.getX() + dist)
            if (coord.getY() >= this.coordinates.getY() && coord.getY() <= this.coordinates.getY() + dist)
                return true ;
        return false ; 
    }

}
