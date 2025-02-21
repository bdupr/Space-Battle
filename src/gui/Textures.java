package gui ;

import java.util.LinkedHashMap ;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList ;

/**
 * Contains a hashmap of the sprites used in the program
 * 
 * The sprites are seperated into different categories with each category under a different key
 *  
 */ 

public class Textures{
    private static final String world_path = "./assets/world";
    private static final String entity_path = "./assets/entities";

    // Map containing all the program's sprites
    private static final LinkedHashMap<Integer,ArrayList<BufferedImage>> SPRITE_MAP = new LinkedHashMap<>() ;

    // Indexes of the sprite categories
    public static final int MISSING         = 0 ;
    public static final int SPACE           = 1 ;
    public static final int SURFACE         = 2 ;
    public static final int MOON            = 3 ;
    public static final int ASTEROID        = 4 ;
    public static final int RED_SOLDIER     = 5 ;
    public static final int RED_ENGINEER    = 6 ;
    public static final int RED_STRUCTURE   = 7 ;
    public static final int BLUE_SOLDIER    = 8 ;
    public static final int BLUE_ENGINEER   = 9 ;
    public static final int BLUE_STRUCTURE  = 10 ;
    public static final int EQUIPMENT       = 11 ;
    public static final int RESSOURCES      = 12 ;
    public static final int PLACEHOLDER	    = 13 ;
    
    // Bit packing to access sprites within their categories. 8 heavy bits for category, 8 lightweight bits for position 
	public static final int missing 	 = 0 ;
	
	public static final int emptySpace	 = (SPACE << 8) | (0 & 0xFF );
	public static final int starySpace	 = (SPACE << 8) | (1 & 0xFF );
	
	public static final int water		 = (SURFACE << 8) | (0 & 0xFF );
	public static final int tree		 = (SURFACE << 8) | (1 & 0xFF );
	public static final int grass		 = (SURFACE << 8) | (2 & 0xFF );
	public static final int stone		 = (SURFACE << 8) | (3 & 0xFF );
	public static final int mountain	 = (SURFACE << 8) | (4 & 0xFF );
	
	public static final int moonRock	 = (MOON << 8) | (0 & 0xFF );
	public static final int moonCrater	 = (MOON << 8) | (1 & 0xFF );
	
	public static final int asteroid1 	 = (ASTEROID << 8) | (0 & 0xFF );
	public static final int asteroid2 	 = (ASTEROID << 8) | (1 & 0xFF );
	public static final int asteroid3 	 = (ASTEROID << 8) | (2 & 0xFF );

	public static final int redSoldierE 	 = (RED_SOLDIER << 8) | (0 & 0xFF ); 
	public static final int redSoldierSE 	 = (RED_SOLDIER << 8) | (1 & 0xFF ); 
	public static final int redSoldierS 	 = (RED_SOLDIER << 8) | (2 & 0xFF ); 
	public static final int redSoldierSW 	 = (RED_SOLDIER << 8) | (3 & 0xFF ); 
	public static final int redSoldierW 	 = (RED_SOLDIER << 8) | (4 & 0xFF ); 
	public static final int redSoldierNW 	 = (RED_SOLDIER << 8) | (5 & 0xFF ); 
	public static final int redSoldierN 	 = (RED_SOLDIER << 8) | (6 & 0xFF ); 
	public static final int redSoldierNE 	 = (RED_SOLDIER << 8) | (7 & 0xFF ); 
	
	public static final int redEngineerE 	 = (RED_ENGINEER << 8) | (0 & 0xFF );
	public static final int redEngineerSE	 = (RED_ENGINEER << 8) | (1 & 0xFF );
	public static final int redEngineerS	 = (RED_ENGINEER << 8) | (2 & 0xFF ); 
	public static final int redEngineerSW	 = (RED_ENGINEER << 8) | (3 & 0xFF ); 
	public static final int redEngineerW	 = (RED_ENGINEER << 8) | (4 & 0xFF ); 
	public static final int redEngineerNW	 = (RED_ENGINEER << 8) | (5 & 0xFF ); 
	public static final int redEngineerN	 = (RED_ENGINEER << 8) | (6 & 0xFF ); 
	public static final int redEngineerNE	 = (RED_ENGINEER << 8) | (7 & 0xFF ); 
	
