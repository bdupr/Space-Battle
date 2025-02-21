package gui ; 

import java.util.ArrayList ;
import java.util.Hashtable ;

import javax.swing.JPanel ;
import javax.swing.JSlider ;
import javax.swing.JLabel ;
import javax.swing.JButton ;
import javax.swing.event.ChangeEvent ;
import javax.swing.event.ChangeListener ;
import javax.swing.border.LineBorder;

import java.awt.Graphics;
import java.awt.Color ;
import java.awt.Dimension ;

public class ConfigPanel extends JPanel{
    
    private int border_size ;	// size of the panel's border
    
    public Config configuration ; 
    
    public JSlider slider1 ;	// responsible for the planet's size 
    public JSlider slider2 ;	// responsible for the planet's rotation speed 
    public JSlider slider3 ;	// responsible for the moon's size
    public JSlider slider4 ;	// responsible for the moon's orbit speed 
    public JSlider slider5 ;	// responsible for the distance between the moon and the planet

    public ArrayList<JLabel> labels ; // All of the panel's labels 
    public ArrayList<JSlider> sliders ; // All of the panel's sliders 
    
    public JButton button ;	// starts the program with the current configuration
    
    public ConfigPanel(){
        // Initialise the panel with the default configuration 
        this.configuration = new Config() ;
        // Initialise the panel attributes
        this.border_size = 5 ;
        this.setPreferredSize( new Dimension(420+border_size*2 ,900)) ;
        this.setBorder(new LineBorder(Color.BLACK,border_size)) ;
        this.setBackground(Color.CYAN) ; 
	this.setLayout(null) ;

	labels = new ArrayList<>() ;
	sliders = new  ArrayList<>() ;

	sliders.add( new JSlider(JSlider.HORIZONTAL, configuration.MIN_PLANET_RADIUS, configuration.MAX_PLANET_RADIUS, configuration.MIN_PLANET_RADIUS) ) ; // planet size
        sliders.add( new JSlider(JSlider.HORIZONTAL, 1, 8, 1) ) ; // planet rotation speed 
        sliders.add( new JSlider(JSlider.HORIZONTAL, 0, 20, 0) ) ; // moon size 
        sliders.add( new JSlider(JSlider.HORIZONTAL, 1, 8, 1) ) ; // moon orbit speed 
        sliders.add( new JSlider(JSlider.HORIZONTAL, 0, 20, 0) ) ; // moon planet distance 
	
	labels.add( new JLabel("Planet Size :") ) ;
	labels.add( new JLabel("Planet Rotation Speed :") ) ;
	labels.add( new JLabel("Moon Size :") ) ;
	labels.add( new JLabel("Moon Orbit Speed :") ) ;
	labels.add( new JLabel("Planet - Moon Distance :") ) ;

        this.button = new JButton("Confirm") ;
        
        
        int spacing =  20 ;
        for( int i = 0 ; i < sliders.size() ; i++){
		// configure the sliders
		sliders.get(i).setMinorTickSpacing(1);
        	sliders.get(i).setPaintTicks(true);
        	sliders.get(i).setPaintLabels(true);
        	sliders.get(i).setSnapToTicks(true);
        	//
        	Hashtable<Integer,JLabel> tickLabels = new Hashtable<>() ;
        	if(i == 1 || i == 3){
        		tickLabels.put( sliders.get(i).getMaximum(), new JLabel("Min")) ;
        		tickLabels.put( sliders.get(i).getMinimum(), new JLabel("Max")) ;
        		sliders.get(i).setInverted(true) ;
        	} else {
			tickLabels.put( sliders.get(i).getMinimum(), new JLabel("Min")) ;
        		tickLabels.put( sliders.get(i).getMaximum(), new JLabel("Max")) ;
        	}
        	
        	sliders.get(i).setLabelTable(tickLabels) ;
        	
        	// add the sliders and their labels  
        	this.add(labels.get(i)) ;
        	this.add(sliders.get(i)) ;
        	
        	// Set the size and location of the components 
        	labels.get(i).setSize(250,40) ;
        	sliders.get(i).setSize(250,40);
        	
        	labels.get(i).setLocation(50,spacing) ;
        	spacing += 40 ;
        	sliders.get(i).setLocation(50,spacing) ;
        	spacing += 80 ;
        }
	
	// add the confirm button 
        this.add(button);
        button.setSize(200,50) ;
        button.setLocation(50,spacing) ;

        addSliderBehavior() ;

    }
    
	private void addSliderBehavior(){
		// Option for planet size
		sliders.get(0).addChangeListener(new ChangeListener() {
		@Override
		    public void stateChanged(ChangeEvent e) {
		        JSlider source = (JSlider)e.getSource();
		        configuration.planetRadius((int)source.getValue()) ;
		        configuration.modifyPlanet() ;
		    }
		});
		
		// Option for planet rotation speed 
		sliders.get(1).addChangeListener(new ChangeListener() {
		@Override
		    public void stateChanged(ChangeEvent e) {
		        JSlider source = (JSlider)e.getSource();
		        configuration.planetRotationFrequency((int)source.getValue()) ;
		    }
		});
		
		// Option for moon size
		sliders.get(2).addChangeListener(new ChangeListener() {
		@Override
		    public void stateChanged(ChangeEvent e) {
		        JSlider source = (JSlider)e.getSource();
		        configuration.moonRadius((int)source.getValue()) ;
		        configuration.modifyMoon() ;
		    }
		});
		
		// Option for moon orbit speed 
		sliders.get(3).addChangeListener(new ChangeListener() {
		@Override
		    public void stateChanged(ChangeEvent e) {
		        JSlider source = (JSlider)e.getSource();
		        configuration.moonOrbitFrequency((int)source.getValue()) ;
		    }
		});
		
		// Option for planet - moon distance
		sliders.get(4).addChangeListener(new ChangeListener() {
		@Override
		    public void stateChanged(ChangeEvent e) {
		        JSlider source = (JSlider)e.getSource();
		        configuration.moonDistanceFromPlanet((int)source.getValue()) ;
		        configuration.modifyMoon() ;
		    }
		});
	}

    public void paint(Graphics g){
	super.paint(g) ;
    }
}
