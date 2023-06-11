package Tile.Unit.Players.Warrior;

import Tile.Unit.Health;
import Tile.Unit.Players.Interfaces.IAbility;
import Tile.Unit.Players.Modifiers;
import Tile.Unit.Players.Player;

public class AvengerShiled implements IAbility {
    private int _hitPower;
    private int _heal;
    private int _abilityCoolDown;
    private int _remainingCoolDown;
    AvengerShiled(int hitPower,int heal,int abilityCoolDown){
        _heal = heal;
        _abilityCoolDown = abilityCoolDown;
        _hitPower = hitPower;
        _remainingCoolDown = 0;
    }

    public int getHeal() {
        return _heal;
    }
    @Override
    public void gameTick(){
        if(_remainingCoolDown > 0)
            _remainingCoolDown--;
    }
    public void abilityCast(Health health, int defense, Player player){
        _remainingCoolDown = _abilityCoolDown;
        health.set_healthAmount(Math.min(health.getHealthAmount()+defense* Modifiers.WorriorAbilityHealthModifier,health.get_healthPool()));
        if(player ==null)
            return;
        player.defend();
    }
}
