package Playable.Unit.Players.Mage;
import Playable.Unit.Players.Player;

import javax.swing.text.Position;
public class Mage extends Player {

    private final int manaModifier = 25;
    private int _manaCost;
    private int _hitCount;
    private int _abilityRange;
    private Blizzard blizzard;
    public Mage(char tile, String name, int healthPool, int attackPoints, int defensePoints, int manaPool,int manaCost,int spellPower,int hitCount,int abilityRange) {
        super(tile, name, healthPool, attackPoints, defensePoints);
        //TODO: check from where we get mana cost hit spell power
        blizzard = new Blizzard(manaPool,spellPower,hitCount,abilityRange);
    }

    @Override
    public void LevelUp() {
        super.LevelUp();
        blizzard.levelUp(super.getPlayerLevel());
    }
    @Override
    public void processStep() {
        blizzard.processStep(this.playerLevel);
    }
    public void abilityCast(){
        blizzard.abilityCast(this._manaCost);
    }
}
