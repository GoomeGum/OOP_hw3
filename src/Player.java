import javax.swing.text.Position;

public class Player extends Unit {

    private int exp;
    private int playerLevel;

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

    }

    @Override
    public void visit(Enemy e) {

    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    private void DecreaseModifier() {
        this.exp -= Modifiers.PlayerLevelModifier * getPlayerLevel();
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
        this.health.healthLevelUp(Modifiers.PlayerHealthModifier,getPlayerLevel());
    }

    private void attackLevelUp() {
        setAttackPoints(getAttackPoints() + (Modifiers.PlayerAttackModifier * getPlayerLevel()));
    }

    private void defenceLevelUp() {
        setDefensePoints(getDefensePoints() + (Modifiers.PlayerDefenceModifier * getPlayerLevel()));

    }

    public void LevelUp() {
        while (getExp() > getPlayerLevel() * Modifiers.PlayerLevelModifier) {
            DecreaseModifier();
            increasePlayerLevel();
            healthLevelUp();
            attackLevelUp();
        }
    }

}
