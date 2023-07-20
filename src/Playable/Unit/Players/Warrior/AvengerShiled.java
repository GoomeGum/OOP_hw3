package Playable.Unit.Players.Warrior;

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

    AvengerShiled(int hitPower, int heal, int abilityCoolDown) {
        _heal = heal;
        _abilityCoolDown = abilityCoolDown;
        _hitPower = hitPower;
        _remainingCoolDown = 0;
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
        _remainingCoolDown = _abilityCoolDown;
        health.set_healthAmount(Math.min(health.getHealthAmount() + defense * Warrior.WarriorAbilityHealthModifier, health.get_healthPool()));
        int randomEnemy = (int)(Math.random() * enemiesInRange.size());
        for (Enemy enemy: enemiesInRange) {
            if (randomEnemy==0) {
                int damage = health.get_healthPool()/10;
                enemy.dealDamage(damage);
                if(damage > 0)
                    // TODO: send message somehow
                if(enemy.isDead())
                    return enemy;
                break;
            }
            randomEnemy--;
        }
        return null;
    }
}
