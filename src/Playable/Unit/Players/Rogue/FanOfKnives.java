package Playable.Unit.Players.Rogue;

import Playable.IMessageCallback;
import Playable.Unit.Enemies.Enemy;

import java.util.LinkedList;
import java.util.List;

public class FanOfKnives {
    private int cost;
    private int currentEnergy;
    private IMessageCallback message;
    public static final int FanOfKnivesCurrentEnergyModifier = 100;

    private String name;

    public  FanOfKnives(String PlayerName,int energy, int cost,IMessageCallback messageCallback){
        this.cost = cost;
        this.currentEnergy = energy;
        message = messageCallback;
        name = PlayerName;
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
        currentEnergy = Math.min(currentEnergy+ 10, 100);
    }


    public List<Enemy> abilityCast(String name,List<Enemy> enemiesInRange, int attackPoints)
    {
        currentEnergy -= cost;
        List<Enemy> killed = new LinkedList<>();
        int randomEnemy = (int)(Math.random() * enemiesInRange.size());
        for (Enemy enemy: enemiesInRange) {
            if (randomEnemy==0) {
                int damage = attackPoints - enemy.defenseRoll();
                if (damage > 0) {
                    message.send(name+" hit "+enemy.getName()+ " for "+damage+" ability damage");
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
