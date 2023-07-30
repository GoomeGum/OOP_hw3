package Playable.Unit.Players.Warrior;

import Playable.IMessageCallback;
import Playable.Unit.Enemies.Enemy;
import Playable.Unit.Health;
import Playable.Unit.Players.Player;

import java.util.Iterator;
import java.util.List;

public class AvengerShiled{
    private int _hitPower;
    private int _heal;
    private int _abilityCoolDown;
    private int _remainingCoolDown;
    private String name;
    private IMessageCallback message;
    AvengerShiled(String PlayerName,int hitPower, int heal, int abilityCoolDown,IMessageCallback messageCallback) {
        _heal = heal;
        _abilityCoolDown = abilityCoolDown;
        _hitPower = hitPower;
        _remainingCoolDown = 0;
        message = messageCallback;
        name = PlayerName;

    }

    public int get_abilityCoolDown() {
        return _abilityCoolDown;
    }

    public int get_remainingCoolDown() {
        return _remainingCoolDown;
    }

    public int getHeal() {
        return _heal;
    }

    public void processStep() {
        if (_remainingCoolDown > 0)
            _remainingCoolDown--;
    }
    public Enemy abilityCast(List<Enemy> enemiesInRange,Health health, int defense) {
        _remainingCoolDown = _abilityCoolDown + 1;
        health.set_healthAmount(Math.min(health.getHealthAmount() + defense * Warrior.WarriorAbilityHealthModifier, health.get_healthPool()));
        int randomEnemy = (int)(Math.random() * enemiesInRange.size());
        for (Enemy enemy: enemiesInRange) {
            if (randomEnemy==0) {
                int damage = health.get_healthPool()/10;
                enemy.dealDamage(damage);
                if(damage > 0)
                    message.send(name+" hit "+enemy.getName()+ " for "+damage+" ability damage");
                if(enemy.isDead())
                    return enemy;
                break;
            }
            randomEnemy--;
        }
        return null;
    }

    public void resetCooldown() {
        _remainingCoolDown = 0;
    }
}
