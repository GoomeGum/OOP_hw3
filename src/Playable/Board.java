package Playable;
import java.util.LinkedList;
import java.util.Random;
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
        board = new char[xplace][yplace];
        for (Empty empty : empties) {
            board[empty._position.get_x()][empty._position.get_y()] = empty._tile;
        }
        for (Enemy enemy : enemies) {
            board[enemy._position.get_x()][enemy._position.get_y()] = enemy._tile;
        }
        for (Wall wall : walls) {
            board[wall._position.get_x()][wall._position.get_y()] = wall._tile;
        }
        board[player._position.get_x()][player._position.get_y()] = player._tile;
    }

    public boolean checkMove(Unit unit, int Xchange, int Ychange) {
        Position oldPosition = unit.getPosition();
        Position newPosition = new Position(Xchange,Ychange);
        if (Xchange > board.length || Ychange > board[0].length || Ychange < 0 || Xchange < 0)
            return false;
        char TileInPlace = board[Xchange][Ychange];
        switch (TileInPlace){
            case '#':
                break;
            case '@': {
                unit.visit(player);
                if(player.isDead())
                {
                    board[Xchange][Ychange] = unit._tile;
                    board[oldPosition.get_x()][oldPosition.get_y()] = 'X';
                    System.out.println("Game Over");

                }
                break;
            }
            case '.': {
                for (Empty empty : empties) {
                    if (empty._position.equals(newPosition)) {
                        unit.visit(empty);
                        board[Xchange][Ychange] = unit._tile;
                        board[oldPosition.get_x()][oldPosition.get_y()] = '.';
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
                            board[Xchange][Ychange] = unit._tile;
                            empties.add(new Empty('.',oldPosition));
                            board[oldPosition.get_x()][oldPosition.get_y()] = '.';
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
}