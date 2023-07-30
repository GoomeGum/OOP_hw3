import Playable.Empty;
import Playable.Position;
import Playable.Unit.Enemies.Enemy;
import Playable.Unit.Enemies.Monster;
import Playable.Unit.Players.Player;
import org.junit.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.Assert.*;

public class PlayerTest {

    private Player p1;
    private Player p2;
    private Monster e1;
    private Monster e2;
    private Empty em1;
    private Position position1;
    private Position position2;
    private Empty em2;
    private GameManager gm;
    private List<String> data;
    @org.junit.Before
    public  void setUp() throws Exception {
        this.data = new LinkedList<>();
        File file = new File("C:\\Users\\GoomeGum\\IdeaProjects\\OOP_hw3\\levels_dir");
        this.gm = new GameManager(s-> data.add(s),file);

        this.p1 = new Player('p', "Mashawsha", 100, 10, 20, 4);
        this.p2 = new Player('p', "Mashawsha", 100, 10, 20, 4);
        this.e1 = new Monster('s', "Lannister Solider", 80, 8, 3, 25, 3);
        this.e2 = new Monster('k', "Lannister Knight", 200, 14, 8, 50, 4);
        this.position1 = new Position(1,1);
        this.em1 = new Empty('.',position1);
        this.position2 = new Position(1,2);
        this.em2 = new Empty('.',position2);
    }

    @org.junit.Test
    public void visit() {
        p1.visit(p2);
        p1.visit(e1);
        e1.visit(e2);
        p2.visit(em1);

        Assert.assertEquals("Mashawsha engaged in combat with Lannister Solider",this.data.get(0));
        String regexPlayer = "Mashawsha rolled [0-9]+ attack points";
        Pattern pattern = Pattern.compile(regexPlayer);
        Matcher matcher = pattern.matcher(this.data.get(1));
        assertTrue(matcher.matches());

        String regexEnemy = "Lannister Solider rolled [0-9]+ defense points";
        pattern = Pattern.compile(regexEnemy);
        matcher = pattern.matcher(this.data.get(2));
        assertTrue(matcher.matches());

        String regexPlayerDamage = "Mashawsha dealt [0-9]+ damage to Lannister Solider";
        pattern = Pattern.compile(regexPlayerDamage);
        matcher = pattern.matcher(this.data.get(3));
        assertTrue(matcher.matches() || "No damage was done".equals(this.data.get(3)));

        Assert.assertEquals("Lannister Solider finished combat with Mashawsha",this.data.get(4));
    }

    @org.junit.Test
    public void levelUp() {
        this.p1.setExp(51);
        this.p1.LevelUp();
        assertEquals("Mashawsha level up!",this.data.get(0));

        assertEquals(120,p1.getHealth());
        assertEquals(18,p1.getAttack());
        assertEquals(20,p1.getDefensePoints());
        assertEquals(2,p1.getPlayerLevel());
        assertEquals(1,p1.getExp());
    }
    @org.junit.After
    public void printer(){

        System.out.println(String.join(", ",this.data).replace(',','\n'));
        System.out.println(" ");
    }
    @org.junit.After
    public void resetSetting(){
        this.data.clear();
        this.p1 = new Player('p', "Mashawsha", 100, 10, 20, 4);
        this.p2 = new Player('p', "Mashawsha", 100, 10, 20, 4);
        this.e1 = new Monster('s', "Lannister Solider", 80, 8, 3, 25, 3);
        this.e2 = new Monster('k', "Lannister Knight", 200, 14, 8, 50, 4);

    }
}