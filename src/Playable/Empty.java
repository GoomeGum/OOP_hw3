package Playable;

import Playable.Unit.Unit;

public class Empty extends Tile {
    public Empty(char tile, Position position) {
        super(tile, position);
    }
    public void accept(Unit unit){
        unit.visit(this);
    }
}