	public static final int redBase 	 = (RED_STRUCTURE << 8) | (0 & 0xFF ); 
	public static final int redTurret 	 = (RED_STRUCTURE << 8) | (1 & 0xFF ); 
	public static final int redExtractor 	 = (RED_STRUCTURE << 8) | (2 & 0xFF ); 
	public static final int redFactory 	 = (RED_STRUCTURE << 8) | (3 & 0xFF ); 
	
	public static final int blueSoldierE 	 = (BLUE_SOLDIER << 8) | ( 1 & 0xFF );
	public static final int blueSoldierSE 	 = (BLUE_SOLDIER << 8) | (1 & 0xFF );
	public static final int blueSoldierS 	 = (BLUE_SOLDIER << 8) | (2 & 0xFF );
	public static final int blueSoldierSW 	 = (BLUE_SOLDIER << 8) | (3 & 0xFF );
	public static final int blueSoldierW 	 = (BLUE_SOLDIER << 8) | (4 & 0xFF );
	public static final int blueSoldierNW 	 = (BLUE_SOLDIER << 8) | (5 & 0xFF );
	public static final int blueSoldierN 	 = (BLUE_SOLDIER << 8) | (6 & 0xFF );
	public static final int blueSoldierNE 	 = (BLUE_SOLDIER << 8) | (7 & 0xFF );

	public static final int blueEngineerE 	 = (BLUE_ENGINEER << 8) | (0 & 0xFF );
	public static final int blueEngineerSE 	 = (BLUE_ENGINEER << 8) | (1 & 0xFF );
	public static final int blueEngineerS 	 = (BLUE_ENGINEER << 8) | (2 & 0xFF );
	public static final int blueEngineerSW 	 = (BLUE_ENGINEER << 8) | (3 & 0xFF );
	public static final int blueEngineerW 	 = (BLUE_ENGINEER << 8) | (4 & 0xFF );
	public static final int blueEngineerNW 	 = (BLUE_ENGINEER << 8) | (5 & 0xFF );
	public static final int blueEngineerN 	 = (BLUE_ENGINEER << 8) | (6 & 0xFF );
	public static final int blueEngineerNE 	 = (BLUE_ENGINEER << 8) | (7 & 0xFF );
	
	public static final int blueBase 	 = (BLUE_STRUCTURE << 8) | (0 & 0xFF );
	public static final int blueTurret 	 = (BLUE_STRUCTURE << 8) | (1 & 0xFF );
	public static final int blueExtractor 	 = (BLUE_STRUCTURE << 8) | (2 & 0xFF );
	public static final int blueFactory 	 = (BLUE_STRUCTURE << 8) | (3 & 0xFF );
	
	public static final int projectile	 = (EQUIPMENT << 8) | (0 & 0xFF );
	public static final int shield 		 = (EQUIPMENT << 8) | (1 & 0xFF );
	
	public static final int crystalFull 	 = (RESSOURCES << 8) | (0 & 0xFF );
	public static final int crystalHalf 	 = (RESSOURCES << 8) | (1 & 0xFF );
	public static final int crystalEmpty 	 = (RESSOURCES << 8) | (2 & 0xFF );
	public static final int moonCrystalFull	 = (RESSOURCES << 8) | (3 & 0xFF );
	public static final int moonCrystalHalf	 = (RESSOURCES << 8) | (4 & 0xFF );
	public static final int moonCrystalEmpty = (RESSOURCES << 8) | (5 & 0xFF );
	public static final int energy 		 = (RESSOURCES << 8) | (6 & 0xFF );

