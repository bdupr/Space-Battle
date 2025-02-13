package entities.agents ;

import entities.* ;
import entities.structure.* ;
import util.* ;
import world.World ;

public class Soldier extends Agent{

    private Entity target ;
    private int detection_range ;
    private int shooting_tick ;

    private int team_color ;

    public Soldier(Coordinates _coordinates, World _world,int _team_id){
        super(_coordinates, _world,_team_id,0, 100,5,5) ;
        
        target = null ; 
        detection_range = 15 ;

        team_color = 0 ;
        if(this.getTeamId() == 1 )
            team_color = 8 ;

        shooting_tick = -1 ;
    }

    public Entity getTarget(){ return target ; }
    public int getDetectionRange(){ return detection_range ; }

    public void setSprite(){ // sets the orientation of the agent as well as its direction
        //setting the direction based on an int makes it easier to find the corresponding sprite in SpriteDisplay without parsing an array to compare coordinates         
        //Will probably change in the future
        
        if(direction.equals(new Coordinates(1,0)))
            sprite = 0 + team_color;
        else if (direction.equals(new Coordinates(1,1)))
            sprite = 1 + team_color;
        else if (direction.equals(new Coordinates(0,1)))
            sprite = 2 + team_color;
        else if (direction.equals(new Coordinates(-1,1)))
            sprite = 3 + team_color;
        else if (direction.equals(new Coordinates(-1,0)))
            sprite = 4 + team_color;
        else if (direction.equals(new Coordinates(-1,-1)))
            sprite = 5 + team_color;
        else if (direction.equals(new Coordinates(0,-1)))
            sprite = 6 + team_color;
        else 
            sprite = 7 + team_color;

    }

    public void move(){
        directionInit() ;
        setSprite() ;
        moveInDirection() ;
    }

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
            for(int i = 0 ; i < world.teams.size() ; i++){ // vérifie toutes les équipes 
                if(  i != this.getTeamId() ){ // Si l'équipe n'est pas la sienne 
                    for(Entity e : world.teams.get(i) ){
                        if( e instanceof Agent ){    
                            Agent a = ((Agent)(e)) ;                    
                            if(a.getAid() != a_id ){
                                if(buffer != null){
                                    if( coordinates.distanceFrom(a.getCoordinates()) < coordinates.distanceFrom(buffer.getCoordinates()) )
                                        buffer = a ;
                                }
                                else{
                                    if( coordinates.distanceFrom(a.getCoordinates()) < detection_range )
                                        buffer = a ;
                                }
                            }
                        }
                        else if(e instanceof Structure){
                            Structure s = ((Structure)(e)) ;
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
            target = buffer ;
            if(target != null)
                System.out.println(this + " targets " + target);
        }
    }

    public boolean shotDelay(int current_tick, int delay){
        if(shooting_tick == -1){
            return true ;
        }
        else if( current_tick > shooting_tick + delay ){
            shooting_tick = -1 ;
            return true ;
        }
        else 
            return false ;
    }

    public void shoot(int current_tick){ // tire un projectile sur sa cible 
        if(target != null && shotDelay(current_tick,10)){
            world.teams_buffer.get(this.getTeamId()).add(new Projectile(this,this.getTeamId(), 20, 4));
            shooting_tick = current_tick ;
        }
    }

}