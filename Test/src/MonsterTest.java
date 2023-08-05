import Playable.Position;
import Playable.Unit.Enemies.Monster;
import Playable.Unit.Players.Player;
import Playable.Unit.Unit;
import org.junit.*;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class MonsterTest {
    Monster m;
    Player p;

    @Before
    public void initTest(){
        this.m = new Monster('m', "Hummus", 100, 100, 7, 5, 3);
        this.m.setPosition(new Position(7, 7));
        this.p = new Player('p', "Mashawsha", 100, 10, 20, 4);
    }
    @Test
    public void testMonsterEnemyMoveClose(){
        this.p.setPosition(new Position(5,5));
        double start_dist = this.m.getPosition().Distance(this.p.getPosition());

        char c = this.m.enemyMove(p);
        move(m,c);

        double end_dist = this.m.getPosition().Distance(this.p.getPosition());
        Assert.assertTrue("The monster should get closer", end_dist < start_dist);
    }
    @Test
    public void testMonsterEnemyMoveRandom(){
        this.p.setPosition(new Position(1,1));
        char c = this.m.enemyMove(p);
        move(m,c);

        Assert.assertNotNull("The monster should move randomly");
    }

    @Test
    public void testMonsterEnemyMoveRandomFun(){
        this.p.setPosition(new Position(1,1));
        char c = this.m.enemyMoveRandom();
        move(m,c);

        Assert.assertNotNull("The monster should move randomly");
    }

    private void move(Unit unit, char choice) {
        int x = unit.getPosition().get_x();
        int y = unit.getPosition().get_y();
        switch (choice) {
            case 'w':
                unit.setPosition(new Position(x-1, y));break;
            case 's':
                unit.setPosition(new Position(x+1, y));break;
            case 'a':
                unit.setPosition(new Position(x, y-1));break;
            case 'd':
                unit.setPosition(new Position(x, y+1));break;
        }
    }
}