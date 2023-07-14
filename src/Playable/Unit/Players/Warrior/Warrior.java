package Playable.Unit.Players.Warrior;

import Playable.Unit.Enemies.Enemy;
import Playable.Unit.Players.Player;
import Playable.Unit.Unit;

import Playable.Position;

import java.util.List;

public class Warrior extends Player {

    public static final int WarriorAbilityHealthModifier = 10;
    public static final int WarriorHealthPoolModifier = 5;
    public static final int WarriorAttackModifier = 2;
    public static final int WarriorDefenseModifier = 1;
    public static final int WarriorRangeModifier = 3;


    AvengerShiled avengerShiled;
  
    public Warrior(char tile, String name, int healthPool, int attackPoints, int defensePoints, int abilityCoolDown) {
        super(tile, name, healthPool, attackPoints, defensePoints,WarriorRangeModifier);
        avengerShiled = new AvengerShiled((int) (health.get_healthPool() * 0.1), getDefensePoints(), abilityCoolDown);
    }
    public void accept(Unit unit){
        unit.visit(this);
    }
    @Override
    public void processStep() {
        avengerShiled.processStep();
    }
    @Override
    public void abilityCast(List<Enemy> enemiesInRange){
        if (avengerShiled.get_abilityCoolDown() > 0)
            this.messageCallback.send("Invalid option. you lost your turn.");
        else {
            Enemy enemy = avengerShiled.abilityCast(enemiesInRange, this.health, WarriorDefenseModifier);
            if (enemy!=null)
                this.onKill(enemy);
        }
    }

}
