package Tile.Unit.Enemies;

import Tile.Unit.Players.Player;
import Tile.Unit.Unit;

import javax.swing.text.Position;

public class Enemy extends Unit {
    public int  experienceValue;

    public Enemy(char tile, Position position, String name, int healthPool, int healthAmount, int attackPoints, int defensePoints) {
        super(tile, position, name, healthPool, healthAmount, attackPoints, defensePoints);
        this.experienceValue = 0;

    }

    @Override
    public void processStep() {

    }

    @Override
    public void onDeath() {

    }

    @Override
    public void visit(Player p) {

    }

    @Override
    public void visit(Enemy e) {

    }

    @Override
    public void accept(Unit unit) {

    }
}
