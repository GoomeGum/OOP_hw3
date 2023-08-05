package Playable.Unit.Enemies;

import Playable.Unit.Players.Player;

import javax.swing.text.Position;

public class Boss extends Monster{
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

    @Override
    public char enemyMove(Player player)
    {
        if (this.getPosition().Distance(player.getPosition()) < this.getVisionRange())
        {
            if (combatTicks == abilityFrequency)
            {
                combatTicks = 0;
                int damage = getAttackPoints()-player.defenseRoll();
                if (damage>0)
                    player.dealDamage(damage);
                return 'q';
            }
            else
            {
                combatTicks++;
                return super.enemyMove(player);
            }
        }
        else{
            return super.enemyMoveRandom();
        }

    }
}
