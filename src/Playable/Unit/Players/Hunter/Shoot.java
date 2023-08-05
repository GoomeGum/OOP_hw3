package Playable.Unit.Players.Hunter;

import Playable.IMessageCallback;
import Playable.Unit.Enemies.Enemy;

import java.util.List;

public class Shoot{
    public int _range;
    public int _arrowsCount;
    public int _ticksCount = 0;
    private IMessageCallback message;
    private final int arrowsCountModifier = 10;
    private String name;

    public Shoot(String PlayerName, int range, int arrowsCount,IMessageCallback messageCallback) {
        _range = range;
        _arrowsCount = arrowsCount;
        message = messageCallback;
        name = PlayerName;
    }

    public void set_arrowsCount(int _arrowsCount) {
        this._arrowsCount = _arrowsCount;
    }

    public int get_arrowsCount() {
        return _arrowsCount;
    }

    public void levelUp(int playerLevel) {
        set_arrowsCount(get_arrowsCount() + arrowsCountModifier * playerLevel);
    }

    public Enemy abilityCast(Enemy enemy, int attackPoints) {
        _arrowsCount--;
        if (enemy != null)
        {
            int damage = attackPoints - enemy.defenseRoll();
            if (damage > 0) {
                message.send(name+" hit "+enemy.getName()+ " for "+damage+" ability damage");
                enemy.dealDamage(damage);
                if (enemy.isDead()) {
                    return enemy;
                }
            }
        }
        return null;

    }
    public void processStep(int level)
    {
        if(_ticksCount==10)
        {
            _arrowsCount = _arrowsCount+level;
            _ticksCount = 0;
        }
        else
            _ticksCount ++;
    }
}
