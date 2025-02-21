package entities ;

import util.* ;
import entities.agents.* ;
import entities.structure.* ;
import gui.Textures ;

public class Projectile extends Entity{

    private static int projectile_number = 0 ;
    private final int p_id ;
    
    private int team_id ;

    private Entity origin ;
    private Entity target ;   
     
    private int damage ;
    private int speed ; 
    private boolean expired ;
    
    public Projectile(Entity origin,int team_id, int damage, int speed){
        
        super(origin.getCoordinates().clone(),Textures.projectile) ;
    
        projectile_number++ ;
        p_id = projectile_number ;
        
        this.origin = origin ; 
        this.team_id = team_id ;
        initTarget() ;
        
        this.damage = damage ;
        this.speed = speed ; 
        expired = false ;
    }

    @Override
    public String toString(){ return super.toString() + " Projectile:" + p_id ; }

    public int getPid(){ return p_id ; }
    public Entity getTarget(){ return target ; }
    public int getDamage(){ return damage ; }
    public int getSpeed(){ return speed ; } 
    public boolean isExpired(){ return expired ; }

    private void initTarget(){
        if(origin instanceof Agent ){    
            if( origin instanceof ShieldSoldier){
                target = ((ShieldSoldier)(origin)).getTarget() ; 
            }
            else{
                target = ((Soldier)(origin)).getTarget() ; 
            }
        }
        else if(origin instanceof Structure ){
            target = ((Turret)(origin)).getTarget() ;
        }
    }
    
    public boolean hit(){ // checks if projectile hit target
        return coordinates.equals(target.getCoordinates()) ;
    }
    
    public void travel(){ 
        if(origin instanceof Agent){
            if(origin instanceof ShieldSoldier){
                ShieldSoldier org = ((ShieldSoldier)(origin)) ;
                travelLogic(org) ;
            }
            else{
                Soldier org = ((Soldier)(origin)) ;
                travelLogic(org) ;
            }
        }
        else if( origin instanceof Structure){
            Turret org = ((Turret)(origin)) ;
            travelLogic(org) ;
        }
    }

