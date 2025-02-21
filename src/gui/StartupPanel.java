package gui ;

import world.StartupWorld ;

import java.util.ArrayList ;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Color ;
import java.awt.Dimension ;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;



public class StartupPanel extends JPanel{
    
    private int tick ;  

    public StartupWorld startup_world ;

    public int sprite_size ; 
    public int border_size ; 
    
    public StartupPanel(StartupWorld startup_world){
        this.startup_world = startup_world ; 
        border_size = 5 ; 
        sprite_size = 8 ;

        this.setPreferredSize(new Dimension( 8*startup_world.getDx() + 2*border_size, 8*startup_world.getDy() + 2*border_size )) ;
		this.setBorder(new LineBorder(Color.BLUE,5));
		this.setBackground(Color.ORANGE) ; 

    }

    

    public void paint(Graphics g){
        super.paint(g) ;
        Graphics2D g2 = (Graphics2D)g ;
        for (int i = 0 ; i < startup_world.getDx() ; i++ ){
            for (int j = 0 ; j < startup_world.getDy() ; j++ ){
                g2.drawImage(Textures.get(startup_world.startup_grid[i][j]),sprite_size*i + border_size ,sprite_size*j + border_size ,sprite_size,sprite_size,this);
            
            }
        }
    }
}
