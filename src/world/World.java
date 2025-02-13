package world ;
import util.* ;
import entities.* ; 
import entities.ressources.* ;
import entities.structure.* ; 
import entities.agents.* ;

import java.util.ArrayList ;

/**
 * world[0] -> background : asteroids,space,stars
 * world[1] -> celestial bodys : planets,moons
 * world[2] -> ressources 
 */

public class World{

    private int dx ;
    private int dy ;   

    public int[][][] world_grid ;  
    public DefaultPlanet planet ;
    public ArrayList<DefaultMoon> moons ;
    
    public ArrayList<ArrayList<Entity>> teams ; // Limit of 6, sprites for only 2 
    public ArrayList<ArrayList<Entity>> teams_buffer ; // Limit of 6, sprites for only 2 
    public int team_number ;

    private ArrayList<Coordinates> starting_base_position ;
    

    public ArrayList<Ressource> ressources ;

    private int tick ;

    public World(int dx , int dy, DefaultPlanet planet,DefaultMoon moon, int team_number ){
        
        this.dx = dx ;
        this.dy = dy ;

        world_grid = new int[dx][dy][3] ;  
        this.planet = planet ;
        moons = new ArrayList<DefaultMoon>() ;

        moons.add(moon) ;
        
        this.team_number  = team_number ;
        teams = new ArrayList<ArrayList<Entity>>() ; // Liste des équipes, qui sont elles mêmes des listes d'entités
        teams_buffer = new ArrayList<ArrayList<Entity>>() ; // Buffer pour la modification de l'arraylist en parcour 

        for(int i = 0; i < team_number ; i++){
            teams.add(new ArrayList<Entity>() ) ;
            teams_buffer.add(new ArrayList<Entity>() ) ;
        }

        //entity_list = new ArrayList<Entity>() ; 
        //entity_buffer = new ArrayList<Entity>() ;

        
        ressources = new ArrayList<Ressource>() ; // Ressources won't disapear so they are treated separately  

        for(int i = 0; i < dx ; i++)
            for(int j = 0 ; j < dy ; j++ ){
                world_grid[i][j][0] = Math.random() < 0.95 ? 4 : 5 ;
                world_grid[i][j][1] = 0 ; 
            }                

        initAsteroidBelt(1.5,80) ; // 1.5
        initRessources() ; 

        starting_base_position = new ArrayList<Coordinates>() ; // Position initiale des bases des agents
        starting_base_position.add(new Coordinates(10,10)) ;
        starting_base_position.add(new Coordinates(dx-11,dy-11)) ;
        starting_base_position.add(new Coordinates(dx-11,10)) ;
        starting_base_position.add(new Coordinates(10,dy-11)) ;
        starting_base_position.add(new Coordinates(dx/2,10)) ;
        starting_base_position.add(new Coordinates(dx/2,dy-11)) ;

        //for( int i = 0 ; i < teams.size() ; i++){ // Pour chaque équipe, initializer la première entité en tant que Base selon une position init
        //    teams.get(i).add(new Base(starting_base_position.get(i),this,i)) ;
        //    ((Base)(teams.get(i).get(0))).addCrystal(2000) ; // Ajouter des ressources dans la base pour les test
        //    ((Base)(teams.get(i).get(0))).addEnergy(2000) ; // Ajouter des ressources dans la base pour les tests
        //}
        
        tick = 0 ;              
    }

    public int getDx(){ return dx ; }
    public int getDy(){ return dy ; }
    public int getTick(){ return tick ; }
    //Removing and adding agents might be done through methods
    //public void addAgent( Agent a ){ agent_list.add(a) ; }
    
    public void step(){
        worldStep() ;
        teamEntityStep() ;
        tick++ ;
    }

