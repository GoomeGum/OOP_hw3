package Tile.Unit.Enemies;

import javax.swing.text.Position;

public class Boss extends Enemy{
    public int visionRange;
    public int abilityFrequency;
    public int combatTicks;

    public Boss(char tile, Position position, String name, int healthPool, int healthAmount, int attackPoints,
                int defensePoints, int visionRange, int abilityFrequency) {
        super(tile, position, name, healthPool, healthAmount, attackPoints, defensePoints);
        this.visionRange = visionRange;
        this.abilityFrequency = abilityFrequency;
        combatTicks = 0;
    }
}
