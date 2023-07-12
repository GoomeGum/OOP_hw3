package Playable.Unit.Players;
import Playable.Unit.Enemies.Enemy;
import Playable.Position;
import Playable.Unit.Unit;


public class Player extends Unit {
    public static  final int PlayerLevelModifier = 50;
    public static final int PlayerHealthModifier = 10;
    public static final int PlayerAttackModifier = 4;
    public static final int PlayerDefenceModifier = 1;
    protected int exp;
    protected int playerLevel;



    public Player(char tile,String name, int healthPool, int attackPoints, int defensePoints) {
        super(tile, name, healthPool, attackPoints, defensePoints);
    }
    @Override
    public void processStep() {
    }
    @Override
    public void onDeath() {
        messageCallback.send("you died!");
    }
    @Override
    public void visit(Player p) {}
    @Override
    public void visit(Enemy e) {
        this.attack(e);
        if(e.isDead()){
            this.TakePlace(e);
            this.onKill(e);
        }
    }

    protected void onKill(Enemy e) {
        this.addExp(e.getExperienceValue());
        this.LevelUp();
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void addExp(int exp) {this.exp+=exp;}
    private void DecreaseModifier() {
        this.exp -= PlayerLevelModifier * getPlayerLevel();
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }

    private void increasePlayerLevel() {
        this.playerLevel++;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    private void healthLevelUp() {
        this.health.healthLevelUp(PlayerHealthModifier,getPlayerLevel());
    }

    private void attackLevelUp() {
        setAttackPoints(getAttackPoints() + (PlayerAttackModifier * getPlayerLevel()));
    }
    private void defenceLevelUp() {
        setDefensePoints(getDefensePoints() + (PlayerDefenceModifier * getPlayerLevel()));

    }

    protected void LevelUp() {
        while (getExp() > getPlayerLevel() * PlayerLevelModifier) {
            DecreaseModifier();
            increasePlayerLevel();
            healthLevelUp();
            attackLevelUp();
        }
    }

}
