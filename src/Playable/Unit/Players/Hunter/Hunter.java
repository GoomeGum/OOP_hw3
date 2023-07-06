package Playable.Unit.Players.Hunter;

import Playable.Unit.Enemies.Enemy;
import Playable.Unit.Players.Player;

import Playable.Position;

public class Hunter extends Player {
    private Shoot shoot;
    private final int arrowsCountModifier = 10;
    private final int attackModifier = 2;
    private final int defenseModifier = 1;

    public Hunter(char tile, String name, int healthPool,  int attackPoints, int defensePoints, int range) {
        super(tile, name, healthPool, attackPoints, defensePoints);
        shoot = new Shoot(range, super.getPlayerLevel() * arrowsCountModifier);
    }

    @Override
    public void LevelUp() {
        super.LevelUp();
        super.setAttackPoints(getAttackPoints() + attackModifier * getPlayerLevel());
        super.setDefensePoints(getDefensePoints() + defenseModifier * getPlayerLevel());
        shoot.levelUp(getPlayerLevel());
    }

    public void AbilityCast(Enemy enemy){
        shoot.shootArrow();
    }

}