    public void worldStep(){
        //Clear celestial bodies and ressources each step 
        for(int i = 0; i < dx ; i++){
            for(int j = 0 ; j < dy ; j++ ){
                world_grid[i][j][1] = 0 ;
                world_grid[i][j][2] = 0 ;
            }
        }

        for(Ressource r : ressources){
            if( !(r instanceof Energy) ){     
                r.getCoordinates().setCoordinates(-50,-50) ;
                r.setVisibility(false) ;
            }
        }

        planet.rotation(tick) ;
        updateCelestialBody(planet);
        for( DefaultMoon moon : moons ){
            moon.orbit(tick);
            updateCelestialBody(moon);
        }
    }
    
    public void teamEntityStep(){
        for(int i = 0 ; i < teams.size(); i++ ){
            for( Entity e : teams.get(i) ){
                if( e instanceof Base && ((Base)(e)).isDestroyed()  ){
                    for( int j = 0 ; j < 20 ; j++)
                        teams_buffer.get(i).add(new Soldier(starting_base_position.get(i).clone(), this,i));
                    for( int j = 0 ; j < 10 ; j++)
                        teams_buffer.get(i).add(new ShieldSoldier(starting_base_position.get(i).clone(), this,i));
                    for( int j = 0 ; j < 5 ; j++)
                        teams_buffer.get(i).add(new Engineer(starting_base_position.get(i).clone(), this,i));
                }
                else if((e instanceof Structure) && !((Structure)(e)).isDestroyed() ){
                    if(e instanceof Base){
                        Base b = ((Base)(e)) ;
                        
                        teams_buffer.get(i).add(b) ;

                        b.creeEng(tick) ; // Déclarer ingénieurs ici sinion premier élément de arraylist n'est plus la base
                    }
                    else if(e instanceof Turret){
                        Turret t = ((Turret)(e)) ;

                        t.targeting() ;
                        t.shoot(tick) ;

                        teams_buffer.get(i).add(t) ;
                    }
                    else if(e instanceof Extractor){
                        Extractor ex = ((Extractor)(e)) ;
                        
                        ex.extract(tick) ;
                        
                        teams_buffer.get(i).add(ex) ;
                    }
                    else if(e instanceof Factory){
                        Factory f = ((Factory)(e)) ;
                        
                        f.forge(tick) ;
                        
                        teams_buffer.get(i).add(f) ;
                    }
                }
                if( (e instanceof Agent ) && !((Agent)(e)).isDead() ){    
                    if( e instanceof ShieldSoldier){
                        ShieldSoldier ss = ((ShieldSoldier)(e)) ; 
                        
                        ss.initTravel() ;
                        ss.move() ;

                        ss.regenShield(tick) ;
                        
                        ss.targeting() ;
                        ss.shoot(tick) ; 
                        teams_buffer.get(i).add(ss) ;
                    }
                    else if( e instanceof Engineer){
                        Engineer eng = ((Engineer)(e));

                        eng.tempBuild() ;
                        eng.recolt() ;
                        eng.initTravel() ;
                        eng.move() ;

                        teams_buffer.get(i).add(eng) ;
                    }
                    else{
                        Soldier s = ((Soldier)(e)) ; 


                        s.initTravel() ;
                        s.move() ;

                        s.targeting() ;
                        s.shoot(tick) ;
                        teams_buffer.get(i).add(s) ;
                    }
                }
                if( e instanceof Projectile){
                    Projectile p = ((Projectile)(e)) ;
                    if(p.isExpired() == false){
                        p.travel() ; 

                        teams_buffer.get(i).add(p) ;
                    }    
                }
            }
        }
        teams = cloneTeamsBuffer() ;
        teams_buffer.clear() ;
        for(int i = 0 ; i < team_number ; i++ )    
            teams_buffer.add(new ArrayList<Entity>()) ;

    } 

    public ArrayList<ArrayList<Entity>> cloneTeamsBuffer(){
        ArrayList<ArrayList<Entity>> _tm = new ArrayList<ArrayList<Entity>>() ;

        for(int i = 0 ; i < teams_buffer.size() ; i++ ){
            _tm.add(new ArrayList<Entity>() ) ;
            for(int j = 0 ; j < teams_buffer.get(i).size() ; j++ ){
                _tm.get(i).add(teams_buffer.get(i).get(j)) ;
            }   
        }
        return _tm ;
    }

