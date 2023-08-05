package Playable.Unit.Players.Warrior;

import Playable.Unit.Enemies.Enemy;
import Playable.Unit.Players.HeroicUnit;
import Playable.Unit.Players.Player;
import Playable.Unit.Unit;

import Playable.Position;

import java.util.ArrayList;
import java.util.List;

public class Warrior extends Player implements HeroicUnit {

    public static final int WarriorAbilityHealthModifier = 10;
    public static final int WarriorHealthPoolModifier = 5;
    public static final int WarriorAttackModifier = 2;
    public static final int WarriorDefenseModifier = 1;
    public static final int WarriorRangeModifier = 3;
    AvengerShiled avengerShiled;
  
    public Warrior(char tile, String name, int healthPool, int attackPoints, int defensePoints, int abilityCoolDown) {
        super(tile, name, healthPool, attackPoints, defensePoints,WarriorRangeModifier);
        avengerShiled = new AvengerShiled(name,(int) (health.get_healthPool() * 0.1), getDefensePoints(), abilityCoolDown,messageCallback);
    }
    public void accept(Unit unit){
        unit.visit(this);
    }
    @Override
    public void processStep() {
        avengerShiled.processStep();
    }
    @Override
    public List<Enemy> abilityCast(List<Enemy> enemiesInRange){
        if (avengerShiled.get_remainingCoolDown() > 0 || enemiesInRange==null)
            this.messageCallback.send("Invalid option. you lost your turn.");
        else {
            messageCallback.send(getName() + " used Avenger's Shield");
            Enemy enemy = avengerShiled.abilityCast(enemiesInRange, this.health, WarriorDefenseModifier);
            if (enemy!=null) {
                this.onKill(enemy);
                return new ArrayList<>() {{add(enemy);}};
            }
        }
        return null;
    }
    @Override
    protected String abilityDescribe(){
        return String.format("Cooldown: %d/%d", avengerShiled.get_remainingCoolDown(),avengerShiled.get_abilityCoolDown());
    }
    @Override
    public void LevelUp(){
        super.LevelUp();
        avengerShiled.resetCooldown();
        health.set_healthPool(health.get_healthPool() + WarriorHealthPoolModifier * getPlayerLevel());
        _attackPoints = _attackPoints + WarriorAttackModifier * getPlayerLevel();
        _defensePoints = _defensePoints + WarriorDefenseModifier * getPlayerLevel();
    }


}
