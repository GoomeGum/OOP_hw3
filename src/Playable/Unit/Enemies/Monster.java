package Playable.Unit.Enemies;

import javax.swing.text.Position;

public class Monster extends Enemy{
    public int visionRange;

    public Monster(char tile,String name, int healthPool, int healthAmount, int attackPoints, int defensePoints, int visionRange) {
        super(tile, name, healthPool, healthAmount, attackPoints, defensePoints);
        this.visionRange = visionRange;
    }
}
