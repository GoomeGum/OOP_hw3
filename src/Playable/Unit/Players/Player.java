package Playable.Unit.Players;
import Playable.Unit.Enemies.Enemy;
import Playable.Position;
import Playable.Unit.Unit;

import java.util.List;


public class Player extends Unit {
    public static  final int PlayerLevelModifier = 50;
    public static final int PlayerHealthModifier = 10;
    public static final int PlayerAttackModifier = 4;
    public static final int PlayerDefenceModifier = 1;
    public static int playerRange;
    protected int exp;
    protected int playerLevel;



    public Player(char tile,String name, int healthPool, int attackPoints, int defensePoints, int abilityRange) {
        super(tile, name, healthPool, attackPoints, defensePoints);
        this.playerRange= abilityRange;
        this.playerLevel = 1;
    }
    @Override
    public void processStep() {
    }

    public static int getPlayerRange() {
        return playerRange;
    }

    @Override
    public void onDeath() {
        messageCallback.send("you died!");
    }
    @Override
    public void visit(Player p) {}
    @Override
    public void visit(Enemy e) {
        this.combat(e);
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
    public void abilityCast(List<Enemy> enemiesInRange){}
    protected void LevelUp() {
        while (getExp() > getPlayerLevel() * PlayerLevelModifier) {
            DecreaseModifier();
            increasePlayerLevel();
            healthLevelUp();
            attackLevelUp();
        }
    }
    @Override
    public String describe() {
        String player= String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d\t\tLevel: %d\t\tExperience: %d/50\t\t", getName(), health.getHealthAmount(), getAttackPoints(), getDefensePoints(), getPlayerLevel(),getExp());
        String ability = this.abilityDescribe();
        return player+" "+ability;
    }
    protected String abilityDescribe(){return " ";}
}
