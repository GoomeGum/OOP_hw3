package Tile.Unit.Players.Mage;

import Tile.Unit.Players.Interfaces.IAbility;

public class Blizzard implements IAbility {
    private int _manaPool;
    private int _currentMana;
    private int _spellPower;
    private int _hitsCounts;
    private int abilityRange;


    public Blizzard(int _manaPool, int _spellPower, int _hitsCounts, int abilityRange) {
        this._manaPool = _manaPool;
        this._currentMana = _manaPool/4;
        this._spellPower = _spellPower;
        this._hitsCounts = _hitsCounts;
        this.abilityRange = abilityRange;
    }

    @Override
    public void gameTick() {

    }
}
