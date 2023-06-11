package Tile.Unit.Players.Warrior;

import Tile.Unit.Players.Player;
import Tile.Unit.Unit;

import javax.swing.text.Position;

public class Warrior extends Player {
    public static final int WarriorAbilityHealthModifier = 10;
    public static final int WarriorHealthPoolModifier = 5;
    public static final int WarriorAttackModifier = 2;
    public static final int WarriorDefenseModifier = 1;




    public Warrior(char tile, Position position, String name, int healthPool, int healthAmount, int attackPoints, int defensePoints, int abilityCoolDown) {
        super(tile, position, name, healthPool, healthAmount, attackPoints, defensePoints);
        ability = new AvengerShiled((int) (health.get_healthPool() * 0.1), getDefensePoints(), abilityCoolDown);
    }
    public void accept(Unit unit){
        unit.visit(this);
    }
}