    /*
    public void entityStep(){
        for( Entity e : entity_list ){
            if((e instanceof Structure) && !((Structure)(e)).isDestroyed() ){
                if(e instanceof Turret){
                    Turret t = ((Turret)(e)) ;
                    
                    t.targeting() ;
                    t.shoot(tick) ;
                    
                    entity_buffer.add(t) ;
                }
                else if(e instanceof Extractor){
                    Extractor ex = ((Extractor)(e)) ;
                    entity_buffer.add(ex) ;
                }
                else if(e instanceof Factory){
                    Factory f = ((Factory)(e)) ;
                    entity_buffer.add(f) ;
                }
                else{
                    Base b = ((Base)(e)) ;
                    entity_buffer.add(b) ;
                }
            }
            if( (e instanceof Agent ) && !((Agent)(e)).isDead() ){    
                if( e instanceof ShieldSoldier){
                    ShieldSoldier ss = ((ShieldSoldier)(e)) ; 


                    //ss.targeting() ;
                    //ss.shoot() ; 
                    entity_buffer.add(ss) ;
                }
                else if( e instanceof Engineer){
                    Engineer eng = ((Engineer)(e));
                    
                    eng.tempBuild() ;
                    
                    eng.initTravel() ;
                    eng.setSprite() ;
                    eng.tempDirectionSet();
                    eng.move() ;

                    entity_buffer.add(eng) ;
                }
                else{
                    Soldier s = ((Soldier)(e)) ; 

                
                    s.initTravel() ;
                    s.setSprite() ;
                    s.tempDirectionSet();
                    s.move() ;
                    
                    s.targeting() ;
                    s.shoot(tick) ; 
                    entity_buffer.add(s) ;
                }
            }
            if( e instanceof Projectile){
                Projectile p = ((Projectile)(e)) ;
                if(p.isExpired() == false){
                    p.travel() ; 
                            
                    entity_buffer.add(p) ;
                }    
            }
        }
        entity_list = copyBufferArray() ;
        entity_buffer.clear() ;  
    }
    */
    /*
    public ArrayList<Entity> copyBufferArray(){ // Remplacer par un Override de la méthode clone 
		ArrayList<Entity> _array = new ArrayList<Entity>();
		for(int i = 0 ; i < entity_buffer.size() ; i++ )
			_array.add(entity_buffer.get(i));
		return _array ;
	}
    */

