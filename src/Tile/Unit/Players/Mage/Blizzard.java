package Tile.Unit.Players.Mage;


public class Blizzard {
    private int _manaPool;
    private int _currentMana;
    private int _spellPower;
    private int _hitsCounts;
    private int abilityRange;

    private final int manaModifier = 25;
    private final int spellPowerModifier = 10;
    public Blizzard(int _manaPool, int _spellPower, int _hitsCounts, int abilityRange) {
        this._manaPool = _manaPool;
        this._currentMana = _manaPool / 4;
        this._spellPower = _spellPower;
        this._hitsCounts = _hitsCounts;
        this.abilityRange = abilityRange;
    }


    public void gameTick() {

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
}
