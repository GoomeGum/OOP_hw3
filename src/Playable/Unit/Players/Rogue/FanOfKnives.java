package Playable.Unit.Players.Rogue;

import Playable.Unit.Enemies.Enemy;

import java.util.LinkedList;
import java.util.List;

public class FanOfKnives {
    private int cost;
    private int currentEnergy;
    public static final int FanOfKnivesCurrentEnergyModifier = 100;

    public  FanOfKnives(int energy, int cost){
        this.cost = cost;
        this.currentEnergy = energy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public int getCost() {
        return cost;
    }

    public void LevelUp(){
        this.currentEnergy += FanOfKnivesCurrentEnergyModifier;
    }

    public void processStep(){
        currentEnergy = Math.min(currentEnergy+ 10, FanOfKnivesCurrentEnergyModifier);
    }


    public List<Enemy> abilityCast(List<Enemy> enemiesInRange, int attackPoints)
    {
        if (enemiesInRange.isEmpty() || currentEnergy < cost)
            return null;
        currentEnergy -= cost;
        List<Enemy> killed = new LinkedList<>();
        int randomEnemy = (int)(Math.random() * enemiesInRange.size());
        for (Enemy enemy: enemiesInRange) {
            if (randomEnemy==0) {
                int damage = attackPoints - enemy.defenseRoll();
                if (damage > 0) {
                    enemy.dealDamage(damage);
                    if (enemy.isDead()) {
                        killed.add(enemy);
                        enemiesInRange.remove(enemy);
                    }
                }
                break;
            }
            randomEnemy--;
        }
        return killed;
    }





}
