package Playable.Unit.Enemies;

import Playable.Unit.Players.Player;
import Playable.Unit.Unit;


public class Enemy extends Unit {
    protected int experienceReward;
    protected int visionRange;


    public Enemy(char tile, String name, int healthPool, int attackPoints, int defensePoints,int experienceReward, int visionRange) {
        super(tile, name, healthPool, attackPoints, defensePoints);
        this.experienceReward = experienceReward ;
        this.visionRange= visionRange;

    }

    public int getExperienceValue() {
        return experienceReward;
    }

    public int getVisionRange() {
        return visionRange;
    }
    public char enemyMove(Player player)
    {
        return 'q';
    }
    @Override
    public void processStep() {

    }

    @Override
    public void onDeath() {

    }

    @Override
    public void visit(Player p) {
        this.attack(p);
        p.defend(this);
    }

    @Override
    public void visit(Enemy e) {
    }
    // not sure its needed
    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }


}
