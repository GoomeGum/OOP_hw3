import javax.swing.text.Position;

public abstract class Tile {
    protected char _tile;
    protected Position _position;

    Tile(char tile, Position position){
        _tile = tile;
        _position = position;
    }
    public String toString(){return String.valueOf(_tile);}
}
