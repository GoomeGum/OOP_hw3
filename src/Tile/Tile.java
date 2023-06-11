package Tile;

import Tile.Unit.Unit;

import javax.swing.text.Position;

public abstract class Tile {
    protected char _tile;
    protected Position _position;

    public Tile(char tile, Position position){
        _tile = tile;
        _position = position;
    }
    public void accept(Tile tile){}

    public void accept(Unit unit){}
    public String toString(){return String.valueOf(_tile);}
}
