import Playable.Unit.Enemies.Enemy;
import Playable.Unit.Enemies.Monster;
import Playable.Unit.Players.Rogue.FanOfKnives;
import org.junit.*;

import java.util.LinkedList;
import java.util.List;

public class FanOfKnivesTest {
    FanOfKnives f;
    List<Enemy> enemies;
    List<Enemy> ans;
    Enemy e;
    @Before
    public void initTest() {
        f = new FanOfKnives(50,2);
        e = new Monster('m', "Hummus", 100, 0, 7, 5, 3);
        enemies = new LinkedList<>();
        enemies.add(e);
    }
    @Test
    public void abilityCastInputNull()
    {
        ans = f.abilityCast(null, 1);
        Assert.assertNull("The list should be null", ans);
    }
    @Test
    public void abilityCastInputOneSickEnemy()
    {
        List<Enemy> ans = f.abilityCast(enemies, 10);
        Assert.assertNotNull("The list should not be null", ans);
    }
    @Test
    public void abilityCastInputTwoSickEnemies()
    {
        enemies.add(e);
        enemies.add(e);
        List<Enemy> ans = f.abilityCast(enemies, 10);
        Assert.assertTrue("The list should not be null", ans.size()>1);
    }
    @Test
    public void abilityCastInputOneHealthyEnemy()
    {
        e = new Monster('m', "Hummus", 100, 100, 7, 100, 3);
        enemies = new LinkedList<>();
        enemies.add(e);
        List<Enemy> ans = f.abilityCast(enemies, 10);
        Assert.assertNull("The list should be null", ans);
    }

    @Test
    public void abilityCastNoEnergy()
    {
        f.setCurrentEnergy(0);
        List<Enemy> ans = f.abilityCast(enemies, 10);
        Assert.assertNull("The list should be null", ans);
    }




}
