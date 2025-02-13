package util ;

public class Vector{

    private Coordinates origin ; 
    private Coordinates destination ;

    private int vx ;
    private int vy ;
    private double length ; 
    
    public Vector(Coordinates _origin, Coordinates _destination){
        origin = _origin ;
        destination = _destination ;

        vx = _destination.getX() - _origin.getX() ;
        vy = _destination.getY() - _origin.getY() ; 

        length = Math.sqrt( vx*vx + vy*vy ) ; 
    }

    public Coordinates getOrigin(){ return origin ; } 
    public Coordinates getDestination(){ return destination ; }
    
    public int getVX(){ return vx ; }
    public int getVY(){ return vy ; }
    public double getLength(){ return length ; }

    public void setVector(Coordinates o, Coordinates d){
        origin = o ;
        destination = d ;

        vx = d.getX() - o.getX() ;
        vy = d.getY() - o.getY() ;
    }
    
    public void div(int den){
        vx /= den ;
        vy /= den ;
    }
    public void mult(int fact){
        vx *= fact ;
        vy *= fact ;
    }

    public void norm(){
        double vxb = (double)vx ;
        vxb /= length ;
        if (vxb > 0.5){
            vx = 1 ;
        }
        else if (vxb < -0.5){
            vx = -1 ;
        }
        else{
            vx = 0 ;
        }
        
        double vyb = (double)vy ;
        vyb /= length ;
        if (vyb > 0.5){
            vy = 1 ;
        }
        else if (vyb < -0.5){
            vy = -1 ;
        }
        else{
            vy = 0 ;
        }
    }

}