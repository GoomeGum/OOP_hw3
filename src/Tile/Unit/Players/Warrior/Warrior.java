package Tile.Unit.Players.Warrior;

import Tile.Unit.Players.Player;
import Tile.Unit.Unit;

import javax.swing.text.Position;

public class Warrior extends Player {
    AvengerShiled avengerShiled;
    public Warrior(char tile, Position position, String name, int healthPool, int healthAmount, int attackPoints, int defensePoints, int abilityCoolDown) {
        super(tile, position, name, healthPool, healthAmount, attackPoints, defensePoints);
        avengerShiled = new AvengerShiled((int) (health.get_healthPool() * 0.1), getDefensePoints(), abilityCoolDown);
    }
    public void accept(Unit unit){
        unit.visit(this);
    }
}
