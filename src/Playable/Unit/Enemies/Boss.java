package Playable.Unit.Enemies;

import javax.swing.text.Position;

public class Boss extends Enemy{
    public int visionRange;
    public int abilityFrequency;
    public int combatTicks;

    public Boss(char tile,  String name, int healthPool, int attackPoints,
                int defensePoints, int visionRange, int abilityFrequency) {
        super(tile, name, healthPool, attackPoints, defensePoints);
        this.visionRange = visionRange;
        this.abilityFrequency = abilityFrequency;
        combatTicks = 0;
    }
}
