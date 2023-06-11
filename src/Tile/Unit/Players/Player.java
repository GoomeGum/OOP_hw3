package Tile.Unit.Players;

import Tile.Unit.Enemies.Enemy;

import javax.swing.text.Position;

import Tile.Unit.Players.Interfaces.IAbility;
import Tile.Unit.Unit;
public class Player extends Unit {
    public static  final int PlayerLevelModifier = 50;
    public static final int PlayerHealthModifier = 10;
    public static final int PlayerAttackModifier = 4;
    public static final int PlayerDefenceModifier = 1;
    protected int exp;
    protected int playerLevel;

    protected IAbility ability;

    public Player(char tile, Position position, String name, int healthPool, int healthAmount, int attackPoints, int defensePoints) {
        super(tile, position, name, healthPool, healthAmount, attackPoints, defensePoints);
    }

    @Override
    public void processStep() {

    }

    @Override
    public void onDeath() {

    }

    @Override
    public void visit(Player p) {
        System.out.println("sdfsdf");
    }

    @Override
    public void visit(Enemy e) {

    }

    @Override
    public void accept(Unit unit) {

    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

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

    public void LevelUp() {
        while (getExp() > getPlayerLevel() * PlayerLevelModifier) {
            DecreaseModifier();
            increasePlayerLevel();
            healthLevelUp();
            attackLevelUp();
        }
    }

}
