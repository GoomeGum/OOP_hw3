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
            System.out.println(this.board.toString());
            char choice = getMovement();
            move(board.player,choice);
            for (Enemy e: this.board.enemies) {
                char enemyMove = e.enemyMove(board.player);
                move(e, enemyMove);
            }
            board.player.processStep();
            System.out.println(board.player.describe());
        }
        return this.board.enemies.size() == 0;
    }

    private void move(Unit unit,char choice) {
        int x = unit.getPosition().get_x();
        int y = unit.getPosition().get_y();
        switch (choice) {
            case 'w':
                board.checkMove(unit,y-1, x);break;
            case 's':
                board.checkMove(unit,y+1, x);break;
            case 'a':
                board.checkMove(unit,y, x-1);break;
            case 'd':
                board.checkMove(unit,y, x+1);break;
            case 'e':
                 board.castAbility();
            case 'q': //do nothing
        }
    }

    private char getMovement() {
        char option = 0;
        boolean validOption = false;
        while (!validOption) {
            System.out.print("Enter an option (a, d, s, w, q ,e): ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.length() == 1) {
                option = input.charAt(0);
                option = Character.toLowerCase(option);
                switch (option) {
                    case 'a', 'd', 's', 'w', 'q','e' -> {
                        validOption = true;
                    }

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
            System.out.println("Select player:");
            for (Player player : players) {
                System.out.println(i + ". " + player.describe());
                i++;
            }
            choice = scanner.nextInt();
        }
        while (choice > players.size() || choice < 1);
        board.player = players.get(choice-1);
        System.out.println("You have selected: "+board.player.getName() );
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
                    Position position = new Position(Yplace, Xplace);
                    switch (tile){
                        case '@':
                            board.player.setPosition(position);
                            break;
                        case '#':
                            board.walls.add(tileFactory.produceWall(position));
                            break;
                        case '.':
                            board.empties.add(tileFactory.produceEmpty(position));
                            break;
                        case 's','k','q','z','b','g','w','M','C','K','B','Q','D':
                            {
                                board.enemies.add(tileFactory.produceEnemy(tile, position));
                            }
                        default:
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

