package entities.structure ;

import util.* ; 
import world.World ;
import entities.agents.* ;

public class Base extends Structure{
    public int sprite ;    

    private int crystal_stock ;
    private int energy_stock ;

    private int eng_tick ;
    
    public Base(Coordinates _coordinates, World _world, int _team_id){
        super(_coordinates,_world,_team_id,700,0,0) ;
        
        sprite = 32 ; // Décide du sprite selon l'équipe
        if(this.getTeamId() == 1 )
            sprite = 36 ;

        crystal_stock = 0 ;
        energy_stock = 0 ;

        eng_tick = - 1 ; 
    }

    public int getCrystalStock(){ return crystal_stock ;}
    public int getEnergyStock(){ return energy_stock ;}

    
    public void addCrystal(int quant){ crystal_stock += quant ; }
    public void addEnergy(int quant){ energy_stock += quant ; }

    public void useCrystal(int quant){
        if(crystal_stock - quant >= 0) 
            crystal_stock -= quant ;
    }
    public void useEnergy(int quant){
        if(energy_stock - quant >= 0) 
            energy_stock -= quant ;
    }
    
    public boolean engDelay(int current_tick,int delay){
        if(eng_tick == -1){
            return true ;
        }
        else if( current_tick > eng_tick + delay ){
            eng_tick = -1 ;
            return true ;
        }
        else 
            return false ;
    }

    public void creeEng(int current_tick){
        if(engDelay(current_tick,400) ){
            if( crystal_stock > 20 && energy_stock > 20){
                if(world.teams.get(this.getTeamId()).get(0) instanceof Base){    
                    Base base =  ((Base)(world.teams_buffer.get( this.getTeamId()).get(0))) ;
                    Engineer eng = new Engineer(this.coordinates.clone(),world,getTeamId()) ;
                    world.teams_buffer.get( getTeamId() ).add(eng) ;
                    base.useCrystal( eng.crystal_cost) ;
                    base.useEnergy( eng.energy_cost) ;
                    eng_tick = current_tick ;
                }
            }
        }
    }
}