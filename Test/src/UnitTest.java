import Playable.Position;
import Playable.Unit.Players.Player;
import Playable.Unit.Unit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UnitTest {
    Unit u1;
    Unit u2;
    @Before
    public void initTest(){
        u1 = new Player('a',"a",1,1,0,2);
        u2 = new Player('a',"a",10,10,10,2);
        u1.initialize(new Position(0,0), s-> System.out.println(s));
        u2.initialize(new Position(1,1), s-> System.out.println(s));

    }
    @Test
    public void testUnitCombatNoDamage() {
        int u2Health = u2.get_currentHealth();
        u1.combat(u2);
        Assert.assertTrue("The unit's health should not change",u2.get_currentHealth() == u2Health);
    }

    @Test
    public void testUnitCombatDamage() {
        int u1Health = u1.get_currentHealth();
        u2.combat(u1);
        Assert.assertTrue("The unit's health should change",u1.get_currentHealth() <= u1Health);
    }

    @Test
    public void testUnitTakePlace() {
        Position u1Position = u1.getPosition();
        u1.TakePlace(u2);
        Assert.assertTrue("The unit's health should not change",u2.getPosition() == u1Position);
    }
    }
