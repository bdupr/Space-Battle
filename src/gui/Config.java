package gui ; 

import world.* ;
import entities.* ;

/** Definition :
 * 
 * This class creates a default program configuration that can be mofified through its methods.
 */

public class Config{
    
    public World world ;
    public DefaultPlanet planet ;
    public DefaultMoon moon ;

    public final static int WORLD_LENGTH = 120 ;  
    public final static int WORLD_HEIGHT = 100 ;
    
    public final static int MIN_PLANET_RADIUS = 30;
    public final static int MAX_PLANET_RADIUS =  ( WORLD_HEIGHT / 2 ) - 1 ; 
     
    public final static int MIN_SIZE_MOON = 10; 
    public final static int MAX_SIZE_MOON = MIN_PLANET_RADIUS ;    
    
    public int planet_radius ; 
    public int planet_rotation_frequency ; 
    
    public int moon_radius ;
    public int moon_distance_from_planet ;
    public int moon_orbit_frequency ;

    public Config(){

        // Default planet configuration  
        planet_radius  = 25 ; 
        planet_rotation_frequency = 10 ; 
        
        // Default moon configuration
        moon_radius = 5 ;
        moon_distance_from_planet = 10 ; 
        moon_orbit_frequency = 2 ;  

        // Default program configuration
        planet = new DefaultPlanet(planet_radius, WORLD_LENGTH/2, WORLD_HEIGHT/2, planet_rotation_frequency ) ;
        moon  = new DefaultMoon(planet, moon_radius, moon_distance_from_planet, moon_orbit_frequency ) ;

        world = new World(WORLD_LENGTH, WORLD_HEIGHT, planet, moon, 2) ;

    }

    // Configuration methods : 
    public void planetRadius(int new_radius){
        planet_radius = new_radius ;
    }

    public void planetRotationFrequency(int new_frequency){
        planet.setRotationFrequency(new_frequency) ;
    }

    public void moonRadius(int new_radius){
        moon_radius = new_radius ;
    }

    public void moonDistanceFromPlanet(int new_distance){
        moon_distance_from_planet = new_distance ;
    }

    public void moonOrbitFrequency(int new_frequency){
        moon.setOrbitFrequency(new_frequency) ;
    }

    public void modifyPlanet(){
        planet = new DefaultPlanet(planet_radius, WORLD_LENGTH/2, WORLD_HEIGHT/2, planet_rotation_frequency ) ;
    }
    public void modifyMoon(){
        moon  = new DefaultMoon(planet, moon_radius, moon_distance_from_planet, moon_orbit_frequency ) ;
    }
    
    // Confirm a new configuration 
    public void submit(){
        modifyPlanet() ;
        modifyMoon() ;
        world = new World(WORLD_LENGTH, WORLD_HEIGHT, planet, moon, 2) ;
    }

}
