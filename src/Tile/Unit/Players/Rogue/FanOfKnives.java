package Tile.Unit.Players.Rogue;

import Tile.Unit.Health;
import Tile.Unit.Players.Player;

public class FanOfKnives {
    public int cost;
    public int currentEnergy;
    public static final int FanOfKnivesCurrentEnergyModifier = 100;

    public  FanOfKnives(int energy, int cost){
        this.cost = cost;
        this.currentEnergy = energy;
    }

    public void LevelUp(){
        this.currentEnergy += FanOfKnivesCurrentEnergyModifier;
    }

    public void gameTick(){
        currentEnergy = Math.min(currentEnergy+ 10, 100);
    }


    public void abilityCast(){}
    {
        currentEnergy -= cost;
    }





}
