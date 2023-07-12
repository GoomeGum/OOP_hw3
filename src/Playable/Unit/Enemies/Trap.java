package Playable.Unit.Enemies;

import javax.swing.text.Position;

public class Trap extends Enemy {
    public  int visibilityTime;
    public  int invisibilityTime;
    public  int ticksCount;
    public boolean visible;


    public Trap(char tile, String name, int healthPool,
                int attackPoints, int defensePoints,int experienceReward, int visibilityTime, int invisibilityTime) {
        super(tile, name, healthPool, attackPoints, defensePoints,experienceReward);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible = true;
    }
}
