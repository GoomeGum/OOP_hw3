package Playable.Unit.Players.Mage;
import Playable.Unit.Enemies.Enemy;
import Playable.Unit.Players.Player;

import javax.swing.text.Position;
import java.util.List;

public class Mage extends Player {

    private int _manaCost;
    private int _abilityRange;
    private Blizzard blizzard;
    public Mage(char tile, String name, int healthPool, int attackPoints, int defensePoints, int manaPool,int manaCost,int spellPower,int hitCount,int abilityRange) {
        super(tile, name, healthPool, attackPoints, defensePoints,abilityRange);
        //TODO: check from where we get mana cost hit spell power
        blizzard = new Blizzard(manaPool,spellPower,hitCount);
        this._manaCost = manaCost;
        this._abilityRange = abilityRange;
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
    @Override
    public void abilityCast(List<Enemy> enemiesInRange){
        if (blizzard.get_currentMana() < _manaCost)
            this.messageCallback.send("Invalid option. Please try again.");
        else {
            List<Enemy> killed = blizzard.abilityCast(enemiesInRange, this._manaCost);
            for (Enemy enemy:killed ) {
                this.onKill(enemy);
            }
        }
    }
    @Override
    protected String abilityDescribe(){
        return String.format("Mana: %d/%d\t\tSpell Power: %d", blizzard.get_currentMana(), blizzard.get_manaPool(), blizzard.get_spellPower());
    }

}
