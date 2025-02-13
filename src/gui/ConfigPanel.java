package gui ; 

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
    
    public Config configuration ; 
    public JSlider slider1 ;
    public JSlider slider2 ;
    public JButton button ;
    
    public ConfigPanel(){
        // Initialise the panel with the default configuration 
        this.configuration = new Config() ;
        // Initialise the panel attributes
        this.setPreferredSize( new Dimension(354,900)) ;
        this.setBorder(new LineBorder(Color.BLACK,5)) ;
        this.setBackground(Color.CYAN) ; 


        this.slider1 = new JSlider(JSlider.HORIZONTAL, 0, 20, 0);
        this.slider2 = new JSlider(JSlider.HORIZONTAL, 0, 20, 0);
        JLabel label1 = new JLabel("Planet Size :") ;
        JLabel label2 = new JLabel("Some other option ") ;

        this.button = new JButton("someting do") ;    
        
        //Turn on labels at major tick marks
        slider1.setMajorTickSpacing(10);
        slider1.setMinorTickSpacing(1);
        slider1.setPaintTicks(true);
        slider1.setPaintLabels(true);
        
        // Planet size 
        this.add(label1) ;
        this.add(slider1);
        
        // Other option 
        this.add(label2) ;
        this.add(slider2);

        this.add(button);

        // Option for planet size
        slider1.addChangeListener(new ChangeListener() {
        @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                configuration.planetRadius((int)source.getValue()) ;
            }
        });

    }
    

    public void paint(Graphics g)
	{
		super.paint(g) ;
    }
}