	public static final int landPlaceholer 	 = bitPack(PLACEHOLDER,0);
	public static final int spacePlaceholder = bitPack(PLACEHOLDER,1);

    static { // loading textures into the map
        try{

		SPRITE_MAP.put(MISSING, new ArrayList<>() ) ;
			SPRITE_MAP.get(MISSING).add(ImageIO.read(new File(world_path + "/missing.png"))) ;
		

		SPRITE_MAP.put(SPACE, new ArrayList<BufferedImage>() ) ;
			SPRITE_MAP.get(SPACE).add(ImageIO.read(new File(world_path + "/empty_space.png"))) ;                        // 01 00
			SPRITE_MAP.get(SPACE).add(ImageIO.read(new File(world_path + "/stary_space.png"))) ;                        // 01 01
		

		SPRITE_MAP.put(SURFACE, new ArrayList<>() ) ;
			SPRITE_MAP.get(SURFACE).add( ImageIO.read(new File(world_path + "/water.png"))) ;                            // 02 00
			SPRITE_MAP.get(SURFACE).add( ImageIO.read(new File(world_path + "/tree.png"))) ;                             // 02 01
			SPRITE_MAP.get(SURFACE).add( ImageIO.read(new File(world_path + "/grass.png"))) ;                            // 02 02
			SPRITE_MAP.get(SURFACE).add( ImageIO.read(new File(world_path + "/stone.png"))) ;                            // 02 03
			SPRITE_MAP.get(SURFACE).add( ImageIO.read(new File(world_path + "/mountain.png"))) ;                         // 02 04
		

		SPRITE_MAP.put(MOON, new ArrayList<>() ) ;
			SPRITE_MAP.get(MOON).add( ImageIO.read(new File(world_path + "/moon_rock.png"))) ;                           // 03 00
			SPRITE_MAP.get(MOON).add( ImageIO.read(new File(world_path + "/moon_crater.png"))) ;                         // 03 01
		

		SPRITE_MAP.put(ASTEROID, new ArrayList<>() ) ; 
			SPRITE_MAP.get(ASTEROID).add(ImageIO.read(new File(world_path + "/asteroid1.png"))) ;                       // 04 00
			SPRITE_MAP.get(ASTEROID).add(ImageIO.read(new File(world_path + "/asteroid2.png"))) ;                       // 04 01
			SPRITE_MAP.get(ASTEROID).add(ImageIO.read(new File(world_path + "/asteroid3.png"))) ;                       // 04 02


		SPRITE_MAP.put(RED_SOLDIER, new ArrayList<>() ) ;                           
			SPRITE_MAP.get(RED_SOLDIER).add( ImageIO.read(new File(entity_path + "/agents/red_ship_e.png"))) ;
			SPRITE_MAP.get(RED_SOLDIER).add( ImageIO.read(new File(entity_path + "/agents/red_ship_se.png"))) ;
			SPRITE_MAP.get(RED_SOLDIER).add( ImageIO.read(new File(entity_path + "/agents/red_ship_s.png"))) ;
			SPRITE_MAP.get(RED_SOLDIER).add( ImageIO.read(new File(entity_path + "/agents/red_ship_sw.png"))) ;
			SPRITE_MAP.get(RED_SOLDIER).add( ImageIO.read(new File(entity_path + "/agents/red_ship_w.png"))) ;
			SPRITE_MAP.get(RED_SOLDIER).add( ImageIO.read(new File(entity_path + "/agents/red_ship_nw.png"))) ;
			SPRITE_MAP.get(RED_SOLDIER).add( ImageIO.read(new File(entity_path + "/agents/red_ship_n.png"))) ;
			SPRITE_MAP.get(RED_SOLDIER).add( ImageIO.read(new File(entity_path + "/agents/red_ship_ne.png"))) ;
		

		SPRITE_MAP.put(RED_ENGINEER,new ArrayList<>()) ;
			SPRITE_MAP.get(RED_ENGINEER).add( ImageIO.read(new File(entity_path + "/agents/red_engineer_e.png"))) ;
            		SPRITE_MAP.get(RED_ENGINEER).add( ImageIO.read(new File(entity_path + "/agents/red_engineer_se.png"))) ;
            		SPRITE_MAP.get(RED_ENGINEER).add( ImageIO.read(new File(entity_path + "/agents/red_engineer_s.png"))) ;
            		SPRITE_MAP.get(RED_ENGINEER).add( ImageIO.read(new File(entity_path + "/agents/red_engineer_sw.png"))) ;
            		SPRITE_MAP.get(RED_ENGINEER).add( ImageIO.read(new File(entity_path + "/agents/red_engineer_w.png"))) ;
            		SPRITE_MAP.get(RED_ENGINEER).add( ImageIO.read(new File(entity_path + "/agents/red_engineer_nw.png"))) ;
            		SPRITE_MAP.get(RED_ENGINEER).add( ImageIO.read(new File(entity_path + "/agents/red_engineer_n.png"))) ;
            		SPRITE_MAP.get(RED_ENGINEER).add( ImageIO.read(new File(entity_path + "/agents/red_engineer_ne.png"))) ;
		

		SPRITE_MAP.put(RED_STRUCTURE, new ArrayList<>() ) ;                           
			SPRITE_MAP.get(RED_STRUCTURE).add( ImageIO.read(new File(entity_path + "/structure/red_base.png"))) ;
            		SPRITE_MAP.get(RED_STRUCTURE).add( ImageIO.read(new File(entity_path + "/structure/red_turret.png"))) ;
            		SPRITE_MAP.get(RED_STRUCTURE).add( ImageIO.read(new File(entity_path + "/structure/red_extractor.png"))) ;
	            	SPRITE_MAP.get(RED_STRUCTURE).add( ImageIO.read(new File(entity_path + "/structure/red_factory.png"))) ;


		SPRITE_MAP.put(BLUE_SOLDIER, new ArrayList<>() ) ;                          
			SPRITE_MAP.get(BLUE_SOLDIER).add( ImageIO.read(new File(entity_path + "/agents/blue_ship_e.png"))) ;
            		SPRITE_MAP.get(BLUE_SOLDIER).add( ImageIO.read(new File(entity_path + "/agents/blue_ship_se.png"))) ;
            		SPRITE_MAP.get(BLUE_SOLDIER).add( ImageIO.read(new File(entity_path + "/agents/blue_ship_s.png"))) ;
            		SPRITE_MAP.get(BLUE_SOLDIER).add( ImageIO.read(new File(entity_path + "/agents/blue_ship_sw.png"))) ;
            		SPRITE_MAP.get(BLUE_SOLDIER).add( ImageIO.read(new File(entity_path + "/agents/blue_ship_w.png"))) ;
            		SPRITE_MAP.get(BLUE_SOLDIER).add( ImageIO.read(new File(entity_path + "/agents/blue_ship_nw.png"))) ;
            		SPRITE_MAP.get(BLUE_SOLDIER).add( ImageIO.read(new File(entity_path + "/agents/blue_ship_n.png"))) ;
            		SPRITE_MAP.get(BLUE_SOLDIER).add( ImageIO.read(new File(entity_path + "/agents/blue_ship_ne.png"))) ;


		SPRITE_MAP.put(BLUE_ENGINEER, new ArrayList<>() ) ;                           
			SPRITE_MAP.get(BLUE_ENGINEER).add( ImageIO.read(new File(entity_path + "/agents/blue_engineer_e.png"))) ;
            		SPRITE_MAP.get(BLUE_ENGINEER).add( ImageIO.read(new File(entity_path + "/agents/blue_engineer_se.png"))) ;
            		SPRITE_MAP.get(BLUE_ENGINEER).add( ImageIO.read(new File(entity_path + "/agents/blue_engineer_s.png"))) ;
            		SPRITE_MAP.get(BLUE_ENGINEER).add( ImageIO.read(new File(entity_path + "/agents/blue_engineer_sw.png"))) ;
            		SPRITE_MAP.get(BLUE_ENGINEER).add( ImageIO.read(new File(entity_path + "/agents/blue_engineer_w.png"))) ;
            		SPRITE_MAP.get(BLUE_ENGINEER).add( ImageIO.read(new File(entity_path + "/agents/blue_engineer_nw.png"))) ;
            		SPRITE_MAP.get(BLUE_ENGINEER).add( ImageIO.read(new File(entity_path + "/agents/blue_engineer_n.png"))) ;
            		SPRITE_MAP.get(BLUE_ENGINEER).add( ImageIO.read(new File(entity_path + "/agents/blue_engineer_ne.png"))) ;
		

		SPRITE_MAP.put(BLUE_STRUCTURE, new ArrayList<>() ) ;                           
			SPRITE_MAP.get(BLUE_STRUCTURE).add( ImageIO.read(new File(entity_path + "/structure/blue_base.png"))) ;
            		SPRITE_MAP.get(BLUE_STRUCTURE).add( ImageIO.read(new File(entity_path + "/structure/blue_turret.png"))) ;
            		SPRITE_MAP.get(BLUE_STRUCTURE).add( ImageIO.read(new File(entity_path + "/structure/blue_extractor.png"))) ;
            		SPRITE_MAP.get(BLUE_STRUCTURE).add( ImageIO.read(new File(entity_path + "/structure/blue_factory.png"))) ;
		

		SPRITE_MAP.put(EQUIPMENT, new ArrayList<>() ) ;
            		SPRITE_MAP.get(EQUIPMENT).add(ImageIO.read(new File(entity_path + "/shield.png"))) ;
            		SPRITE_MAP.get(EQUIPMENT).add( ImageIO.read(new File(entity_path + "/projectile.png"))) ;
		

		SPRITE_MAP.put(RESSOURCES , new ArrayList<>() ) ;
		        SPRITE_MAP.get(RESSOURCES).add( ImageIO.read(new File(entity_path + "/ressources/crystal_full.png"))) ;
            		SPRITE_MAP.get(RESSOURCES).add( ImageIO.read(new File(entity_path + "/ressources/crystal_half.png"))) ;
            		SPRITE_MAP.get(RESSOURCES).add( ImageIO.read(new File(entity_path + "/ressources/crystal_empty.png"))) ;
            		SPRITE_MAP.get(RESSOURCES).add( ImageIO.read(new File(entity_path + "/ressources/moon_crystal_full.png"))) ;
            		SPRITE_MAP.get(RESSOURCES).add( ImageIO.read(new File(entity_path + "/ressources/moon_crystal_half.png"))) ;
            		SPRITE_MAP.get(RESSOURCES).add( ImageIO.read(new File(entity_path + "/ressources/moon_crystal_empty.png"))) ;
            		SPRITE_MAP.get(RESSOURCES).add( ImageIO.read(new File(entity_path + "/ressources/energy.png"))) ;
        

		SPRITE_MAP.put(PLACEHOLDER , new ArrayList<>() ) ; 
			SPRITE_MAP.get(PLACEHOLDER).add( ImageIO.read(new File(world_path + "/space_filler.png"))) ;
            		SPRITE_MAP.get(PLACEHOLDER).add( ImageIO.read(new File(world_path + "/land_filler.png"))) ;

        } catch (Exception e ){
            e.printStackTrace();
			System.exit(-1);
        }
    }
    
    private static int bitPack(int key, int pos){
    	return (key << 8) | (pos & 0xFF );
    }
    
    public static BufferedImage get(int value){
    	int key = value >> 8 ;
    	int pos = value & 0xFF ;
    	return SPRITE_MAP.get(key).get(pos) ;
    }
}
