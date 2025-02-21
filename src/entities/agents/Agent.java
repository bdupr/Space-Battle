package entities.agents ;

import entities.* ;
import util.* ;
import world.World ;

/*
Targeting system could keep references to dead agents, remember to fix this issue in advance in case 
this does occur
*/
public abstract class Agent extends Entity{
    
    private static int agent_number = 0 ;
    protected final int a_id ;
    
    protected World world ;  
    private int team_id ;

    protected Coordinates destination ;
    protected Coordinates direction ; 
    
    public int health ;
    public int crystal_cost ;
    public int energy_cost ;
    
    public Agent(Coordinates coordinates, World world, int team_id , int sprite, int init_sprite, int health, int crystal_cost, int energy_cost){
        
        super(coordinates,sprite) ;
        
        agent_number++ ;
        a_id = agent_number ;
        
        this.world = world ;
        this.team_id = team_id ;
        sprite = init_sprite ; 

        destination = coordinates.clone() ;
        direction = new Coordinates(0,0) ; 
        
        this.health = health ;
        this.crystal_cost = crystal_cost ;
        this.energy_cost = energy_cost ;
    }

    @Override
    public String toString(){ return super.toString() + " Agent:" + a_id ; }
    
    public int getAgentNumber(){ return agent_number ; }
    public int getAid(){ return a_id ; }
    public int getTeamId(){ return team_id ; }
    public int getSprite(){ return sprite ; }

    public void initTravel(){ // initialise la destinatoin des agents à des coordonées au hazard
        if(coordinates.equals(destination))
            destination = new Coordinates((int)(Math.random() * world.getDx()), (int)(Math.random() * world.getDy())) ;
    }
    
    public void directionInit(){
        if(!coordinates.equals(destination)){
            Vector v = new Vector(coordinates,destination) ;
            v.norm() ;
            direction.setCoordinates(v.getVX(),v.getVY()) ;
        }
        else{
            direction.setCoordinates(0,0) ;
        }
    }

    public void moveInDirection(){ // verifie si la nouvelle position est dans le monde, ensuite déplace
        if( world.inBounds(coordinates.getX() + direction.getX(),coordinates.getY() + direction.getY()) ){
            coordinates.add(direction) ;
        }
    }

    public boolean isDead(){
        if(health <= 0){
            System.out.println(this.toString()+ " is dead") ;
            return true  ;
        }
        return false ;
    }
    
}
