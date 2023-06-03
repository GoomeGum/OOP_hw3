public class Position {
    private int _x;
    private int _y;

    public Position(int x,int y){
        _x = x;
        _y = y;
    }

    public int get_x() {
        return _x;
    }

    public int get_y() {
        return _y;
    }

    public void set_x(int _x) {
        this._x = _x;
    }

    public void set_y(int _y) {
        this._y = _y;
    }

    public double Distance(Position other){
        return Math.sqrt(Math.pow(_x-other.get_x(),2) + Math.pow(_y- other.get_y(),2));
    }
}
