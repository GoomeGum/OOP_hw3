package Playable;
import java.util.LinkedList;
import Playable.Unit.Enemies.Enemy;
import java.util.ArrayList;
import java.util.List;
import Playable.Unit.Players.Player;
import Playable.Unit.Unit;


public class Board {
    public List<Empty> empties = new ArrayList<>();

    public List<Enemy> enemies = new ArrayList<>();

    public List<Wall> walls = new ArrayList<>();

    public Player player;

    public char[][] board;

    public void buildStringBoard(int xplace, int yplace) {
        board = new char[yplace][xplace];
        for (Empty empty : empties) {
            board[empty._position.get_y()][empty._position.get_x()] = empty._tile;
        }
        for (Enemy enemy : enemies) {
            board[enemy._position.get_y()][enemy._position.get_x()] = enemy._tile;
        }
        for (Wall wall : walls) {
            board[wall._position.get_y()][wall._position.get_x()] = wall._tile;
        }
        board[player._position.get_y()][player._position.get_x()] = player._tile;
    }

    public boolean checkMove(Unit unit, int YChange, int XChange) {
        Position oldPosition = unit.getPosition();
        Position newPosition = new Position(YChange,XChange);
        if (YChange > board.length || XChange > board[0].length || XChange < 0 || YChange < 0)
            return false;
        char TileInPlace = board[YChange][XChange];
        switch (TileInPlace){
            case '#':
                break;
            case '@': {
                unit.visit(player);
                if(player.isDead())
                {
                    board[YChange][XChange] = unit._tile;
                    board[oldPosition.get_y()][oldPosition.get_x()] = 'X';
                    System.out.println("Game Over");

                }
                break;
            }
            case '.': {
                for (Empty empty : empties) {
                    if (empty._position.equals(newPosition)) {
                        unit.visit(empty);
                        board[YChange][XChange] = unit._tile;
                        board[oldPosition.get_y()][oldPosition.get_x()] = '.';
                        break;
                    }
                }
            }
            default:
            {
                for (Enemy enemy : enemies) {
                    if (enemy._position.equals(newPosition)) {
                        unit.visit(enemy);
                        if(enemy.isDead())
                        {
                            board[YChange][XChange] = unit._tile;
                            empties.add(new Empty('.',oldPosition));
                            enemies.remove(enemy);
                            board[oldPosition.get_y()][oldPosition.get_x()] = '.';
                        }
                        break;
                    }
                }
            }
        }
        return true;
    }

    public List<Enemy> getEnemiesInRange() {
        List<Enemy> enemiesInRange = new LinkedList<>();
        for (Enemy enemy:enemies) {
            if (player._position.Distance(enemy._position) < player.getPlayerRange())
                enemiesInRange.add(enemy);
        }
        return enemiesInRange;
    }

    public Board() {
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (char[] row : board) {
            for (char cell : row) {
                sb.append(cell).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void castAbility() {
        List<Enemy> deadEnemies = player.abilityCast(this.getEnemiesInRange());
        if(deadEnemies == null)
            return;
        for (Enemy enemy: deadEnemies) {
            Position position = enemy.getPosition();
            empties.add(new Empty('.',position));
            enemies.remove(enemy);
            board[position.get_y()][position.get_x()] = '.';
        }
    }
}