package gui ;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class TitleScreen extends JPanel{

    public TitleScreen(){

        JLabel label1 = new JLabel("Text here") ;
        JLabel label2 = new JLabel("Press 'Space' To Begin") ;
        this.add(label1) ;
        this.add(label2) ;
    }

}