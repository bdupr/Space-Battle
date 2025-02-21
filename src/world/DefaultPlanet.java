package world ;
import util.Coordinates ;
import util.Pair ;
import gui.Textures ;

import java.util.ArrayList ;

/** Description : 
 * 
 * This class initialises an instance of DefaultPlanet. As a class that extends CelestialBody it inherits all its variables and methods.
 * 
 * This class posses a rotation method and a planet_meridian array exclusively used for said method. As the information for planet_meridian 
 * needs to be kept between method calls its initialised as a class variable. 
 */

public class DefaultPlanet extends CelestialBody{    
    
    private int[] planet_meridian ;                 // Array representing the position of the planet's meridian on its surface for each section
    private int rotation_frequency ;                // Frequency for the rotation of the planet in the program    
    private int rotation_tick ;                     // Timer for the rotation of the planet in the program    

    public DefaultPlanet(int radius, int x0, int y0, int rotation_frequency){
        super(radius, x0, y0);
        setSurfaceSprites() ;                           // Initialising the sprites for the planet's surface 
        
        this.rotation_frequency = rotation_frequency ;
        rotation_tick = -1 ;

        planet_meridian = new int[surface.getDy()] ;
        
                        
        for(int j = 0; j < surface.getDy() ; j++){      // Initialising the values of planet_meridian
            planet_meridian[j] = surface.getDx()/2+1  ;
        }
    }

    // Getters / Setters
    public void setRotationFrequency( int new_frequency ) { new_frequency = rotation_frequency ; }

    /**
     * Determines if the frequency delay for a rotation has been reached 
     * current_tick refers to the current tick of the program
     */
    private boolean rotationDelay(int current_tick){
        if(rotation_tick == -1){    // Initial value of tick at program launch
            return true ;
        }
        else if( current_tick > rotation_tick + rotation_frequency ){
            rotation_tick = -1 ;
            return true ;
        }
        else 
            return false ;
    }

    /**
     * Executes a rotation with a frequency determined by rotationDelay and resets the delay after 
     * current_tick refers to the current tick of the program
     */
    public void rotation(int current_tick){
        if(rotationDelay(current_tick)){
            rotationLogic() ;
            rotation_tick = current_tick ;
        }
    }

    /**
     * Gives the illusion of the planet rotating by shifting the surface from right to left
     * Does so in an manner that looks realistic, with the sections closest to the "equator" of the planet rotating faster than the poles
     * 
     * To acheive this the method will always shift the "equator" and compare it to all other sections of the surface based on 
     * the current position of the planet's meridian along that section's diameter :
     *          (current_section_meridian/current_section_diameter*2 > central_section_meridian/central_section_diameter*2). 
     * If their positions are not proportional it will proceed to shift those sections.  
     */
    private void rotationLogic(){
        int[] buffer1 = new int[surface.getDy()] ;              // Buffer for the sprite layer 
        int[] buffer2 = new int[surface.getDy()] ;              // Buffer for the ressource layer 

        int anchor = planet_meridian[surface.getDy()/2] - 2 ;   // Meridian of the central section 
        
        for(int j  = 0 ; j < surface.getDy(); j++){
                
            int cpoint = planet_meridian[j] - ( surface.getDx()/2 - section_diameter[j] ) - 1  ;        // Meridian of the current section 
                                                                                                        // Is offset by the unused portion of the surface

            double central_meridian_pos = (double)(anchor/(double)(section_diameter[surface.getDy()/2]*2)) ;    // Position of the meridian on central section
            double current_meridian_pos = (double)(cpoint/(double)(section_diameter[j]*2)) ;                    // Position of the meridian on current section

            // Always shift if central section, otherwise if current section's meridian is not proportional to the central section's meridian    
            if( current_meridian_pos > central_meridian_pos || j == surface.getDy()/2){   
                for(int i  = 0 ; i < surface.getDx(); i++){

                    // Only shift the usable part of the surface
                    if(i >= surface.getDx()/2 - section_diameter[j] && surface.getDx()/2 + section_diameter[j] > i){
                        if( i == surface.getDx()/2 - section_diameter[j] ){
                            buffer1[j] = surface.surface_grid[i][j][0] ;
                            buffer2[j] = surface.surface_grid[i][j][1] ;
                            surface.surface_grid[i][j][0] = surface.surface_grid[i+1][j][0] ;
                            surface.surface_grid[i][j][1] = surface.surface_grid[i+1][j][1] ;
                        }
                        else if( i == surface.getDx()/2 + section_diameter[j] - 1 ){
                            surface.surface_grid[i][j][0] = buffer1[j] ;
                            surface.surface_grid[i][j][1] = buffer2[j] ;
                        } 
                        else{
                            surface.surface_grid[i][j][0] = surface.surface_grid[i+1][j][0] ;
                            surface.surface_grid[i][j][1] = surface.surface_grid[i+1][j][1] ;
                        }
                    }
                } 

                // Update the position of the planet's meridian for this section
                if( planet_meridian[j] == surface.getDx()/2 - section_diameter[j] + 1 ){
                    planet_meridian[j] = surface.getDx()/2 - section_diameter[j] ; // Loop back to start of usable surface
                }
                else{
                    planet_meridian[j]-- ;
                }
            }
        }
        return ;
    }

    /**
     * Initialises the sprites for each elevation of the planet's surface 
     * Leave empty to use the default option (not recommended)
     */
    protected void setSurfaceSprites(){
        ArrayList< Pair< Integer,ArrayList<Integer> >> a = new ArrayList<>() ;  // Declare the elevation initialising list 
        ArrayList<Integer> ocean = new ArrayList<>() ;                          // Declare layers
        ArrayList<Integer> land = new ArrayList<>() ;
        ArrayList<Integer> mountain_rock = new ArrayList<>() ;
        ArrayList<Integer> mountain_peaks = new ArrayList<>() ;

        ocean.add(Textures.water);                                                           // Initialise layer sprites 
        land.add(Textures.grass);
        land.add(Textures.tree);
        mountain_rock.add(Textures.stone);
        mountain_peaks.add(Textures.mountain);                                                   
        
        a.add(new Pair<Integer,ArrayList<Integer>>(9,ocean)) ;                  // Initialise layer elevation 
        a.add(new Pair<Integer,ArrayList<Integer>>(12,land)) ;
        a.add(new Pair<Integer,ArrayList<Integer>>(14,mountain_rock)) ;
        a.add(new Pair<Integer,ArrayList<Integer>>(20,mountain_peaks)) ;

        try{
            surface.initElevation(a) ;
            surface.initSurface(2) ;
        }catch( UninitialisedElevationException e ){
            e.printStackTrace() ;
        }catch( ElevationOutOfBoundsException e ){
            e.printStackTrace() ;
        }
        for(int j = 0; j < surface.getDy() ; j++)
                surface.surface_grid[surface.getDx()/2+1][j][0] = 0;
        /*Draws a line in the middle of the planet for testing purposes, feel free to remove
            
        */
    }

}
