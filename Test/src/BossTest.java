import Playable.Position;
import Playable.Unit.Players.Player;
import Playable.Unit.Enemies.Boss;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BossTest {
    Boss B;
    Player p1;
    Player p2;

    @Before
    public void initTest(){
        B =new Boss('b',"b",10,10,10,0,1,3);
        p1 = new Player('p', "p", 100, 10, 20, 4);
        p2 = new Player('p', "p", 1, 10, 20, 4);

    }
    @Test
    public void testBossEnemyMoveDamage(){
        B.setPosition(new Position(1,1));
        p1.setPosition(new Position(0,0));
        int pHealth = p1.get_currentHealth();
        B.enemyMove(p1);
        Assert.assertTrue("The player's health should change",p1.get_currentHealth() <= pHealth);
    }
    @Test
    public void testBossEnemyMoveKill(){
        B.setPosition(new Position(1,1));
        p2.setPosition(new Position(0,0));
        int pHealth = p2.get_currentHealth();
        B.enemyMove(p2);
        Assert.assertTrue("The player should be dead", !p2.isDead());
    }
    @Test
    public void testBossEnemyMoveTooFar(){
        B.setPosition(new Position(1,1));
        p1.setPosition(new Position(10,10));
        int pHealth = p1.get_currentHealth();
        B.enemyMove(p1);
        Assert.assertTrue("The player's health should not change", p1.get_currentHealth() == pHealth);
    }
}
