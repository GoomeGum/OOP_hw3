package Playable.Unit.Players.Hunter;

import Playable.Unit.Enemies.Enemy;
import Playable.Unit.Players.Player;

import Playable.Position;

import java.util.List;

public class Hunter extends Player {
    private Shoot shoot;
    private final int arrowsCountModifier = 10;
    private final int attackModifier = 2;
    private final int defenseModifier = 1;
    private static final int RangeModifier = 6;


    public Hunter(char tile, String name, int healthPool,  int attackPoints, int defensePoints, int range) {
        super(tile, name, healthPool, attackPoints, defensePoints,RangeModifier);
        shoot = new Shoot(range, super.getPlayerLevel() * arrowsCountModifier);
    }

    @Override
    public void LevelUp() {
        super.LevelUp();
        super.setAttackPoints(getAttackPoints() + attackModifier * getPlayerLevel());
        super.setDefensePoints(getDefensePoints() + defenseModifier * getPlayerLevel());
        shoot.levelUp(getPlayerLevel());
    }

    @Override
    public void abilityCast(List<Enemy> enemiesInRange){
        if (shoot.get_arrowsCount() == 0 && enemiesInRange==null)
            this.messageCallback.send("Invalid option. Please try again.");
        else {
            messageCallback.send(getName() + " used Shoot");
            Enemy theChosen = null;
            double rangeTheChosen = 0.;
            if(!enemiesInRange.isEmpty()) {
                theChosen = enemiesInRange.get(0);
                rangeTheChosen = this._position.Distance(theChosen.getPosition());
            }
            for (Enemy enemy:enemiesInRange) {
                double rangeEnemy = this._position.Distance(enemy.getPosition());
                if(rangeEnemy < rangeTheChosen)
                {
                    theChosen = enemy;
                    rangeTheChosen = rangeEnemy;
                }
            }
            if (shoot.abilityCast(theChosen, this._attackPoints) != null)
                this.onKill(theChosen);
        }
    }
    @Override
    public void processStep() {
        shoot.processStep(this.playerLevel);
    }
}
