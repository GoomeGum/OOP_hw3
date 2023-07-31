package Playable.Unit.Players.Rogue;

import Playable.Unit.Enemies.Enemy;
import Playable.Unit.Health;
import Playable.Unit.Players.HeroicUnit;
import Playable.Unit.Players.Player;

import javax.swing.text.Position;
import java.util.List;

public class Rogue extends Player implements HeroicUnit {
    public FanOfKnives ability;
    public static final int RogueAttackModifier = 3;
    public static final int RogueMaxEnergyModifier = 100;
    public static final int RogueRangeModifier = 3;



    public Rogue(char tile,  String name, int healthPool, int attackPoints, int defensePoints, int cost) {
        super(tile, name, healthPool,  attackPoints, defensePoints,RogueRangeModifier);
        ability = new FanOfKnives(name,RogueMaxEnergyModifier, cost,messageCallback);
    }
    public void LevelUp(){
        ability.LevelUp();
        int NewAttack= getAttackPoints()+ (RogueAttackModifier * getPlayerLevel());
        setAttackPoints(NewAttack);
        
    }

    @Override
    public List<Enemy> abilityCast(List<Enemy> enemiesInRange){
        if (ability.getCurrentEnergy() < ability.getCost() || enemiesInRange == null)
            this.messageCallback.send("Invalid option. Please try again.");
        else {
            messageCallback.send(getName() + " used Fan Of Knives");
            List<Enemy> killed = ability.abilityCast(getName(),enemiesInRange, _attackPoints);
            for (Enemy enemy : killed) {
                this.onKill(enemy);
            }
            return killed;
        }
    return null;
    }
    @Override
    public void processStep() {
        ability.processStep();
    }
    @Override
    protected String abilityDescribe(){
        return String.format("Energy: %d/%d", ability.getCurrentEnergy(),RogueMaxEnergyModifier);
    }
}
