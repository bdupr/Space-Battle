package world ;

import gui.Config ;
import gui.Textures ;

public class StartupWorld {
    private Config configuration ; 

    private int dx ;
    private int dy ;
    
    public int[][] startup_grid ;
    private DefaultMoon startup_moon ;
    private DefaultPlanet startup_planet ;

    public StartupWorld(Config configuration){
        this.configuration = configuration ;

        this.dx = configuration.world.getDx() ;
        this.dy = configuration.world.getDy() ;
        
        startup_grid = new int[dx][dy] ;

        startup_planet = configuration.planet ;
        startup_moon = configuration.moon ;
    }

    // Getters
    public int getDx(){ return dx ; }
    public int getDy(){ return dy ; }

    public void update(){
        
        startup_planet = configuration.planet ;
        startup_moon = configuration.moon ;
        
        for(int i = 0; i < startup_grid.length; i++)
        	for( int j = 0; j < startup_grid[i].length; j++ ) 
        		startup_grid[i][j] = Textures.spacePlaceholder  ;
        
        updateCelestialBody(startup_planet) ;
        updateCelestialBody(startup_moon) ;
    }
    

    /**
     * Draws lines from the surface_grid of a celestial body between -x and +x for both the +y and -y sections, with the coordinates of the 
     * celestial body x0 and y0 being used as the reference point.
     */
    private void drawSurface(CelestialBody cb, int x0, int y0, int x, int y){ 
        for(int i = -x; i < x ; i++){           //  Draw the surface between x0-x and x0+x for the y0-y section
            startup_grid[x0+i][y0+y] = Textures.landPlaceholer ;
        }
        if(y != 0){         // So long as we are not on the "equator", prevents section from being drawn twice
            for(int i = -x; i < x ; i++){       //  Draw the surface between x0-x and x0+x for the y0-y section
                startup_grid[x0+i][y0-y] = Textures.landPlaceholer ;
            }
        }
    }

    /**
     * Updates a celestial body by drawing a filled circle on the world grid at its given coordinates with its given radius. 
     * The circle is filled with the surface of the celestial body.
     */
    public void updateCelestialBody(CelestialBody cb){
        // Modifed version of the Bresenham circle algorithm to draw a filled circle 
        int t1 = -cb.getRadius() ;      // Determines when to decrement x
        int x = cb.getRadius() ;        // Value of x
        int y = 0 ;                     // Value of y
        
        while(x >= y){
            int t2 = y ;                // Save the value of y at the begining of the loop
            t1 += y ;
            ++y ;
            t1 += y ; 

            // Draw lines from the "equator" of the celestial body outwards
            drawSurface(cb, cb.getCoordinates().getX(), cb.getCoordinates().getY(), x, t2) ;

            if(t1 >= 0){
                if(x != 0)
                    // Draw lines from the "poles" of the celestial body inwards
                    drawSurface(cb, cb.getCoordinates().getX(), cb.getCoordinates().getY(), t2, x) ;
                t1 -= x ;
                --x ; 
                t1 -= x ;
            }
        }
    }

    

}
