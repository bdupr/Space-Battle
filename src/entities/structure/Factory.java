package entities.structure ;

import util.* ; 
import world.World ;
import entities.agents.* ;
import gui.Textures;

public class Factory extends Structure{

    private int forge_tick ;   

    public Factory(Coordinates coordinates,World world,int team_id){
        super(coordinates, world,team_id,Textures.blueFactory,350,5,5) ;
        
        if(this.getTeamId() == 1 )
            sprite = Textures.redFactory ;

        forge_tick = -1 ;
    }

    public boolean forgeDelay(int current_tick,int delay){
        if(forge_tick == -1){
            return true ;
        }
        else if( current_tick > forge_tick + delay ){
            forge_tick = -1 ;
            return true ;
        }
        else 
            return false ;
    }

    public void forge(int current_tick){
        if(forgeDelay(current_tick,50) ){
            boolean base_pres = false ;
            int base_id = -1  ;
            int t_id  = -1  ;
            for(int i = 0 ; i < world.teams.size() ; i++){ 
                if(  i == this.getTeamId() ){
                    for(int j = 0 ; j < world.teams.get(i).size(); j++ )
                    if( world.teams.get(i).get(j) instanceof Base){
                        base_pres = true ;
                        base_id = j ;
                        t_id  = i ;
                    }
                }
            }
            if(base_pres){
                
                if( ((Base)(world.teams.get(t_id).get(base_id))).getCrystalStock() > 300 && ((Base)(world.teams.get(t_id).get(base_id))).getEnergyStock() > 400){
                    double rand = Math.random() ;
                    if( rand > 0.5 ){ 
                        Soldier s = new Soldier(this.coordinates.clone(),world,getTeamId()) ;
                        world.teams_buffer.get( getTeamId()).add(s) ;
                        ((Base)(world.teams.get(t_id).get(base_id))).useCrystal( s.crystal_cost) ;
                        ((Base)(world.teams.get(t_id).get(base_id))).useEnergy( s.energy_cost) ;
                    }
                    else if( rand < 0.5){
                        ShieldSoldier ss = new ShieldSoldier(this.coordinates.clone(),world,getTeamId()) ;
                        world.teams_buffer.get( getTeamId()).add(ss) ;
                        ((Base)(world.teams.get(t_id).get(base_id))).useCrystal( ss.crystal_cost) ;
                        ((Base)(world.teams.get(t_id).get(base_id))).useEnergy( ss.energy_cost) ;
                    }
                    forge_tick = current_tick ; 
                }
                else if ( ((Base)(world.teams.get(t_id).get(base_id))).getCrystalStock() > 50 && ((Base)(world.teams.get(t_id).get(base_id))).getEnergyStock() > 40){ 
                    Soldier s = new Soldier(this.coordinates.clone(),world,getTeamId()) ;
                    world.teams_buffer.get( getTeamId()).add(s) ;
                    ((Base)(world.teams.get(t_id).get(base_id))).useCrystal( s.crystal_cost) ;
                    ((Base)(world.teams.get(t_id).get(base_id))).useEnergy( s.energy_cost) ;
                    forge_tick = current_tick ;
                }
            
            }
        }
    }
}
