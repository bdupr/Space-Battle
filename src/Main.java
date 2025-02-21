import java.awt.Graphics;
import java.lang.Object ;
import javax.swing.SwingUtilities ;
import javax.swing.Timer ;
import java.awt.event.ActionListener ;
import java.awt.event.ActionEvent ;

import world.* ;
import gui.* ;
import entities.* ;
import entities.agents.* ;
import entities.structure.* ;
import util.* ;

public class Main{
    /*Checklist :
        
        World    
            Add multiple moons     
                make error class if moon orbit is bigger than world (moon + distance + planet > world)
                generate moon orbits depending on colision 
                make moon orbit modifiable 
            Finish planet
                add checkbounds method 
                multiple direction rotation for planet (unlikely)
            Different world variants
                planet without moons variant
                galactic map variant
                planet and multiple moons variant
            Bugs
                Important
                
                Not important 
                    Some planet sizes cause moon to not spin on edges of arcs 
            Catastophies
                volcanoes throw up smoke in the atmosphere
        Gui
            Menu to select world type
            Global/Local view
            Move around world in local view 
            Statistics and graphs
        Entities
            Agents 
                hit detection before moving
                hit method
            Structures
                dsiplay when on planet 
            Ressources 
                dsiplay when on planet
        Other
            Make set coordinates unavailable in some cases
            Up the resolution
            Make a clock or game ticks 
            -------------------------------------------------------------------AJOUT ERREUR !!
        Notes 
            Rotation order test :
            for(int i = 0 ; i < planet.getRadius()*2+1; i++)
                System.out.print(planet.getRotationOrder()[i]+",");
            Random in between range :
                min + (int)(Math.random() * ((max-min) + 1 )) ;
            Empty method :
                throw new UnsupportedOperationException("Unimplemented method 'move'");
            
            
            In order : 
            	Finish the configuration options
            		moon size 
            		moon position
            		planet size 
            		moon orbit speed 
            		moon distance from planet
            		planet rotation speed 
            		impose limits based on the whole planetary systems
            	resizeing cb doesn't create a new instance
            		make CelestialBody have a initSurface method
            		make a resizable state 
            		make some startup celestial bodys with differrent initialisations 
            	figure out the window to panel size discrepency
            		still doesn't work
            	parlell processing of sprites, figure out how to synchronize the program better with  java swings edt thread 
    */

    public static void main(String[] args){
        
        int WINDOW_SIZE_X = 1400;
        int WINDOW_SIZE_Y = 847;
        int SPRITE_SIZE = 8 ;  

        int dx = 120 ; 
        int dy = 100 ;
        
        int planet_radius = 25 ;  // modifiable by user 
        
        DefaultPlanet planet = new DefaultPlanet(planet_radius,dx/2,dy/2, 10);
        DefaultMoon moon = new DefaultMoon(planet, 5, 10, 2) ;

        World world = new World(dx, dy, planet, moon,2) ;
        
        int red_team = 0 ;
        int blue_team = 1 ;
	
        //world.teams.get(blue_team).add(new Engineer(new Coordinates((int)(Math.random() * dx), (int)(Math.random() * dy)), world,blue_team));
        //world.teams.get(red_team).add(new Factory(new Coordinates(21,10), world,red_team));
 
        //world.teams.get(blue_team).add(new Factory(new Coordinates(dx-2,dy-20), world,blue_team));
        //SurfacePanel est une ressource temporaire pour les test, possiblité de l'intégrer à l'affichage 
        
        GUI gui = new GUI(new SpritePanel(world, SPRITE_SIZE), WINDOW_SIZE_X, WINDOW_SIZE_Y, new SurfacePanel(planet.getSurface(), SPRITE_SIZE), new ConfigPanel()) ;

        Timer programTimer = new Timer(20,new ActionListener (){
		@Override
		public void actionPerformed( ActionEvent e){
			world.step();
			gui.repaint();
		}
        }) ;
         
        Timer startupTimer = new Timer(20,new ActionListener (){
		@Override
		public void actionPerformed( ActionEvent e){
			if( gui.getInitStatus()){
				((Timer)(e.getSource())).stop() ;
				programTimer.start() ; 
			}
			gui.getStartupWorld().update() ;
            		gui.repaint() ;
		}
        }) ;
        
        System.out.println("Height : "+gui.getHeight()) ;
        System.out.println("Width : "+gui.getWidth()) ;
        
        startupTimer.start() ;
        
        /*
        while( !gui.getInitStatus() ){
            gui.getStartupWorld().update() ;
            gui.repaint() ;
            try { Thread.sleep(33) ;} 
            catch (Exception e) {}
        }
        int delay = 0 ;
        int iteration = 10000;
        
        while( iteration != 0 ){
		world.step();
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
    				public void run() {
        				gui.repaint();
    				}
			});
		} catch (Exception e ){
			e.printStackTrace() ;
		}
		iteration--;
		
            try { Thread.sleep(delay) ;} 
            catch (Exception e) {}
        }
        */
        
    
    }   
}
