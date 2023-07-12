package Playable.Unit.Enemies;

import javax.swing.text.Position;

public class Boss extends Enemy{
    public int abilityFrequency;
    public int combatTicks;
    private int experienceReward;
    public Boss(char tile,  String name, int healthPool, int attackPoints,
                int defensePoints,int experienceReward, int visionRange, int abilityFrequency) {
        super(tile, name, healthPool, attackPoints, defensePoints,experienceReward, visionRange);
        this.abilityFrequency = abilityFrequency;
        this.experienceReward = experienceReward;
        combatTicks = 0;
    }
}
