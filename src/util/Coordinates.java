package util ;

public class Coordinates{

    private int x;
    private int y;
    
    public Coordinates(int _x, int _y){
        x = _x ;
        y = _y ;
    }

    @Override
    public String toString(){ return "["+x+","+y+"]"; }
    
    public int  getX(){ return x ; }
    public int  getY(){ return y ; }
    public void setX(int nx){ x = nx ; }
    public void setY(int ny){ y = ny ; }
    
    public void setCoordinates(int nx, int ny){
        x = nx ; 
        y = ny ;
    }
    public void setCoordinates(Coordinates coord){
        x = coord.getX() ;
        y = coord.getY() ;
    }

    public boolean equals(Coordinates coord){
        return (coord.getX() == x && coord.getY() == y) ;
    }

    public void add(int nx, int ny){
        x += nx ;
        y += ny ;
    }
    
    public void add(Coordinates coord){
        x += coord.getX() ;
        y += coord.getY() ;
    }

    public void add(Vector vector){
        x += vector.getVX() ;
        y += vector.getVY() ;
    }

    public double distanceFrom(Coordinates coord){
        return Math.sqrt( (coord.getX() - x)*(coord.getX() - x) + (coord.getY() - y)*(coord.getY() - y) ) ;
    }

    @Override
    public Coordinates clone(){
        return new Coordinates (x,y) ;
    }
}