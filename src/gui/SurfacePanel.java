package gui ;
import world.CelestialBodySurface ;

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

public class SurfacePanel extends JPanel {

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
	

	private Image[] sprites ;
	
	private int sprite_size;
	private CelestialBodySurface surface;

	public SurfacePanel(CelestialBodySurface surface, int sprite_size){
		this.setSize(70,70) ;
		this.setBorder(new LineBorder(Color.BLUE,5));
		this.setBackground(Color.ORANGE) ; 
		
		try
		{
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}

		this.sprite_size = sprite_size ;
		
		this.surface = surface ;

		sprites = new Image[] {
		missing	,			// 0
		waterSprite,		// 1
		treeSprite,			// 2
		grassSprite,		// 3
		emptySpaceSprite,	// 4
		starySpaceSprite, 	// 5
		stoneSprite,		// 6
		mountainSprite,	    // 7
		moon_rock,			// 8
		moon_crater  		// 9
		} ;
	}

	
	public void paint(Graphics g)
	{
		super.paint(g) ;
		Graphics2D g2 = (Graphics2D)g;
		for ( int i = 0 ; i < surface.getDx() ; i++ )
			for ( int j = 0 ; j < surface.getDy() ; j++ )
				g2.drawImage(sprites[surface.surface_grid[i][j][0]],sprite_size*i,sprite_size*j,sprite_size,sprite_size,this);
	}
	

}