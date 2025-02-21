package world ;
import util.Coordinates ;
import util.Pair ;
import gui.Textures ;

import java.util.ArrayList ;

/** Description : 
 * 
 * This class initialises an instance of DefaultPlanet. As a class that extends CelestialBody it inherits all its variables and methods.
 * 
 * This class posses a parent planet around which it orbits. To do so it has an orbit_path array which conatins all the coordinates along
 * the moon's path of orbit sequentially.
 * 
 * The moon's initial position is set to be direclty to the right of its planet by default but can be set randomly with the randomInitPos()
 * method
 */

public class DefaultMoon extends CelestialBody{
    
    private static int nbMoons = 0 ;            // Number of instances of DefaultMoon
    private final int id ;                      // Id of this instance of DefaultMoon
    
    private DefaultPlanet parent_planet ;       // The parent planet of the moon 
    private ArrayList<Coordinates> orbit_path ; // The orbit path of the moon, represented by an array of coordinates
    private int distance_from_planet;           // The distance between the surfaces of the moon and its planet
    
    private int orbit_frequency ;               // Frequency for the orbit of the moon in the program
    private int orbit_tick ;                    // Timer for the orbit of the moon in the program    
    
    
    public DefaultMoon(DefaultPlanet parent_planet, int radius, int distance_from_planet, int orbit_frequency){
        // Initial position to the right of the planet
        super(radius, parent_planet.getCoordinates().getX() + parent_planet.getRadius() + distance_from_planet + radius , parent_planet.getCoordinates().getY());
        setSurfaceSprites() ;                   // Initialising the sprites for the planet's surface 
        
        this.parent_planet = parent_planet ;
        id = nbMoons ;                          // Moon's id
        nbMoons += 1 ;                          

        // --------------------------------------------------> modify this 
        this.distance_from_planet = distance_from_planet ; 

        this.orbit_frequency = orbit_frequency ;
        orbit_tick = -1 ;
        
        orbit_path = new ArrayList<Coordinates>() ;
        initOrbitPath() ;  

        randomInitPos() ;                       // Choose a random position for the moon                              
        
    }

    // Getters / Setters
    public int getId(){ return id ; }
    public void setOrbitFrequency(int new_frequency) { orbit_frequency = new_frequency ; }

    /**
     * Initialises the orbit_path array. Does so by drawing the orbit around the coordinates of the moon's planet and saving each point 
     * as a coordinate inside the array.
     * Since the Bresenham circle algorithm draws a circle in 8 diffrent arcs and some of them are drawn in opposite directions some 
     * Modifications are necessary before attributing the values to orbit_path  
     */
    private void initOrbitPath(){
         
        // Declaring and initialising the result array
        ArrayList<ArrayList<Coordinates>> arcs = new ArrayList<>() ;
        // Declaring the 8 arcs
        for (int i = 0 ; i < 8 ; i++ ){
            arcs.add(new ArrayList<Coordinates>()) ;
        }

        // Use of the Bresenham circle algorithm to draw the orbit's path divided into 8 diffrent arcs 
        int t1 = radius + parent_planet.getRadius() + distance_from_planet / 16 ;       // div 16 to make the circle diagonals les straight 
        int x = radius + parent_planet.getRadius() + distance_from_planet ;
        int y = 0 ;
        while(x >= y){
            // Add the new coordinates to the orbit path for each cycle
            arcs.get(0).add(new Coordinates(parent_planet.getCoordinates().getX()-x,parent_planet.getCoordinates().getY()-y));
            arcs.get(1).add(new Coordinates(parent_planet.getCoordinates().getX()-y,parent_planet.getCoordinates().getY()-x));
            arcs.get(2).add(new Coordinates(parent_planet.getCoordinates().getX()+y,parent_planet.getCoordinates().getY()-x));
            arcs.get(3).add(new Coordinates(parent_planet.getCoordinates().getX()+x,parent_planet.getCoordinates().getY()-y));
            arcs.get(4).add(new Coordinates(parent_planet.getCoordinates().getX()+x,parent_planet.getCoordinates().getY()+y));
            arcs.get(5).add(new Coordinates(parent_planet.getCoordinates().getX()+y,parent_planet.getCoordinates().getY()+x));
            arcs.get(6).add(new Coordinates(parent_planet.getCoordinates().getX()-y,parent_planet.getCoordinates().getY()+x));
            arcs.get(7).add(new Coordinates(parent_planet.getCoordinates().getX()-x,parent_planet.getCoordinates().getY()+y));

            y = y + 1 ;
            t1 = t1 + y ;
            int t2 = t1 - x ;
            
            if(t2 >= 0){
                t1 = t2 ;
                x = x - 1 ;
            }
        }
        
        // Add the arcs to orbit_path, flip every other arc
        for(int i = 0 ; i < arcs.size() ; i++){
            if( (i+1)%2 == 0){
                arcs.get(i).remove(0) ;                         // Trim every other arc so as to not have any repeat values
                arcs.get(i).remove(arcs.get(i).size() - 1 ) ;   // Otherwise the moon will get stuck on duplicate coordinates when orbiting
                orbit_path.addAll(reverseArc(arcs.get(i))) ;
            }
            else{
                orbit_path.addAll(arcs.get(i)) ;
            }
        }
    }

