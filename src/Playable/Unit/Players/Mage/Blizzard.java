package Playable.Unit.Players.Mage;


import Playable.IMessageCallback;
import Playable.Unit.Enemies.Enemy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Blizzard {
    private int _manaPool;
    private int _currentMana;
    private int _spellPower;
    private int _hitsCounts;
    private IMessageCallback message;
    private String name;
    private final int manaModifier = 25;
    private final int spellPowerModifier = 10;
    public Blizzard(String PlayerName, int _manaPool, int _spellPower, int _hitsCounts, IMessageCallback messageCallback) {
        this._manaPool = _manaPool;
        this._currentMana = _manaPool / 4;
        this._spellPower = _spellPower;
        this._hitsCounts = _hitsCounts;
        message = messageCallback;
        name = PlayerName;
    }

    public int get_currentMana() {
        return _currentMana;
    }

    public int get_spellPower() {
        return _spellPower;
    }

    public int get_manaPool() {
        return _manaPool;
    }

    public int get_hitsCounts() {
        return _hitsCounts;
    }

    public void processStep(int level) {
        _currentMana = Math.min(_manaPool, (_currentMana+1)*level);
    }


    public void levelUp(int level) {
        set_manaPool(level * manaModifier);
        set_currentMana(Math.min(_currentMana + _manaPool/4 , _manaPool));
        set_spellPower(_spellPower + spellPowerModifier*level);
    }

    public void set_manaPool(int manaPool) {
        this._manaPool = manaPool;
    }

    public void set_currentMana(int currentMana) {
        this._currentMana = currentMana;
    }

    public void set_spellPower(int spellPower) {
        this._spellPower = spellPower;
    }
    public List<Enemy> abilityCast(List<Enemy> enemiesInRange, int manaCost){
        _currentMana = _currentMana -manaCost;
        List<Enemy> killed = new LinkedList<>();
        int hits = 0;
        while (hits < _hitsCounts && enemiesInRange.size()>0)
        {
            int randomEnemy = (int)(Math.random() * enemiesInRange.size());
            for (Enemy enemy: enemiesInRange) {
                if (randomEnemy==0) {
                    int damage = this._spellPower - enemy.defenseRoll();
                    if (damage > 0) {
                        message.send(name+" hit "+enemy.getName()+ " for "+damage+" ability damage");
                        enemy.dealDamage(damage);
                        if (enemy.isDead()) {
                            enemiesInRange.remove(enemy);
                            killed.add(enemy);
                        }
                    }
                    break;
                }
                randomEnemy--;
            }
            hits++;
        }
        return killed;
    }
}
