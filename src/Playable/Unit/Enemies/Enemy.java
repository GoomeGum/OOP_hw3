package Playable.Unit.Enemies;

import Playable.Unit.Players.Player;
import Playable.Unit.Unit;


public class Enemy extends Unit {
    protected int experienceValue;

    public Enemy(char tile, String name, int healthPool, int attackPoints, int defensePoints) {
        super(tile, name, healthPool, attackPoints, defensePoints);
        this.experienceValue = 0;

    }

    public int getExperienceValue() {
        return experienceValue;
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