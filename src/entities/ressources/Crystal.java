package entities.ressources ;

import util.Coordinates ;
import gui.Textures ;

public class Crystal extends Ressource{

    public Crystal(Coordinates coordinates){
        super(coordinates, Textures.crystalFull, 25) ;
    }

}
