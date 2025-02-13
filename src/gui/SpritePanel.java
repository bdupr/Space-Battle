package gui ;

import world.World ;
import entities.* ;
import entities.ressources.* ;
import entities.agents.* ;
import entities.structure.* ;

import java.util.ArrayList ;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JPanel;

import javax.swing.border.LineBorder;
import java.awt.Color ;
import java.awt.Dimension ;

/**
 * Rename to paint/draw class, move all sprite rendering and references to texture class
 */

public class SpritePanel extends JPanel {

	private Image missing ;
	private Image waterSprite;
	private Image grassSprite;
	private Image treeSprite;
	private Image emptySpaceSprite;
	private Image starySpaceSprite; 
	private Image stoneSprite;
	private Image mountainSprite;
	private Image moon_rock ;
	private Image moon_crater ;
	private Image asteroid1 ;
	private Image asteroid2 ;
	private Image asteroid3 ;

	private Image red_soldier_e ; 
	private Image red_soldier_se ;
	private Image red_soldier_s ;
	private Image red_soldier_sw ;
	private Image red_soldier_w ;
	private Image red_soldier_nw ;
	private Image red_soldier_n ;
	private Image red_soldier_ne ;
	
	private Image blue_soldier_e ;
	private Image blue_soldier_se ;
	private Image blue_soldier_s ;
	private Image blue_soldier_sw ;
	private Image blue_soldier_w ;
	private Image blue_soldier_nw ;
	private Image blue_soldier_n ;
	private Image blue_soldier_ne ;

	private Image red_engineer_e ; 
	private Image red_engineer_se ; 
	private Image red_engineer_s ; 
	private Image red_engineer_sw ; 
	private Image red_engineer_w ; 
	private Image red_engineer_nw ; 
	private Image red_engineer_n ; 
	private Image red_engineer_ne ; 

	private Image blue_engineer_e ;
	private Image blue_engineer_se ;
	private Image blue_engineer_s ;
	private Image blue_engineer_sw ;
	private Image blue_engineer_w ;
	private Image blue_engineer_nw ;
	private Image blue_engineer_n ;
	private Image blue_engineer_ne ;
	
	private Image red_base ;
	private Image red_turret ;
	private Image red_extractor ;
	private Image red_factory ;
	
	private Image blue_base ;
	private Image blue_turret ;
	private Image blue_extractor ;
	private Image blue_factory ;
	
	private Image projectile ;
	private Image shield ;
	
	private Image crystal_full ;
	private Image crystal_half ;
	private Image crystal_empty ;
	
	private Image moon_crystal_full ;
	private Image moon_crystal_half ;
	private Image moon_crystal_empty ;

	private Image energy ;

	private Image[] world_sprites ;
	private Image[] entity_sprites ; 
	
	private int sprite_size;
	private int border_size; 
	public World world ;

