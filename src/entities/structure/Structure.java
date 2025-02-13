package entities.structure ;

import world.World ;
import entities.* ;
import util.* ; 

public abstract class Structure extends Entity{
    
    private static int structure_number = 0 ;
    protected final int s_id ;
    
    protected World world ;
    private int team_id ;

    public int health ;
    public int crystal_cost ;
    public int energy_cost ;
    
    public Structure(Coordinates _coordinates,World _world,int _team_id, int _health, int _crystal_cost , int _energy_cost){
        super(_coordinates) ;

        structure_number++ ;
        s_id = structure_number ;

        world = _world ;
        team_id = _team_id ;

        health = _health ;
        crystal_cost = _crystal_cost ;
        energy_cost = _energy_cost ;
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