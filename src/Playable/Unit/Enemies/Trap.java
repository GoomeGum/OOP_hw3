package Playable.Unit.Enemies;

import javax.swing.text.Position;

public class Trap extends Enemy {
    public  int visibilityTime;
    public  int invisibilityTime;
    public  int ticksCount;
    public boolean visible;


    public Trap(char tile, Position position, String name, int healthPool, int healthAmount,
                int attackPoints, int defensePoints, int visibilityTime, int invisibilityTime) {
        super(tile, position, name, healthPool, healthAmount, attackPoints, defensePoints);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible = true;
    }
}
