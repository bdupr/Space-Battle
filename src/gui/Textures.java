package gui ;

import java.util.LinkedHashMap ;

import java.io.File;

import javax.imageio.ImageIO;

/**
 * Contains a hashmap of the sprites used in the program
 * 
 * The sprites are seperated into different categories with each category under a different key
 * Each specific sprite is identified by 2 integers
 * 
 * mask : 00 00 
 * 
 * The first one indicates the key of the category to which the sprite belongs 
 * The second one indicates the index of the sprite within said list
 * 
 * #70 01 11 00 00 00 00 00 00 id mask
 *                       00 00 map key + texture position layer 1
 *                 00 00       map key + texture position layer 2 
 *           00 00             map key + texture position resources 
 *     00 00                   dynamic texture counter + dynamic texture counter limit/10 + dynamic texture flag 
 * #70                         header 
 *  
 */ 

public class Textures{
    private String world_path = "./assets/world";
    private String entity_path = "./assets/entities";

    // Map containing all the program's sprites
    private static LinkedHashMap sprite_map = new LinkedHashMap<Integer,List<Image>>() ;

    // Indexes of the sprite categories
    public static final int missing_sprite          = 0 ;
    public static final int space_sprites           = 1 ;
    public static final int surface_sprites         = 2 ;
    public static final int moon_sprites            = 3 ;
    public static final int asteroid_sprites        = 4 ;
    public static final int red_soldier_sprites     = 5 ;
    public static final int red_engineer_sprites    = 6 ;
    public static final int red_structure_sprites   = 7 ;
    public static final int blue_soldier_sprites    = 8 ;
    public static final int blue_engineer_sprites   = 9 ;
    public static final int blue_structure_sprites  = 10 ;
    public static final int equipment_sprites       = 11 ;
    public static final int crystal_sprites         = 12 ;
    public static final int moon_crystal_sprites    = 13 ;
    public static final int energy_sprites          = 14 ;
    
