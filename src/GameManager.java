import Playable.*;
import Playable.Unit.Enemies.Enemy;
import Playable.Unit.Players.Player;
import Playable.Unit.Unit;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GameManager {
    public Board board;
    public IMessageCallback massageCallBack;
    public List<File> levels;
    private TileFactory tileFactory;

    public GameManager(IMessageCallback massage) {
        massageCallBack = massage;
        this.board = new Board();
        Unit.board = board;
        Unit.messageCallback = massage;
        tileFactory = new TileFactory();
    }

    public void start() {
        selectPlayer();
        createLevels();
        boolean finishedLevel = true;
        for (int i = 0; i < levels.size() && finishedLevel; i++) {
            buildBoard(i);
            finishedLevel = play();
        }

    }

    private boolean play() {
        while (this.board.enemies.size() > 0 && !this.board.player.isDead()) {
            char choice = getMovement();
            move(board.player,choice);
            for (Enemy e: this.board.enemies) {
                char enemyMove = e.play(board.player);
                move(e, enemyMove);
            }
            //activate player tick

        }
        return this.board.enemies.size() == 0;
    }

    private void move(Unit unit,char choice) {
        switch (choice) {
            case 'a':
                board.checkMove(unit,-1, 0);
            case 'd':
                board.checkMove(unit,1, 0);
            case 's':
                board.checkMove(unit,0, -1);
            case 'w':
                board.checkMove(unit,1, 0);
            case 'e':
                //activate special power
            case 'q':
                //do nothing

        }
    }

    private char getMovement() {
        char option = 0;
        boolean validOption = false;
        while (!validOption) {
            System.out.print("Enter an option (a, d, s, w,q ,e): ");
            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine();

            if (input.length() == 1) {
                option = input.charAt(0);
                option = Character.toLowerCase(option);
                switch (option) {
                    case 'a', 'd', 's', 'w', 'e', 'q' -> {
                        validOption = true;
                    }
                    //need to activate special power
                    default -> massageCallBack.send("Invalid option. Please try again.");
                }
            }
        }
        return option;
    }

    private void createLevels() {
        String baseDir = "levels_dir";
        File folder = new File(baseDir);
        File[] fileNames = folder.listFiles();
        generateFileList(fileNames);
    }

    private void generateFileList(File[] fileNames) {
        List<File> fileList = new ArrayList<>();

        for (File fileName : fileNames) {
            File file = new File(fileName.getAbsolutePath());
            fileList.add(file);
        }
        levels = fileList;
    }

    private void selectPlayer() {
        Scanner scanner = new Scanner(System.in);
        List<Player> players = tileFactory.listPlayers();
        int i = 1;
        int choice;
        do {
            for (Player player : players) {
                System.out.println(i + ". " + player.describe());
            }
            System.out.println("choose your fighter");
            choice = scanner.nextInt();
        } while (choice > players.size() || choice < 1);
        board.player = players.get(choice);
    }

    private void buildBoard(int level) {
        board.walls.clear();
        board.empties.clear();
        board.enemies.clear();
        File file = levels.get(level);

        int Xplace = 0;
        int Yplace = 0;
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                Xplace = 0;
                for (char tile : line.toCharArray()) {
                    Position position = new Position(Xplace, Yplace);
                    if (tile == '#')
                        board.walls.add(tileFactory.produceWall(position));
                    else if (tile == '.')
                        board.empties.add(tileFactory.produceEmpty(position));
                    else {
                        board.enemies.add(tileFactory.produceEnemy(tile, position));
                    }
                    Xplace++;
                }
                Yplace++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        board.buildStringBoard(Xplace, Yplace);
    }

}

