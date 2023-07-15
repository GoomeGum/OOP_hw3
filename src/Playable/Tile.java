package Playable;

import Playable.Unit.Unit;


public abstract class Tile {
    protected char _tile;
    protected Position _position;

    public Tile(char tile){
        _tile = tile;
    }
    public Tile(char tile,Position pose){
        _tile = tile;
        _position = pose;
    }

    public Position getPosition() {
        return _position;
    }
    public void setPosition(Position pos){
        _position = pos;

    }

    public void accept(Tile tile){}

    public void accept(Unit unit){}
    public String toString(){return String.valueOf(_tile);}

    public char getTile() {
        return _tile;
    }
}
