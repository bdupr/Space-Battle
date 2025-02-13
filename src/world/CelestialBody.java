package world ;
import util.Coordinates ;

import java.util.ArrayList ;

/** Description : 
 * 
 * This class describes the components of a celestial body.
 * In this program a celestial body refers to a circle drawn based on the Bresenham circle algorithm in a 2d grid environment.
 * This class contains all the necessary components to execute said algorithm, including an array detailing the length (or diameter
 * as referred hereafter) of each section of the circle, with the unit of length being the amount of horizontal cells it occupies on the grid.
 */

public abstract class CelestialBody{
    
    protected int radius ;                      // The radius of the celestial body
    protected CelestialBodySurface surface ;    // The surface of the celestial body
    protected Coordinates coordinates ;         // The coordinates of the celestial body's center point
    protected int[] section_diameter ;          // Length of each line of the circle on the grid upon which it will be drawn

    public CelestialBody(int radius, int x0, int y0){
        this.radius = radius ;
        coordinates = new Coordinates(x0, y0);
        section_diameter = new int[radius * 2 + 1] ;
        
        initSectionDiameter(radius) ;           // Initialisation of section_diameter's values  
        
        surface = new CelestialBodySurface(radius*4+1,radius*2+1,section_diameter) ;    // Initialisation of surface  
    }

    // Getters 
    public int getRadius(){ return radius ; }
    public CelestialBodySurface getSurface(){ return surface ; }
    public Coordinates getCoordinates(){ return coordinates ; }
    public int[] getSectionDiameter(){ return section_diameter ; }

    /**
     * Allows inheriting classes to set their own unique surface sprites, which are otherwise set to a default option by
     * the Surface class.  
     * Leave empty to keep default surface sprites (not recommended, check Surface class for more information)
     */
    protected abstract void setSurfaceSprites() ; 

    /**
     * Initialises the values of the section_diameter array
     * It calculates symetrically the diameters of both extremities and that of the center of the circle using a modified version
     * of the Bresenham circle algorithm
     */
    private void initSectionDiameter(int radius){ 
        int t1 = -radius ;              // Determines when x decreases and when to calculate the extremities 
        int x = radius ;                // Radius of the mid section of the circle
        int y = 0 ;                     // Radius of the extremities of the circle 
        int index1 = radius ;           
        int index2 = 0 ;
        while(x >= y){
            int t2 = y ;
            t1 += y ;
            ++y ;
            t1 += y ; 

            
            section_diameter[index1] = x*2 ;                        // Initialises the section_diameter array from middle to end 
            if( t2 != 0){
                section_diameter[radius - (index1- radius)] = x*2 ; // Initialises the section_diameter array from middle to begining 
            }
            index1++ ;

            if(t1 >= 0){
                if(x != 0){
                    section_diameter[index2] = t2*2 ;   // Initialises the section_diameter array starting from the extremities towards the middle 
                    section_diameter[radius * 2 - index2] = t2*2 ;
                    index2++ ;
                }
                t1 -= x ;
                --x ; 
                t1 -= x ;
            }
        }
    }

}