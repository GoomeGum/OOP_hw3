package Playable;

public class Position {
    private int _x;
    private int _y;

    public Position(int x,int y){
        _x = x;
        _y = y;
    }

    public void UpdateY(int Ychange) {
        _y+= Ychange;
    }

    public int get_x() {
        return _x;
    }

    public int get_y() {
        return _y;
    }

    public double Distance(Position other){
        return Math.sqrt(Math.pow(_x-other.get_x(),2) + Math.pow(_y- other.get_y(),2));
    }

    public boolean equals(Position other){
        return this.get_x() == other.get_x() && this.get_y() == other.get_y();
    }

    public void UpdateX(int xchange) {
        _x+=xchange;
    }
}