    /**
     * Reverses the order of an ArrayList of Coordinates
     * Used in the initOrbitPath() method
     */
    private ArrayList<Coordinates> reverseArc(ArrayList<Coordinates> arc ){
        ArrayList<Coordinates> result = new ArrayList<>() ;
        
        for(int i = arc.size() - 1 ; i >= 0; i--){
            result.add( arc.get(i)) ;
        }
        
        return result ;
    }
    
    /**
     * Determines if the frequency delay for an orbit step has been reached 
     * current_tick refers to the current tick of the program
     */
    private boolean orbitDelay(int current_tick){
        if(orbit_tick == -1){      // Initial value of tick at program launch
            return true ;
        }
        else if( current_tick > orbit_tick + orbit_frequency ){
            orbit_tick = -1 ;
            return true ;
        }
        else 
            return false ;
    }

    /**
     * Executes an orbit step with a frequency determined by orbitDelay and resets the delay after 
     * current_tick refers to the current tick of the program
     */
    public void orbit(int current_tick){
        if(orbitDelay(current_tick)){
            orbitLogic();
            orbit_tick = current_tick ;
        }
    } 

    /**
     * Finds the coordinates of the moon in the orbit path, then advances it by one
     */
    private void orbitLogic(){
        for( int j = 0; j < orbit_path.size() ; j++){
            if( getCoordinates().equals(orbit_path.get(j))){  
                if(j == orbit_path.size() - 1){
                    getCoordinates().setX(orbit_path.get(0).getX());
                    getCoordinates().setY(orbit_path.get(0).getY());    
                    break;
                } else {            
                    getCoordinates().setX(orbit_path.get(j+1).getX());
                    getCoordinates().setY(orbit_path.get(j+1).getY());
                    break;
                }
            }               
        }
    }

    /**
     * Sets the coordinates of the moon to a random point on its orbit path 
     */
    public void randomInitPos(){
        coordinates.setCoordinates(orbit_path.get( (int)(Math.random()*orbit_path.size()) )) ;
    }

    /**
     * Initialises the sprites for each elevation of the moon's surface 
     * Leave empty to use the default option (not recommended)
     */
    protected void setSurfaceSprites(){
        ArrayList< Pair< Integer,ArrayList<Integer> >> a = new ArrayList<>() ;  // Declare the elevation initialising list 
        ArrayList<Integer> moon_rock = new ArrayList<>() ;                      // Declare layers

        moon_rock.add(Textures.moonCrater) ;                                                      // Initialise layer sprites 
        moon_rock.add(Textures.moonRock) ;                                                 
        
        a.add(new Pair<Integer,ArrayList<Integer>>(20,moon_rock)) ;             // Initialise layer elevation 
    
        try{
            surface.initElevation(a) ;
            surface.initSurface(2) ;
        }catch( UninitialisedElevationException e ){
            e.printStackTrace() ;
        }catch( ElevationOutOfBoundsException e ){
            e.printStackTrace() ;
        }
    } 

}
