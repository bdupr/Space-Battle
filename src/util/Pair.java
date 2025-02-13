package util ;

public class Pair<F,S> { 
    private final F first ;
    private final S second ;

    public Pair(F _first ,S _second ){
        first = _first ;
        second = _second ;  
    }

    public F getFirst(){ return first ; }
    public S getSecond(){ return second ; }

}