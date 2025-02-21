package world ;
import entities.ressources.* ;
import util.Pair ;
import gui.Textures ;

import java.util.ArrayList ;

/** Description : 
 * 
 * This class initialises a surface that is tied to a CelestialBody. A surface is a dx*dy grid with dy = diameter + 1 and dx = (diameter*2) + 1
 * with diameter being the diameter if the CelestialBody. 
 * 
 * The reason dx is twice as big as dy is because some celestial bodys have the ability to rotate horizontally, thus their surface needs to
 * be at least as big as 2 times their diameter.
 * 
 * The surface will be initialised using a Perlin noise generator to simulate elevation in terrain. Each cell of surface_grid will contain the id
 * of the texture that will be displayed at that position.
 * 
 * The surface will be subdivided into 20 levels of elevation represented by the elevation array, with the elevation being proportional to the 
 * index. If there is more than one texture id per level they will be attributed on said level via uniform random distribution.
 * 
 * The section_diameter array is used in the context of hiding the portion of the surface that will not be shown in the context of a rotating
 * CelestialBody, which is purely an asthetic choice. For more information visit the Planet and CelestialBody class.
 *
 */

public class CelestialBodySurface{

    public int[][][] surface_grid ;                     // Grid containing information pertaining to the surface  
    private int dx ;                                    // Width of the surface 
    private int dy ;                                    // Height of the surface 
    private ArrayList< ArrayList<Integer> > elevation;  // Array of size 20 detailing the levels of the surface based on the index. 
    private final int ELEVATION_LIMIT = 20 ;            // Upper bound for elevation array
    private int[] section_diameter ;                    // Length of each line of the CelestialBody on the grid upon which it will be drawn

    public CelestialBodySurface(int dx, int dy, int[] section_diameter){
        
        this.dx = dx ;
        this.dy = dy ;
        this.section_diameter = section_diameter ;    

        surface_grid = new int[dx][dy][2] ;  

        elevation = new ArrayList<>(20) ;

        defaultElevation() ;                            // Set a default elevation config, prevents empty arraylist
        
        try{
            initSurface(2);
        } catch ( UninitialisedElevationException e ){
            e.printStackTrace() ;
        }
    }

    // Getters 
    public int getDx(){ return dx ; }
    public int getDy(){ return dy ; }
    
    /**
     * Initialises all the elements of the surface 
     * Throws an UninitialisedElevationException if the elevation array hasn't been previously initialised with the initElevation() method
     * Uses Perlin noise to simulate terrain
     * The variable zoom is a magnification factor for the Perlin noise, the lower the value the more homogenous it is. 
     * noise_gen.noise(i,j) ~ [-1,1] with the most common values between -0.5 and 0.5 
     */
    public void initSurface(int zoom) throws UninitialisedElevationException {
        if(elevation.isEmpty()) throw new UninitialisedElevationException() ;
        NoiseGenerator noise_gen = new NoiseGenerator() ;
        for (int i = 0 ; i < dx ; i++ ){
            for (int j = 0 ; j < dy ; j++ ){                    // for every cell in surface_grid
                
                double upper_bound = - 0.9 * zoom ;
                double lower_bound = - zoom ;

                for (int k = 0 ; k < elevation.size() ; k++ ){  
                    // Check if the value of the noise map is between the interval
                    if( noise_gen.noise(i*zoom,j*zoom) >= lower_bound && noise_gen.noise(i*zoom,j*zoom) < upper_bound ){
                        // If so give it the value of the proportional elevation level 
                        surface_grid[i][j][0] = elevation.get(k).get( (int)(Math.random() * elevation.get(k).size() ) ) ; 
                        break ;
                    }
                    else{
                        upper_bound += 0.1*zoom ;
                        lower_bound += 0.1*zoom ;
                    }
                }
            }
        }

        hideCorners() ;
    }
    
    /**
     * Initialises the elevation array for the surface.
     * It takes for argument an array of pairs each containing a set of textures for an elevation and the highest elevation level they will reach.
     * for example the pair (5,[3,9]) indicates that between lower_bound and 5 elevation[] will contain the texture set [3,9] 
     * Throws an ElevationOutOfBoundsException if the elevation bound in a pair is greater than 20 as per the elevation array size limit. 
     */
    public void initElevation(ArrayList< Pair< Integer,ArrayList<Integer> > > array) throws ElevationOutOfBoundsException {
        elevation.clear() ;         // Clear the list to remove the previous elevation config if any
        int lower_bound = 0 ;
        for (int i = 0 ; i < array.size() ; i++ ){
            if(array.get(i).getFirst() > ELEVATION_LIMIT ) throw new ElevationOutOfBoundsException(ELEVATION_LIMIT) ;
            
            for (int j = lower_bound ; j < array.get(i).getFirst() ; j++ ){
                elevation.add( array.get(i).getSecond() )  ;
            }   
            lower_bound = array.get(i).getFirst() ;
        }
    }

    /**
     * The default elevation for any surface, prevents the elevation array from being uninitialised.
     */
    public void defaultElevation(){
        ArrayList< Pair< Integer,ArrayList<Integer> >> a = new ArrayList<>() ;  // Declare the initialising list 
        ArrayList<Integer> default_sprite = new ArrayList<>() ;                 // Default sprite set
        default_sprite.add(Textures.water) ;                                                     

        a.add(new Pair<Integer,ArrayList<Integer>>(20,default_sprite)) ;        // One sprite for all 20 layers    

        try{
            initElevation(a) ;
        } catch(ElevationOutOfBoundsException e){
            e.printStackTrace() ;
        }
            
    } 

    /**
     * Hides the corners of the surface that will not be used or seen in the program
     * This is a purely asthetic measure
     */
    private void hideCorners(){
        for(int i = 0; i < dx ; i++)
            for (int j = 0 ; j < dy ; j++ )
                if( i < dx/2 - section_diameter[j] || i > dx/2+1 + section_diameter[j])
                    surface_grid[i][j][0] = Textures.missing ;
    }
}