	public SpritePanel(World world, int sprite_size){
		border_size = 5 ;
		
		this.setPreferredSize(new Dimension( 8*world.getDx() + 2*border_size, 8*world.getDy() + 2*border_size )) ;
		this.setBorder(new LineBorder(Color.BLUE,5));
		this.setBackground(Color.ORANGE) ; 
		
		try
		{ // loading assets
			//World
			missing = ImageIO.read(new File("./assets/world/missing.png")); //0
			waterSprite = ImageIO.read(new File("./assets/world/water.png")); //1
			treeSprite = ImageIO.read(new File("./assets/world/tree.png")); //2
			grassSprite = ImageIO.read(new File("./assets/world/grass.png")); //3
			emptySpaceSprite = ImageIO.read(new File("./assets/world/empty_space.png")); //4
			starySpaceSprite = ImageIO.read(new File("./assets/world/stary_space.png")); //5
			stoneSprite = ImageIO.read(new File("./assets/world/stone.png")); //6
			mountainSprite = ImageIO.read(new File("./assets/world/mountain.png")); //7
			moon_rock = ImageIO.read(new File("./assets/world/moon_rock.png")); //8
			moon_crater = ImageIO.read(new File("./assets/world/moon_crater.png")); //9
			asteroid1 = ImageIO.read(new File("./assets/world/asteroid1.png")); //10
			asteroid2 = ImageIO.read(new File("./assets/world/asteroid2.png")); //10
			asteroid3 = ImageIO.read(new File("./assets/world/asteroid3.png")); //10

			//Entities
			red_soldier_e = ImageIO.read(new File("./assets/entities/agents/red_ship_e.png")); //0
			red_soldier_se = ImageIO.read(new File("./assets/entities/agents/red_ship_se.png")); //1
			red_soldier_s = ImageIO.read(new File("./assets/entities/agents/red_ship_s.png")); //2
			red_soldier_sw = ImageIO.read(new File("./assets/entities/agents/red_ship_sw.png")); //3
			red_soldier_w = ImageIO.read(new File("./assets/entities/agents/red_ship_w.png")); //4
			red_soldier_nw = ImageIO.read(new File("./assets/entities/agents/red_ship_nw.png")); //5
			red_soldier_n = ImageIO.read(new File("./assets/entities/agents/red_ship_n.png")); //6
			red_soldier_ne = ImageIO.read(new File("./assets/entities/agents/red_ship_ne.png")); //7
			
			blue_soldier_e = ImageIO.read(new File("./assets/entities/agents/blue_ship_e.png")); //8
			blue_soldier_se = ImageIO.read(new File("./assets/entities/agents/blue_ship_se.png")); //9
			blue_soldier_s = ImageIO.read(new File("./assets/entities/agents/blue_ship_s.png")); //10
			blue_soldier_sw = ImageIO.read(new File("./assets/entities/agents/blue_ship_sw.png")); //11
			blue_soldier_w = ImageIO.read(new File("./assets/entities/agents/blue_ship_w.png")); //12
			blue_soldier_nw = ImageIO.read(new File("./assets/entities/agents/blue_ship_nw.png")); //13
			blue_soldier_n = ImageIO.read(new File("./assets/entities/agents/blue_ship_n.png")); //14
			blue_soldier_ne = ImageIO.read(new File("./assets/entities/agents/blue_ship_ne.png")); //15
			
			red_engineer_e = ImageIO.read(new File("./assets/entities/agents/red_engineer_e.png")); // 16 
			red_engineer_se = ImageIO.read(new File("./assets/entities/agents/red_engineer_se.png")); // 17
			red_engineer_s = ImageIO.read(new File("./assets/entities/agents/red_engineer_s.png")); // 18
			red_engineer_sw = ImageIO.read(new File("./assets/entities/agents/red_engineer_sw.png")); // 19
			red_engineer_w = ImageIO.read(new File("./assets/entities/agents/red_engineer_w.png")); // 20
			red_engineer_nw = ImageIO.read(new File("./assets/entities/agents/red_engineer_nw.png")); // 21
			red_engineer_n = ImageIO.read(new File("./assets/entities/agents/red_engineer_n.png")); // 22
			red_engineer_ne = ImageIO.read(new File("./assets/entities/agents/red_engineer_ne.png")); // 23

			blue_engineer_e = ImageIO.read(new File("./assets/entities/agents/blue_engineer_e.png")); // 24 
			blue_engineer_se = ImageIO.read(new File("./assets/entities/agents/blue_engineer_se.png")); // 25
			blue_engineer_s = ImageIO.read(new File("./assets/entities/agents/blue_engineer_s.png")); // 26
			blue_engineer_sw = ImageIO.read(new File("./assets/entities/agents/blue_engineer_sw.png")); // 27
			blue_engineer_w = ImageIO.read(new File("./assets/entities/agents/blue_engineer_w.png")); // 28
			blue_engineer_nw = ImageIO.read(new File("./assets/entities/agents/blue_engineer_nw.png")); // 29
			blue_engineer_n = ImageIO.read(new File("./assets/entities/agents/blue_engineer_n.png")); // 30
			blue_engineer_ne = ImageIO.read(new File("./assets/entities/agents/blue_engineer_ne.png")); // 31

			red_base = ImageIO.read(new File("./assets/entities/structure/red_base.png")); //32
			red_turret = ImageIO.read(new File("./assets/entities/structure/red_turret.png")); //33
			red_extractor = ImageIO.read(new File("./assets/entities/structure/red_extractor.png")); //34
			red_factory = ImageIO.read(new File("./assets/entities/structure/red_factory.png")); //35

			blue_base = ImageIO.read(new File("./assets/entities/structure/blue_base.png")); //36
			blue_turret = ImageIO.read(new File("./assets/entities/structure/blue_turret.png")); //37
			blue_extractor = ImageIO.read(new File("./assets/entities/structure/blue_extractor.png")); //38
			blue_factory = ImageIO.read(new File("./assets/entities/structure/blue_factory.png")); //39

			shield = ImageIO.read(new File("./assets/entities/shield.png")); //40
			projectile = ImageIO.read(new File("./assets/entities/projectile.png")); //41

			crystal_full = ImageIO.read(new File("./assets/entities/ressources/crystal_full.png")); //42
			crystal_half = ImageIO.read(new File("./assets/entities/ressources/crystal_half.png")); //43
			crystal_empty = ImageIO.read(new File("./assets/entities/ressources/crystal_empty.png")); //44

			moon_crystal_full = ImageIO.read(new File("./assets/entities/ressources/moon_crystal_full.png")); //45
			moon_crystal_half = ImageIO.read(new File("./assets/entities/ressources/moon_crystal_half.png")); //46
			moon_crystal_empty = ImageIO.read(new File("./assets/entities/ressources/moon_crystal_empty.png")); //47

			energy = ImageIO.read(new File("./assets/entities/ressources/energy.png")); //48
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}

		this.sprite_size = sprite_size ;
		this.world = world ;

		world_sprites = new Image[] {
		missing	,			// 0
		
		waterSprite,		// 1
		treeSprite,			// 2
		grassSprite,		// 3
		
		emptySpaceSprite,	// 4
		starySpaceSprite, 	// 5
		
		stoneSprite,		// 6
		mountainSprite,	    // 7
		
		moon_rock,			// 8
		moon_crater,  		// 9
		
		asteroid1,			// 10
		asteroid2,			// 11
		asteroid3			// 12
		} ;

		entity_sprites = new Image[] {
		red_soldier_e,			// 0
		red_soldier_se,			// 1
		red_soldier_s,			// 2
		red_soldier_sw,			// 3
		red_soldier_w,			// 4
		red_soldier_nw,			// 5
		red_soldier_n,			// 6
		red_soldier_ne,			// 7
		blue_soldier_e,			// 8
		blue_soldier_se,		// 9
		blue_soldier_s,			// 10
		blue_soldier_sw,		// 11
		blue_soldier_w,			// 12
		blue_soldier_nw,		// 13
		blue_soldier_n,			// 14
		blue_soldier_ne,		// 15
		red_engineer_e,			// 16	
		red_engineer_se,		// 17
		red_engineer_s,			// 18
		red_engineer_sw,		// 19
		red_engineer_w,			// 20
		red_engineer_nw,		// 21
		red_engineer_n,			// 22
		red_engineer_ne,		// 23
		blue_engineer_e,		// 24
		blue_engineer_se,		// 25
		blue_engineer_s,		// 26
		blue_engineer_sw,		// 27
		blue_engineer_w,		// 28
		blue_engineer_nw,		// 29
		blue_engineer_n,		// 30
		blue_engineer_ne,		// 31
		red_base,				// 32
		red_turret,				// 33
		red_extractor,			// 34
		red_factory,			// 35
		blue_base,				// 36
		blue_turret,			// 37
		blue_extractor,			// 38
		blue_factory,			// 39
		shield,					// 40
		projectile,				// 41
		crystal_full,			// 42
		crystal_half,			// 43
		crystal_empty,			// 44
		moon_crystal_full,      // 45
		moon_crystal_half,      // 46
		moon_crystal_empty,		// 47
		energy					// 48
		} ;
	}
	
