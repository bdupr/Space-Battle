package entities.ressources ; 

import util.Coordinates ;
import gui.Textures ;

public class Energy extends Ressource{

    public Energy(Coordinates coordinates){
        super(coordinates, Textures.energy, 38) ;
    }

}
