public class Player extends Units{

    private int exp;
    private int playerLevel;



    public Player(String name, int healthPool, int healthAmount, int attackPoints, int defensePoints) {
        super(name, healthPool, healthAmount, attackPoints, defensePoints);
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
    private void DecreseModifier(){
        this.exp-=modifiers.PlayerLevelModifier*getPlayerLevel();
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
    private void increasePlayerLevel(){
        this.playerLevel++;
    }
    public int getPlayerLevel() {
        return playerLevel;
    }
    private void healthLevelUp(){
        set_healthPool(get_healthPool() + (modifiers.PlayerHealthModifier * getPlayerLevel()));
        regenerateHealth();
    }
    private void attackLevelUp(){
        set_attackPoints(get_attackPoints() + (modifiers.PlayerAttackModifier * getPlayerLevel()));
    }

    private void defenceLevelUp(){
        set_defensePoints(get_defensePoints() + (modifiers.PlayerDefenceModifier * getPlayerLevel()));

    }
    public void LevelUp(){
        while(getExp() >getPlayerLevel() * modifiers.PlayerLevelModifier ){
            DecreseModifier();
            increasePlayerLevel();
            healthLevelUp();
            attackLevelUp();

        }
    }
}