	public void paint(Graphics g){
		super.paint(g) ;
		Graphics2D g2 = (Graphics2D)g;
		for ( int i = 0 ; i < world.getDx() ; i++ )
			for ( int j = 0 ; j < world.getDy() ; j++ ){
				g2.drawImage(world_sprites[world.world_grid[i][j][0]],sprite_size*i + border_size ,sprite_size*j + border_size ,sprite_size,sprite_size,this);
				if ( world.world_grid[i][j][1] != 0 )
					g2.drawImage(world_sprites[world.world_grid[i][j][1]],sprite_size*i + border_size ,sprite_size*j + border_size ,sprite_size,sprite_size,this);
			}
		
		for ( Ressource r : world.ressources ){
			if(r.getVisibility()){
				if(r instanceof Crystal)
					g2.drawImage(entity_sprites[42],sprite_size*r.getCoordinates().getX() + border_size ,sprite_size*r.getCoordinates().getY() + border_size ,sprite_size,sprite_size,this) ; 
				else if( r instanceof MoonCrystal)
					g2.drawImage(entity_sprites[45],sprite_size*r.getCoordinates().getX() + border_size ,sprite_size*r.getCoordinates().getY() + border_size ,sprite_size,sprite_size,this) ; 
				else 
					g2.drawImage(entity_sprites[48],sprite_size*r.getCoordinates().getX() + border_size ,sprite_size*r.getCoordinates().getY() + border_size ,sprite_size,sprite_size,this) ; 

			}
		}

		for (ArrayList<Entity> arr : world.teams ){
			for ( Entity e : arr ){ 
				if(e instanceof Structure){
    			    if(e instanceof Turret){
    			        Turret t = ((Turret)(e)) ;
    			        g2.drawImage(entity_sprites[t.sprite],sprite_size*t.getCoordinates().getX() + border_size ,sprite_size*t.getCoordinates().getY() + border_size ,sprite_size*2,sprite_size*2,this) ; 
    			    }
    			    else if(e instanceof Extractor){
    			        Extractor ex = ((Extractor)(e)) ;
    			        g2.drawImage(entity_sprites[ex.sprite],sprite_size*ex.getCoordinates().getX() + border_size ,sprite_size*ex.getCoordinates().getY() + border_size ,sprite_size*2,sprite_size*2,this) ; 
    			    }
    			    else if(e instanceof Factory){
    			        Factory f = ((Factory)(e)) ;
    			        g2.drawImage(entity_sprites[f.sprite],sprite_size*f.getCoordinates().getX() + border_size ,sprite_size*f.getCoordinates().getY() + border_size ,sprite_size*2,sprite_size*2,this) ; 
    			    }
    			    else{
    			        Base b = ((Base)(e)) ;
    			        g2.drawImage(entity_sprites[b.sprite],sprite_size*b.getCoordinates().getX() + border_size ,sprite_size*b.getCoordinates().getY() + border_size ,sprite_size*2,sprite_size*2,this) ; 
    			    }
    			}
				if (e instanceof Agent){
					if(e instanceof ShieldSoldier){
						ShieldSoldier ss = ((ShieldSoldier)(e)) ;
						g2.drawImage(entity_sprites[ss.getSprite()],sprite_size*ss.getCoordinates().getX() + border_size ,sprite_size*ss.getCoordinates().getY() + border_size ,sprite_size,sprite_size,this) ;
						if(ss.power != 0){
							g2.drawImage(entity_sprites[40],sprite_size*ss.getCoordinates().getX() + border_size ,sprite_size*ss.getCoordinates().getY() + border_size ,sprite_size,sprite_size,this) ;
						}
					}
					else if (e instanceof Engineer){
						Engineer eng = ((Engineer)(e));
						g2.drawImage(entity_sprites[eng.getSprite()],sprite_size*eng.getCoordinates().getX() + border_size ,sprite_size*eng.getCoordinates().getY() + border_size ,sprite_size,sprite_size,this) ;
					}
					else{
						Soldier s = ((Soldier)(e)) ;
						g2.drawImage(entity_sprites[s.getSprite()],sprite_size*s.getCoordinates().getX() + border_size ,sprite_size*s.getCoordinates().getY() + border_size ,sprite_size,sprite_size,this) ;
					}	
				}
				else if (e instanceof Projectile){
					Projectile p = ((Projectile)(e)) ;
					g2.drawImage(entity_sprites[41],sprite_size*p.getCoordinates().getX() + border_size ,sprite_size*p.getCoordinates().getY() + border_size ,sprite_size,sprite_size,this) ; 
				}
				
			}
		}
	}

}