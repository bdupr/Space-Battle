package entities.agents ;

import java.util.ArrayList ;

import entities.* ;
import entities.structure.* ;
import entities.ressources.* ;
import gui.Textures ;

import util.* ;
import world.World ;

public class Engineer extends Agent{
	
	private int team_color;
    public boolean in_task ;
    public int task_id ;
    public boolean has_crystal ;
    public int crystal_storage ; 

    public Ressource target_crystal ;
    
    public Engineer(Coordinates coordinates, World world, int team_id){
        super(coordinates, world,team_id,Textures.blueEngineerE,16,70,5,5) ;
	
	team_color = Textures.blueEngineerE ;
        if(this.getTeamId() == 1 ){
            	sprite = Textures.redEngineerE ;
        	team_color = Textures.redEngineerE ;
        }

        in_task = false ;
        task_id = 0 ;
        has_crystal = false ;
        crystal_storage = 0 ;

        target_crystal = null ;
    }

    public void setSprite(){ // sets the orientation of the agent as well as its direction
        //setting the direction based on an int makes it easier to find the corresponding sprite in SpriteDisplay without parsing an array to compare coordinates         
        //Will probably change in the future
        sprite = team_color ;
        if(direction.equals(new Coordinates(1,0)))
            sprite += 0;
        else if (direction.equals(new Coordinates(1,1)))
            sprite += 1;
        else if (direction.equals(new Coordinates(0,1)))
            sprite += 2 ;
        else if (direction.equals(new Coordinates(-1,1)))
            sprite += 3;
        else if (direction.equals(new Coordinates(-1,0)))
            sprite += 4;
        else if (direction.equals(new Coordinates(-1,-1)))
            sprite += 5;
        else if (direction.equals(new Coordinates(0,-1)))
            sprite += 6;
        else 
            sprite += 7;

    }

    public void move(){
        directionInit() ;
        setSprite() ;
        moveInDirection() ;
    }

    public boolean validBuild(Coordinates coord ){
        boolean res = (  coord.getX() != world.getDx() - 1 && coord.getY() != world.getDy() - 1 ) ;
        for( ArrayList<Entity> arr : world.teams ){ 
            for( Entity e : arr ){
                if( e instanceof Structure){
                    Structure strct = ((Structure)(e)) ;
                    if( coord.distanceFrom(strct.getCoordinates()) < 2){
                        res = false ;
                    }
                }
            }
        }
        return res ;
    }

    public void tempBuild(){
        
        if(validBuild(this.getCoordinates())){
            
            if(Math.random() > 0.9){
                
                if( world.world_grid[coordinates.getX()][coordinates.getY()][0] == 10 ){
                    world.teams_buffer.get(this.getTeamId()).add(new Extractor(new Coordinates(this.getCoordinates().getX(),this.getCoordinates().getY()),world,this.getTeamId() )) ;
                }
                else if(world.world_grid[coordinates.getX()][coordinates.getY()][0] == 11 || world.world_grid[coordinates.getX()][coordinates.getY()][0] == 12){
                    double var = Math.random() ;
                    if(var < 0.5){            
                        world.teams_buffer.get(this.getTeamId()).add(new Turret(new Coordinates(this.getCoordinates().getX(),this.getCoordinates().getY()),world,this.getTeamId() )) ;
                    }
                    else if( var > 0.5 ){
                        world.teams_buffer.get(this.getTeamId()).add(new Factory(new Coordinates(this.getCoordinates().getX(),this.getCoordinates().getY()),world,this.getTeamId() )) ;
                    }
                }

            }
        }
    }

    public void recolt(){
        if( world.teams.get(this.getTeamId()).get(0) instanceof Base ){        
            for(Ressource r : world.ressources){
                if( r instanceof Crystal){
                    if(r.getCoordinates().equals(coordinates)){
                        ((Base)(world.teams.get(this.getTeamId()).get(0))).addCrystal(20) ;
                    }

                }
                else if(r instanceof MoonCrystal){
                    if(r.getCoordinates().equals(coordinates)){
                        ((Base)(world.teams.get(this.getTeamId()).get(0))).addCrystal(50) ;
                    }
                }
            }
        }
    }

}
