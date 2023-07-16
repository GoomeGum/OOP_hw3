package Playable.Unit.Enemies;

import Playable.Position;
import Playable.Unit.Players.Player;

import java.util.List;
import java.util.Random;

public class Monster extends Enemy{
    public Monster(char tile,String name, int healthPool, int healthAmount, int attackPoints, int defensePoints, int visionRange) {
        super(tile, name, healthPool, healthAmount, attackPoints, defensePoints, visionRange);
    }
    @Override
    public char enemyMove(Player player)
    {
        Position enemyP = this.getPosition();
        Position playerP = player.getPosition();
        char move = ' ';
        if (playerP.Distance(enemyP)<this.getVisionRange()) {
            int dx = enemyP.get_x() - playerP.get_x();
            int dy = enemyP.get_y() - playerP.get_y();
            if (Math.abs(dx) > Math.abs(dy))
                if (dx > 0)
                    move = 'w';
                else
                    move = 's';
            else if (dx > 0)
                move = 'a';
            else
                move = 'd';
        }
        else {
            Random random = new Random();
            int toWhere = random.nextInt(0, 5);
            String s = "qadws";
            move= s.charAt(toWhere);
        }
        return move;
    }

}
