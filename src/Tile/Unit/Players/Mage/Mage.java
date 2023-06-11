package Tile.Unit.Players.Mage;
import Tile.Unit.Players.Player;
import javax.swing.text.Position;
public class Mage extends Player {

    private final int manaModifier = 25;
    private Blizzard blizzard;
    public Mage(char tile, Position position, String name, int healthPool, int healthAmount, int attackPoints, int defensePoints, int manaPool,int spellPower) {
        super(tile, position, name, healthPool, healthAmount, attackPoints, defensePoints);
        //TODO: check from where we get mana cost hit spell power
        blizzard = new Blizzard(manaPool,spellPower,0,0);
    }

    @Override
    public void LevelUp() {
        super.LevelUp();
        blizzard.levelUp(super.getPlayerLevel());
    }

}
