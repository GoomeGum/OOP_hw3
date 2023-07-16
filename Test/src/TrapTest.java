import Playable.Position;
import Playable.Unit.Enemies.Monster;
import Playable.Unit.Enemies.Trap;
import Playable.Unit.Players.Player;
import org.junit.*;

public class TrapTest {
    Trap t;
    Player p;
    @Before
    public void initTest(){
        this.t = new Trap('m', "Hummus", 100, 5, 7, 5, 3, 4);
        this.t.setPosition(new Position(7, 7));
        this.p = new Player('p', "Mashawsha", 100, 10, 20, 4);
    }
    @Test
    public void testMonsterEnemyMoveClose(){
        this.p.setPosition(new Position(5,5));
        double start_dist = this.t.getPosition().Distance(this.p.getPosition());

        char c = this.t.enemyMove(p);

        double end_dist = this.t.getPosition().Distance(this.p.getPosition());
        Assert.assertTrue("The monster should get closer", end_dist < start_dist);
    }
}
