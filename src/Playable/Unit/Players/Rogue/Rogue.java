package Playable.Unit.Players.Rogue;

import Playable.Unit.Health;
import Playable.Unit.Players.Player;

import javax.swing.text.Position;

public class Rogue extends Player {
    public FanOfKnives ability;
    public static final int RogueAttackModifier = 3;
    public static final int RogueMaxAnergyModifier = 100;


    public Rogue(char tile,  String name, int healthPool, int healthAmount, int attackPoints, int defensePoints, int cost) {
        super(tile, name, healthPool,  attackPoints, defensePoints);
        ability = new FanOfKnives(RogueMaxAnergyModifier, cost);
    }
    public void LevelUp(){
        ability.LevelUp();
        int NewAttack= getAttackPoints()+ (RogueAttackModifier * getPlayerLevel());
        setAttackPoints(NewAttack);
    }

    public void abilityCast(Health health, int defense, Player player){}
    {
        ability.abilityCast();

    }
}
