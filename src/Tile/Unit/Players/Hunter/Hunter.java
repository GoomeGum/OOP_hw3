package Tile.Unit.Players.Hunter;

import Tile.Empty;
import Tile.Unit.Enemies.Enemy;
import Tile.Unit.Players.Player;

import javax.swing.text.Position;

public class Hunter extends Player {
    private Shoot shoot;
    private final int arrowsCountModifier = 10;
    private final int attackModifier = 2;
    private final int defenseModifier = 1;

    public Hunter(char tile, Position position, String name, int healthPool, int healthAmount, int attackPoints, int defensePoints, int range) {
        super(tile, position, name, healthPool, healthAmount, attackPoints, defensePoints);
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