    /**
     * Draws lines from the surface_grid of a celestial body between -x and +x for both the +y and -y sections, with the coordinates of the 
     * celestial body x0 and y0 being used as the reference point.
     * Also make any ressources present on those cells of the surface_grid appear in the world by setting their coordinates and making them visible.
     * (The method draws the central portion the surface_grid)
     */
    private void drawSurface(CelestialBody cb, int x0, int y0, int x, int y){ 
        for(int i = -x; i < x ; i++){           //  Draw the surface between x0-x and x0+x for the y0-y section
            world_grid[x0+i][y0+y][1] = cb.getSurface().surface_grid[(cb.getRadius()*2)+i][cb.getRadius()-y][0] ;
            
            // Ressource managment
            int r1 = cb.getSurface().surface_grid[(cb.getRadius()*2)+i][cb.getRadius()-y][1] ; 
            if(r1 != 0){
                world_grid[x0+i][y0+y][2] = r1 ;
                ressources.get(r1-1).getCoordinates().setCoordinates(x0+i,y0+y) ;
                ressources.get(r1-1).setVisibility(true) ;
            }
        }
        if(y != 0){         // So long as we are not on the "equator", prevents section from being drawn twice
            for(int i = -x; i < x ; i++){       //  Draw the surface between x0-x and x0+x for the y0-y section
                world_grid[x0+i][y0-y][1] = cb.getSurface().surface_grid[(cb.getRadius()*2)+i][cb.getRadius()+y][0] ;
                
                // Ressource managment 
                int r2 = cb.getSurface().surface_grid[(cb.getRadius()*2)+i][cb.getRadius()+y][1] ;
                if(r2 != 0){
                    world_grid[x0+i][y0-y][2] = r2 ;
                    ressources.get(r2-1).getCoordinates().setCoordinates(x0+i,y0-y) ;
                    ressources.get(r2-1).setVisibility(true) ;
                }
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

    public void initRessources(){
        //Initialize ressources for the planet
        //May change with the addition of multiple planets
        int index = 1 ;
        for(int i = 0 ; i < planet.getSurface().getDx(); i++){
            for(int j = 0 ; j < planet.getSurface().getDy(); j++){
                if( planet.getSurface().surface_grid[i][j][0] == 6 )
                    if(Math.random() > 0.8){
                        this.ressources.add(new Crystal(new Coordinates(-50,-50))) ;
                        planet.getSurface().surface_grid[i][j][1] = index ;
                        index++ ;
                    }
            }
        }

        for( DefaultMoon moon : moons ){
            for(int i = 0 ; i < moon.getSurface().getDx(); i++){
                for(int j = 0 ; j < moon.getSurface().getDy(); j++){
                    if( moon.getSurface().surface_grid[i][j][0] == 8 )
                        if(Math.random() > 0.9){
                            this.ressources.add(new MoonCrystal(new Coordinates(-50,-50))) ;
                            moon.getSurface().surface_grid[i][j][1] = index ;
                            index++ ;
                        }
                }
            }
        }

        for(int i = 0 ; i < dx; i++){
            for(int j = 0 ; j < dy; j++){
                if( world_grid[i][j][0] == 10 ){
                    if( Math.random() > 0.9 ){
                        Energy eg = new Energy(new Coordinates(i,j)) ;
                        eg.setVisibility(true) ;
                        this.ressources.add(eg) ;
                        index++ ;
                    }
                }
            }
        
        }

    }
    
    /**
     * Initialises an asteroid_array
     * Attributes asteroid sprites to the world, heavily favoring the cells closest to the corners of the world
     * The variable zoom is a magnification factor for the Perlin noise, the lower the value the more homogenous it is. 
     * noise_gen.noise(i,j) ~ [-1,1] with the most common values between -0.5 and 0.5 
     */
    public void initAsteroidBelt(double density,int zoom){
        NoiseGenerator noise_gen = new NoiseGenerator() ; 

        Coordinates[] corners = new Coordinates[] {
                        new Coordinates(0,0),                       // Top left 
                        new Coordinates(dx-1,dy-1),                 // Bottom right
                        new Coordinates(0,dy-1),                    // Bottom left 
                        new Coordinates(dx-1,0)                     // Top right
                    } ;
        double max_distance = corners[0].distanceFrom(corners[1]) ;         // Longest distance between 2 coordinates possible 
        
        for(int i = 0 ; i < dx ; i++){
            for(int j = 0 ; j < dy ; j++ ){
                
                // Tighten the bounds to create asteroid patches instead of having a uniform coverage 
                if(noise_gen.noise(i*zoom,j*zoom) < 0.1 && noise_gen.noise(i*zoom,j*zoom) > -0.1 ){
                    
                    Coordinates current_coord = new Coordinates(i,j);               // Coordinates of the current cell
                    double weight = 0 ;                                             // Likelyhood of an asteroid sprite being drawn          
                    for(Coordinates corner : corners )                              // The closer to the corners a cell is the higher the weight
                        weight = Math.max(weight, Math.exp(current_coord.distanceFrom(corner)/max_distance) ) ; // weight ~ [1,3]

                    if(Math.random() < (weight - 2) * density)                      // Control the density of asteroids 
                        world_grid[i][j][0] = (int)(10 + Math.random() * ((12-10) + 1)) ; // Randomly assign one of the different asteroid sprites 
                }
            }
        }
    }

    public boolean inBounds(int x, int y){
        return (x < dx && y < dy) && (x >= 0 && y >= 0) ;
    }

}