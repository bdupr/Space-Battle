package gui ;

import world.StartupWorld ;


import javax.swing.JComponent ;
import javax.swing.JSlider ;
import javax.swing.JFrame ;
import javax.swing.JPanel ;
import javax.swing.event.ChangeListener ;
import javax.swing.event.ChangeEvent ;
import javax.swing.border.LineBorder ;
import javax.swing.AbstractAction ;
import javax.swing.KeyStroke ;
import javax.swing.ActionMap ;
import javax.swing.InputMap ;

import java.awt.BorderLayout ;
import java.awt.Dimension ;
import java.awt.Color ;
import java.awt.event.ComponentAdapter ;
import java.awt.event.ComponentEvent ;
import java.awt.event.ActionListener ;
import java.awt.event.ActionEvent ;
import java.awt.event.KeyAdapter ;
import java.awt.event.KeyEvent ;

/* 
 * Class responsible for displaying all graphical elements of the simulation 
 */

public class GUI extends JFrame {

    private SpritePanel world_panel ; // JPanel displaying the world of the simulation
    private SurfacePanel surface_panel ; 
    private ConfigPanel config_panel ; 
    private StartupPanel startup_panel ; 
    private StartupWorld startup_world ; 

    private boolean initStatus ;

    public GUI(SpritePanel world_panel, int sizex, int sizey, SurfacePanel surface_panel, ConfigPanel config_panel){ 
		super("World of Sprite") ;
        
        this.world_panel = world_panel ;
        this.surface_panel = surface_panel; 
        this.config_panel = config_panel ;

        this.startup_world = new StartupWorld(config_panel.configuration) ;
        this.startup_panel = new StartupPanel(startup_world) ;
        
        initStatus = false ;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; //Closes program when window is closed
		this.setSize(sizex,sizey);  //Set the size of the window 
        this.setLocationRelativeTo(null);  //Centers the window 
        this.setLayout(new BorderLayout()) ;
        

        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getRootPane().getActionMap();
        // --------------------------------------------------------------------------------------
        // Title screen
        
        TitleScreen title_panel = new TitleScreen();
        this.add(title_panel);
        
        // Define the key binding to pass the title screen
        KeyStroke spacePressed = KeyStroke.getKeyStroke("SPACE") ;
        // Map the key binding 
        inputMap.put(spacePressed, "passTitleScreen") ;
        // Define key behavior
        actionMap.put("passTitleScreen", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e ){
                System.out.println("Passed Title Screen");
                inputMap.remove(spacePressed);
                getContentPane().remove(title_panel);
                
                getContentPane().add(startup_panel,BorderLayout.WEST);
                getContentPane().add(config_panel,BorderLayout.EAST);
                
                getContentPane().revalidate();
                getContentPane().repaint();
            }
        }) ;

        // --------------------------------------------------------------------------------------
        // Configuration portion 

        config_panel.button.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ){
                System.out.println("button works") ;
                
                
                getContentPane().remove(config_panel);
                getContentPane().remove(startup_panel);

                getContentPane().add(world_panel,BorderLayout.WEST);

                initStatus = true ;
                /*
                remove configpanel
                remove startuppanel
                add world panel
                add stat panel
                add all key bindings
                */
                
                getContentPane().revalidate();
                getContentPane().repaint();
            }
        });
        
        // --------------------------------------------------------------------------------------

        

        // Define the key bindings for the GUI
        KeyStroke aPressed = KeyStroke.getKeyStroke("A") ;
        
        // Map key bindings
        inputMap.put(aPressed, "changeView") ;
        
        // Define key behavior
        actionMap.put("changeView", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e ){
                System.out.println("Key 'A' pressed, toggling view");
                if (surface_panel.isVisible()) {
                    // Remove surface_panel and show world_panel
                    getContentPane().remove(surface_panel);
                    surface_panel.setVisible(false);
                    getContentPane().add(world_panel);
                    world_panel.setVisible(true);
                    
                } else {
                    // Remove world_panel and show surface_panel
                    getContentPane().remove(world_panel);
                    world_panel.setVisible(false);
                    getContentPane().add(surface_panel);
                    surface_panel.setVisible(true);
                }
                getContentPane().revalidate();
                getContentPane().repaint();
            }
        }) ;

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setResizable(true) ; // Prevents the user from resizing the window 
        this.setVisible(true);
    }
    
    // Getters
    public boolean getInitStatus(){ return initStatus ; }  
    //public World getWorld(){ return world ; }                                  
    public StartupWorld getStartupWorld(){ return startup_world ; }                                  

}
