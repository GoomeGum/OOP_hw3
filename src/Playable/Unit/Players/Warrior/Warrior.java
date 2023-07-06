package Playable.Unit.Players.Warrior;

import Playable.Unit.Players.Player;
import Playable.Unit.Unit;

import Playable.Position;

public class Warrior extends Player {

    public static final int WarriorAbilityHealthModifier = 10;
    public static final int WarriorHealthPoolModifier = 5;
    public static final int WarriorAttackModifier = 2;
    public static final int WarriorDefenseModifier = 1;

    AvengerShiled avengerShiled;
  
    public Warrior(char tile, String name, int healthPool, int attackPoints, int defensePoints, int abilityCoolDown) {
        super(tile, name, healthPool, attackPoints, defensePoints);
        avengerShiled = new AvengerShiled((int) (health.get_healthPool() * 0.1), getDefensePoints(), abilityCoolDown);
    }
    public void accept(Unit unit){
        unit.visit(this);
    }
}