    private void travelLogic(Soldier org){
        // Block of code checks if the shooter is still targeting the same target 
        if( org.getTarget() == null ){ // If it doesn't have a target anymore projectile disapears 
            expired = true ;
        }
        else if( target instanceof Agent ){ // Case for Agent 
            if( org.getTarget() instanceof Agent){ // If traget is different or dead projectile disapears
                if(((Agent)(org.getTarget())).getAid() != ((Agent)(target)).getAid() || ((Agent)(target)).isDead() ){
                    expired = true ; 
                }
            }
            else{ // If traget type is different projectile disapears
                expired = true ;
            }
        }
        else if( target instanceof Structure ){ // Case for Structure 
            if( org.getTarget() instanceof Structure){ // If traget is different or dead projectile disapears
                if( ((Structure)(org.getTarget())).getSid() != ((Structure)(target)).getSid() || ((Structure)(target)).isDestroyed() ){
                    expired = true ; 
                }
            }
            else{ // If traget type is different projectile disapears
                expired = true ;
            }
        }
        
        // if speed is 0, projectile reaches target, else its journey is divided by the value of speed  
        if( !coordinates.equals(target.getCoordinates())){
            if( speed == 0 ){
                coordinates.setCoordinates(target.getCoordinates()) ;
                if ( hit() ){ // Deals damage to target if hit connects
                    expired = true ;   
                    if(target instanceof Structure)
                        ((Structure)(target)).health -= damage ;
                    else if(target instanceof Agent){
                        if(target instanceof ShieldSoldier){
                            ShieldSoldier ss = ((ShieldSoldier)(target)) ;
                            if( ss.power == 0 )
                                ss.health -= damage ;
                            else if( ss.power - damage <= 0)
                                ss.power = 0 ;
                            else
                                ss.power -= damage  ;
                        }
                        else 
                            ((Agent)(target)).health -= damage ;
                    }
                }
            } else {
                Vector vector = new Vector(coordinates,target.getCoordinates()) ;
                vector.div(speed) ;
                speed-- ;

                coordinates.add(vector) ;
                
                if ( hit() ){ // Deals damage to target if hit connects
                    expired = true ;   
                    if(target instanceof Structure)
                        ((Structure)(target)).health -= damage ;
                    else if(target instanceof Agent){
                        if(target instanceof ShieldSoldier){
                            ShieldSoldier ss = ((ShieldSoldier)(target)) ;
                            if( ss.power == 0 )
                                ss.health -= damage ;
                            else if( ss.power - damage <= 0)
                                ss.power = 0 ;
                            else
                                ss.power -= damage  ;
                        }
                        else 
                            ((Agent)(target)).health -= damage ;
                    }
                }
            }
        }
        else {
            expired = true ;
        }
    }    
    //---------------------------------------------------------------------------------------------------------------------
    private void travelLogic(ShieldSoldier org){
        // Block of code checks if the shooter changed target since the shot 
        if( org.getTarget() == null ){ // If it doesn't have a target anymore projectile disapears 
            expired = true ;
        }
        else if( target instanceof Agent ){ // Case for Agent 
            if( org.getTarget() instanceof Agent){ // If traget is different or dead projectile disapears
                if( ((Agent)(org.getTarget())).getAid() != ((Agent)(target)).getAid() || ((Agent)(target)).isDead() ){
                    expired = true ; 
                }
            }
            else{ // If traget type is different projectile disapears
                expired = true ;
            }
        }
        else if( target instanceof Structure ){ // Case for Structure 
            if( org.getTarget() instanceof Structure){ // If traget is different or dead projectile disapears
                if( ((Structure)(org.getTarget())).getSid() != ((Structure)(target)).getSid() || ((Structure)(target)).isDestroyed() ){
                    expired = true ; 
                }
            }
            else{  // If traget type is different projectile disapears
                expired = true ;
            }
        }
    
        // if speed is 0, projectile reaches target, else its journey is divided by the value of speed  
        if( !coordinates.equals(target.getCoordinates())){
            if( speed == 0 ){
                coordinates.setCoordinates(target.getCoordinates()) ;
                if ( hit() ){ // Deals damage to target if hit connects
                    expired = true ;   
                    if(target instanceof Structure)
                        ((Structure)(target)).health -= damage ;
                    else if(target instanceof Agent){
                        if(target instanceof ShieldSoldier){
                            ShieldSoldier ss = ((ShieldSoldier)(target)) ;
                            if( ss.power == 0 )
                                ss.health -= damage ;
                            else if( ss.power - damage <= 0)
                                ss.power = 0 ;
                            else
                                ss.power -= damage  ;
                        }
                        else 
                            ((Agent)(target)).health -= damage ;
                    }
                }  
            } else {
                Vector vector = new Vector(coordinates,target.getCoordinates()) ;
                vector.div(speed) ;
                speed-- ;

                coordinates.add(vector) ;

                if ( hit() ){ // Deals damage to target if hit connects
                    expired = true ;   
                    if(target instanceof Structure)
                        ((Structure)(target)).health -= damage ;
                    else if(target instanceof Agent){
                        if(target instanceof ShieldSoldier){
                            ShieldSoldier ss = ((ShieldSoldier)(target)) ;
                            if( ss.power == 0 )
                                ss.health -= damage ;
                            else if( ss.power - damage <= 0)
                                ss.power = 0 ;
                            else
                                ss.power -= damage  ;
                        }
                        else 
                            ((Agent)(target)).health -= damage ;
                    }
                }  
            }
        }
        else {
            expired = true ;
        }
    }
    //---------------------------------------------------------------------------------------------------------------------
    private void travelLogic(Turret org){
        // Block of code checks if the shooter changed target since the shot 
        if( org.getTarget() == null ){ // If it doesn't have a target anymore projectile disapears 
            expired = true ;
        }
        else if( target instanceof Agent ){ // Case for Agent 
            if( org.getTarget() instanceof Agent){ // If traget is different or dead projectile disapears
                if( ((Agent)(org.getTarget())).getAid() != ((Agent)(target)).getAid() || ((Agent)(target)).isDead() ){
                    expired = true ; 
                }
            }
            else{ // If traget type is different projectile disapears
                expired = true ;
            }
        }
        else if( target instanceof Structure ){ // Case for Structure 
            if( org.getTarget() instanceof Structure){ // If traget is different or dead projectile disapears
                if( ((Structure)(org.getTarget())).getSid() != ((Structure)(target)).getSid() || ((Structure)(target)).isDestroyed() ){
                    expired = true ; 
                }
            }
            else{ // If traget type is different projectile disapears
                expired = true ;
            }
        }

        // if speed is 0, projectile reaches target, else its journey is divided by the value of speed  
        if( !coordinates.equals(target.getCoordinates())){
            if( speed == 0 ){
                coordinates.setCoordinates(target.getCoordinates()) ;
                if ( hit() ){ // Deals damage to target if hit connects
                    expired = true ;   
                    if(target instanceof Structure)
                        ((Structure)(target)).health -= damage ;
                    else if(target instanceof Agent){
                        if(target instanceof ShieldSoldier){
                            ShieldSoldier ss = ((ShieldSoldier)(target)) ;
                            if( ss.power == 0 )
                                ss.health -= damage ;
                            else if( ss.power - damage <= 0)
                                ss.power = 0 ;
                            else
                                ss.power -= damage  ;
                        }
                        else 
                            ((Agent)(target)).health -= damage ;
                    }
                } 
            } else {
                Vector vector = new Vector(coordinates,target.getCoordinates()) ;
                vector.div(speed) ;
                speed-- ;

                coordinates.add(vector) ;

                if ( hit() ){ // Deals damage to target if hit connects
                    expired = true ;   
                    if(target instanceof Structure)
                        ((Structure)(target)).health -= damage ;
                    else if(target instanceof Agent){
                        if(target instanceof ShieldSoldier){
                            ShieldSoldier ss = ((ShieldSoldier)(target)) ;
                            if( ss.power == 0 )
                                ss.health -= damage ;
                            else if( ss.power - damage <= 0)
                                ss.power = 0 ;
                            else
                                ss.power -= damage  ;
                        }
                        else 
                            ((Agent)(target)).health -= damage ;
                    }
                }    
            }
        }
        else {
            expired = true ;
        }
    }        

}
