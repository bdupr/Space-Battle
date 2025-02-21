package entities.structure ;

import util.* ; 
import world.World ;
import entities.* ;
import entities.agents.* ;
import gui.Textures ;

public class Turret extends Structure{
    
    private Entity target ;
    private int detection_range ;
    private int shooting_tick ; 

    public Turret(Coordinates coordinates,World world,int team_id){
        super(coordinates, world,team_id,Textures.blueTurret, 175,5,5) ;
       
        if(this.getTeamId() == 1 )
            sprite = Textures.redTurret ;

        target = null ;
        detection_range = 15 ;
        shooting_tick = -1 ;

    }

    public Entity getTarget(){ return target ; }
    public int getDetectionRange(){ return detection_range ; }

    public void targeting(){ // sets target as the closest enemy agent at the time. Stops targeting only when enemy is out of range or dead (TO BE DONE)
        if(target != null ){
            if( coordinates.distanceFrom(target.getCoordinates()) > detection_range )
                target = null ;   
            else if( (target instanceof Agent) && ((Agent)(target)).isDead() )
                target = null ;
            else if((target instanceof Structure) && ((Structure)(target)).isDestroyed())
                target = null ;
        }
        else{
            Entity buffer = null ;
            for(int i = 0 ; i < world.teams.size() ; i++){ // parses all teams 
                if(  i != this.getTeamId() ){ // If team is not its own  
                    for(Entity e : world.teams.get(i) ){
                        if( e instanceof Agent ){    
                            Agent a = ((Agent)(e)) ;                    
                            if(buffer != null){
                                if( coordinates.distanceFrom(a.getCoordinates()) < coordinates.distanceFrom(buffer.getCoordinates()) )
                                    buffer = a ;
                            }
                            else{
                                if( coordinates.distanceFrom(a.getCoordinates()) < detection_range )
                                    buffer = a ;
                            }
                        }
                        else if(e instanceof Structure){
                            Structure s = ((Structure)(e)) ;
                            if(s.getSid() != s_id ){
                                if(buffer != null){
                                    if( coordinates.distanceFrom(s.getCoordinates()) < coordinates.distanceFrom(buffer.getCoordinates()) )
                                        buffer = s ;
                                }
                                else{
                                    if( coordinates.distanceFrom(s.getCoordinates()) < detection_range )
                                        buffer = s ;
                                }
                            }
                        }
                    }
                }
            }
            target = buffer ;
            if(target != null)
                System.out.println(this + " targets " + target);
        }
    }

    public boolean shotDelay(int current_tick){
        if(shooting_tick == -1){
            return true ;
        }
        else if( current_tick > shooting_tick + 10 ){
            shooting_tick = -1 ;
            return true ;
        }
        else 
            return false ;
    }

    public void shoot(int current_tick){ // shoots a projectile on its target    
        if(target != null && shotDelay(current_tick)){
            world.teams_buffer.get(this.getTeamId()).add(new Projectile(this,this.getTeamId(), 30, 4));
            shooting_tick = current_tick ;
        }
    }

}
