package Playable.Unit.Enemies;

import Playable.Position;
import Playable.Unit.Players.Player;

import java.util.Random;

public class Monster extends Enemy{
    public Monster(char tile,String name, int healthPool, int healthAmount, int attackPoints, int defensePoints, int visionRange) {
        super(tile, name, healthPool, healthAmount, attackPoints, defensePoints, visionRange);
    }
    @Override
    public char play(Player player)
    {
        Position enemyP = this.getPosition();
        Position playerP = player.getPosition();
        char move = ' ';
        if (playerP.Distance(enemyP)<this.getVisionRange()) {
            int dx = enemyP.get_x() - playerP.get_x();
            int dy = enemyP.get_y() - playerP.get_y();
            if (Math.abs(dx) > Math.abs(dy))
                if (dx > 0)
                    move = 'a';
                else
                    move = 'd';
            else if (dx > 0)
                move = 'w';
            else
                move = 's';
        }
        else
        {
            Random random = new Random();
            int toWhere = random.nextInt(0,5);
            switch (toWhere) {
                case 1:
                    move = 'a';
                    break;
                case 2:
                    move = 'd';
                    break;
                case 3:
                    move = 'w';
                    break;
                case 4:
                    move = 's';
                    break;
                case 0:
                    move = 'q';
                    break;
            }
        }
        return move;
    }

}
