package Playable.Unit.Enemies;

import Playable.Unit.Players.Player;
import Playable.Unit.Unit;


public class Enemy extends Unit {
    protected int experienceReward;

    public Enemy(char tile, String name, int healthPool, int attackPoints, int defensePoints,int experienceReward) {
        super(tile, name, healthPool, attackPoints, defensePoints);
        this.experienceReward = experienceReward ;

    }

    public int getExperienceValue() {
        return experienceReward;
    }

    @Override
    public void processStep() {

    }

    @Override
    public void onDeath() {

    }

    @Override
    public void visit(Player p) {
        p.defend(this);
    }

    @Override
    public void visit(Enemy e) {
        //I think they don't need to fight
    }
    // not sure its needed
    @Override
    public void accept(Unit unit) {

    }

}
