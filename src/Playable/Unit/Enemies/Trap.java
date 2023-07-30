package Playable.Unit.Enemies;

import Playable.Position;
import Playable.Unit.Players.Player;

public class Trap extends Enemy {
    public  int visibilityTime;
    public  int invisibilityTime;
    public  int ticksCount;
    public boolean visible;


    public Trap(char tile, String name, int healthPool,
                int attackPoints, int defensePoints,int experienceReward, int visibilityTime, int invisibilityTime) {
        super(tile, name, healthPool, attackPoints, defensePoints,experienceReward, 0);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible = true;
    }
    @Override
    public char enemyMove(Player player)
    {
        Position playerP = player.getPosition();
        visible = ticksCount < visibilityTime;
        if(ticksCount == (visibilityTime + invisibilityTime))
            ticksCount = 0;
        else
            ticksCount ++;
        if (this.getPosition().Distance(playerP) < 2)
            this.visit(player);
        if(visible)
            this._tile = 'Q';
        else
            this._tile = '.';
        return 'q';
    }
}
