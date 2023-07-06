package Playable;

import Playable.Unit.Enemies.Enemy;
import java.util.ArrayList;
import java.util.List;
import Playable.Unit.Players.Player;


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
    }


    public boolean checkMove(int Xchange, int Ychange) {
        Position position = player.getPosition();
        position.UpdateX(Xchange);
        position.UpdateY(Ychange);
        int yVal = position.get_y();
        int xVal = position.get_x();
        if (xVal > board.length || yVal > board[0].length || yVal < 0 || xVal < 0)
            return false;
        for (Enemy enemy : enemies) {
            if (enemy._position.equals(position)) {
                player.accept(enemy);
            }
        }
        for (Empty empty : empties) {
            if (empty._position.equals(position)) {
                player.accept(empty);
            }
        }
        //else it's a wall and we do nothing
        return true;
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