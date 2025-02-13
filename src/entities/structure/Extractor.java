package entities.structure ;

import util.* ; 
import world.World ;

public class Extractor extends Structure{

    public int sprite ;
    
    private int extract_tick ;     

    public Extractor(Coordinates _coordinates, World _world,int _team_id){
        super(_coordinates, _world, _team_id,200,5,5) ;
        
        sprite = 34 ;
        if(this.getTeamId() == 1 )
            sprite = 38 ;

        extract_tick = - 1 ; 
    }

    public boolean extractDelay(int current_tick, int delay){
        if(extract_tick == -1){
            return true ;
        }
        else if( current_tick > extract_tick + delay ){
            extract_tick = -1 ;
            return true ;
        }
        else 
            return false ;
    }

    public void extract(int current_tick){ // extrait de l'énergie et la stock dans sa base 
         
        if( extractDelay(current_tick,20) ){
            if( world.teams.get(this.getTeamId()).get(0) instanceof Base ){    
                ((Base)(world.teams.get(this.getTeamId()).get(0))).addEnergy(5) ; // La base se trouve au premier indice de son équipe 
                //System.out.println(((Base)(world.teams.get(this.getTeamId()).get(0))).getEnergyStock()) ;
                extract_tick = current_tick ;
            }
        }
    }

}