    static { // loading textures into the map
        try{
            sprite_map.put(missing_sprite, ImageIO.read(new File(world_path + "/missing.png"))) ;                           // 00 00

            sprite_map.put(space_sprites, ImageIO.read(new File(world_path + "/empty_space.png"))) ;                        // 01 00
            sprite_map.put(space_sprites, ImageIO.read(new File(world_path + "/stary_space.png"))) ;                        // 01 01

            sprite_map.put(surface_sprites, ImageIO.read(new File(world_path + "/water.png"))) ;                            // 02 00
            sprite_map.put(surface_sprites, ImageIO.read(new File(world_path + "/tree.png"))) ;                             // 02 01
            sprite_map.put(surface_sprites, ImageIO.read(new File(world_path + "/grass.png"))) ;                            // 02 02
            sprite_map.put(surface_sprites, ImageIO.read(new File(world_path + "/stone.png"))) ;                            // 02 03
            sprite_map.put(surface_sprites, ImageIO.read(new File(world_path + "/mountain.png"))) ;                         // 02 04

            sprite_map.put(moon_sprites, ImageIO.read(new File(world_path + "/moon_rock.png"))) ;                           // 03 00
            sprite_map.put(moon_sprites, ImageIO.read(new File(world_path + "/moon_crater.png"))) ;                         // 03 01

            sprite_map.put(asteroid_sprites, ImageIO.read(new File(world_path + "/asteroid1.png"))) ;                       // 04 00
            sprite_map.put(asteroid_sprites, ImageIO.read(new File(world_path + "/asteroid2.png"))) ;                       // 04 01
            sprite_map.put(asteroid_sprites, ImageIO.read(new File(world_path + "/asteroid3.png"))) ;                       // 04 02 

            sprite_map.put(red_soldier_sprites, ImageIO.read(new File(entity_path + "/agents/red_ship_e.png"))) ;
            sprite_map.put(red_soldier_sprites, ImageIO.read(new File(entity_path + "/agents/red_ship_se.png"))) ;
            sprite_map.put(red_soldier_sprites, ImageIO.read(new File(entity_path + "/agents/red_ship_s.png"))) ;
            sprite_map.put(red_soldier_sprites, ImageIO.read(new File(entity_path + "/agents/red_ship_sw.png"))) ;
            sprite_map.put(red_soldier_sprites, ImageIO.read(new File(entity_path + "/agents/red_ship_w.png"))) ;
            sprite_map.put(red_soldier_sprites, ImageIO.read(new File(entity_path + "/agents/red_ship_nw.png"))) ;
            sprite_map.put(red_soldier_sprites, ImageIO.read(new File(entity_path + "/agents/red_ship_n.png"))) ;
            sprite_map.put(red_soldier_sprites, ImageIO.read(new File(entity_path + "/agents/red_ship_ne.png"))) ;

            sprite_map.put(red_engineer_sprites, ImageIO.read(new File(entity_path + "/agents/red_engineer_e.png"))) ;
            sprite_map.put(red_engineer_sprites, ImageIO.read(new File(entity_path + "/agents/red_engineer_se.png"))) ;
            sprite_map.put(red_engineer_sprites, ImageIO.read(new File(entity_path + "/agents/red_engineer_s.png"))) ;
            sprite_map.put(red_engineer_sprites, ImageIO.read(new File(entity_path + "/agents/red_engineer_sw.png"))) ;
            sprite_map.put(red_engineer_sprites, ImageIO.read(new File(entity_path + "/agents/red_engineer_w.png"))) ;
            sprite_map.put(red_engineer_sprites, ImageIO.read(new File(entity_path + "/agents/red_engineer_nw.png"))) ;
            sprite_map.put(red_engineer_sprites, ImageIO.read(new File(entity_path + "/agents/red_engineer_n.png"))) ;
            sprite_map.put(red_engineer_sprites, ImageIO.read(new File(entity_path + "/agents/red_engineer_ne.png"))) ;

            sprite_map.put(red_structure_sprites, ImageIO.read(new File(entity_path + "/structure/red_base.png"))) ;
            sprite_map.put(red_structure_sprites, ImageIO.read(new File(entity_path + "/structure/red_turret.png"))) ;
            sprite_map.put(red_structure_sprites, ImageIO.read(new File(entity_path + "/structure/red_extractor.png"))) ;
            sprite_map.put(red_structure_sprites, ImageIO.read(new File(entity_path + "/structure/red_factory.png"))) ;

            sprite_map.put(blue_soldier_sprites, ImageIO.read(new File(entity_path + "/agents/blue_ship_e.png"))) ;
            sprite_map.put(blue_soldier_sprites, ImageIO.read(new File(entity_path + "/agents/blue_ship_se.png"))) ;
            sprite_map.put(blue_soldier_sprites, ImageIO.read(new File(entity_path + "/agents/blue_ship_s.png"))) ;
            sprite_map.put(blue_soldier_sprites, ImageIO.read(new File(entity_path + "/agents/blue_ship_sw.png"))) ;
            sprite_map.put(blue_soldier_sprites, ImageIO.read(new File(entity_path + "/agents/blue_ship_w.png"))) ;
            sprite_map.put(blue_soldier_sprites, ImageIO.read(new File(entity_path + "/agents/blue_ship_nw.png"))) ;
            sprite_map.put(blue_soldier_sprites, ImageIO.read(new File(entity_path + "/agents/blue_ship_n.png"))) ;
            sprite_map.put(blue_soldier_sprites, ImageIO.read(new File(entity_path + "/agents/blue_ship_ne.png"))) ;

            sprite_map.put(blue_engineer_sprites, ImageIO.read(new File(entity_path + "/agents/blue_engineer_e.png"))) ;
            sprite_map.put(blue_engineer_sprites, ImageIO.read(new File(entity_path + "/agents/blue_engineer_se.png"))) ;
            sprite_map.put(blue_engineer_sprites, ImageIO.read(new File(entity_path + "/agents/blue_engineer_s.png"))) ;
            sprite_map.put(blue_engineer_sprites, ImageIO.read(new File(entity_path + "/agents/blue_engineer_sw.png"))) ;
            sprite_map.put(blue_engineer_sprites, ImageIO.read(new File(entity_path + "/agents/blue_engineer_w.png"))) ;
            sprite_map.put(blue_engineer_sprites, ImageIO.read(new File(entity_path + "/agents/blue_engineer_nw.png"))) ;
            sprite_map.put(blue_engineer_sprites, ImageIO.read(new File(entity_path + "/agents/blue_engineer_n.png"))) ;
            sprite_map.put(blue_engineer_sprites, ImageIO.read(new File(entity_path + "/agents/blue_engineer_ne.png"))) ;

            sprite_map.put(blue_structure_sprites, ImageIO.read(new File(entity_path + "/structure/blue_base.png"))) ;
            sprite_map.put(blue_structure_sprites, ImageIO.read(new File(entity_path + "/structure/blue_turret.png"))) ;
            sprite_map.put(blue_structure_sprites, ImageIO.read(new File(entity_path + "/structure/blue_extractor.png"))) ;
            sprite_map.put(blue_structure_sprites, ImageIO.read(new File(entity_path + "/structure/blue_factory.png"))) ;

            sprite_map.put(equipment_sprites, ImageIO.read(new File(entity_path + "/shield.png"))) ;
            sprite_map.put(equipment_sprites, ImageIO.read(new File(entity_path + "/projectile.png"))) ;

            sprite_map.put(crystal_sprites, ImageIO.read(new File(entity_path + "/ressources/crystal_full.png"))) ;
            sprite_map.put(crystal_sprites, ImageIO.read(new File(entity_path + "/ressources/crystal_half.png"))) ;
            sprite_map.put(crystal_sprites, ImageIO.read(new File(entity_path + "/ressources/crystal_empty.png"))) ;

            sprite_map.put(moon_crystal_sprites, ImageIO.read(new File(entity_path + "/ressources/moon_crystal_full.png"))) ;
            sprite_map.put(moon_crystal_sprites, ImageIO.read(new File(entity_path + "/ressources/moon_crystal_half.png"))) ;
            sprite_map.put(moon_crystal_sprites, ImageIO.read(new File(entity_path + "/ressources/moon_crystal_empty.png"))) ;

            sprite_map.put(energy_sprites, ImageIO.read(new File(entity_path + "/ressources/energy.png"))) ;
        } catch (Exception e ){
            e.printStackTrace();
			System.exit(-1);
        }
    }
}