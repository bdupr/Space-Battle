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
import java.awt.image.BufferedImage ;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JPanel;

import javax.swing.border.LineBorder;
import java.awt.Color ;
import java.awt.Dimension ;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Rename to paint/draw class, move all sprite rendering and references to texture class
 */

public class SpritePanel extends JPanel {
	
	private static ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()) ; 
	
	private int sprite_size;
	private int border_size; 
	public World world ;

	public SpritePanel(World world, int sprite_size){
		border_size = 5 ;
		
		this.setPreferredSize(new Dimension( (8*world.getDx()) + 2*border_size, (8*world.getDy()) + 2*border_size )) ;
		this.setBorder(new LineBorder(Color.BLUE,border_size));
		this.setBackground(Color.ORANGE) ; 
		
		this.sprite_size = sprite_size ;
		this.world = world ;
	
	}
	
	private void fillColoredPixels(BufferedImage image, int x, int y, BufferedImage sprite,int sprite_size){
		for( int i = x; i < x + sprite_size; i++){
			for( int j = y; j < y+ sprite_size; j++){
				image.setRGB(i,j,sprite.getRGB(i-x,j-y)) ;
			}
		}
	}
	
	public void paint(Graphics g){
		super.paint(g) ;
		//BufferedImage image = new BufferedImage( world.getDx()*sprite_size,world.getDy()*sprite_size,BufferedImage.TYPE_INT_RGB) ;
		//CountDownLatch latch = new CountDownLatch(world.getDx()) ;
		
		SpritePanel panel = this ;
		Graphics2D g2 = (Graphics2D)g;
		for ( int i = 0 ; i < world.getDx() ; i++ ){
			for(int j = 0 ; j < world.getDy() ; j++ ){
				g2.drawImage(Textures.get(world.world_grid[i][j][0]),sprite_size*i + border_size ,sprite_size*j + border_size ,sprite_size,sprite_size,panel);
				if ( world.world_grid[i][j][1] != 0 ){
					g2.drawImage(Textures.get(world.world_grid[i][j][1]),sprite_size*i + border_size ,sprite_size*j + border_size ,sprite_size,sprite_size,panel);
							}
			}
		}
		
		
		
		/*	
		for ( int i = 0 ; i < world.getDx() ; i++ ){
			final int ind = i ;
			Runnable runa = new Runnable(){
				public void run(){
					for(int j = 0 ; j < world.getDy() ; j++ ){
						fillColoredPixels(image,ind*sprite_size,j*sprite_size,Textures.get(world.world_grid[ind][j][0]),sprite_size);
						if(world.world_grid[ind][j][1] != 0)
							fillColoredPixels(image,ind*sprite_size,j*sprite_size,Textures.get(world.world_grid[ind][j][1]),sprite_size);
//							g2.drawImage(Textures.get(world.world_grid[ind][j][0]),sprite_size*ind + border_size ,sprite_size*j + border_size ,sprite_size,sprite_size,panel);
//							if ( world.world_grid[ind][j][1] != 0 ){
//								g2.drawImage(Textures.get(world.world_grid[ind][j][1]),sprite_size*ind + border_size ,sprite_size*j + border_size ,sprite_size,sprite_size,panel);
//							}
						latch.countDown() ;
					}
				}
			};
			SpritePanel.exec.submit(runa) ; 
		}
		
		try{
			latch.await() ; 
		} catch ( InterruptedException e){
			e.printStackTrace() ;
		}
		g2.drawImage(image,border_size,border_size,world.getDx()*sprite_size,world.getDy()*sprite_size,this) ;
		*/
		
		/*
		
		// Modify entitites to have an atribute taht dictates their sprite upon creation  
		for ( Ressource r : world.ressources ){
			if(r.getVisibility()){
				g2.drawImage(Textures.get(r.getSprite()),sprite_size*r.getCoordinates().getX() + border_size ,sprite_size*r.getCoordinates().getY() + border_size ,sprite_size,sprite_size,this) ; 
			}
		}
		
		// Modify entitites to have an atribute taht dictates their sprite upon creation  
		for (ArrayList<Entity> arr : world.teams ){
			for ( Entity e : arr ){ 
				if(e instanceof Structure)
    			    		g2.drawImage(Textures.get(e.getSprite()),sprite_size*e.getCoordinates().getX() + border_size ,sprite_size*e.getCoordinates().getY() + border_size ,sprite_size*2,sprite_size*2,this) ; 
    			    	else 
    			    		g2.drawImage(Textures.get(e.getSprite()),sprite_size*e.getCoordinates().getX() + border_size ,sprite_size*e.getCoordinates().getY() + border_size ,sprite_size,sprite_size,this) ;  				
			}
		}
		*/
	}

}
