import javax.swing.text.Position;

public abstract class Unit extends Tile{
    protected String _name;
   protected Health health;
    protected int _attackPoints;
    protected int _defensePoints;

    public Unit(char tile, Position position, String name, int healthPool, int healthAmount, int attackPoints, int defensePoints ){
        super(tile, position);
        health = new Health(healthPool,healthAmount);
        _name = name;
        _attackPoints = attackPoints;
        _defensePoints = defensePoints;
    }

    public int getAttackPoints() {
        return _attackPoints;
    }

    public void setAttackPoints(int _attackPoints) {
        this._attackPoints = _attackPoints;
    }

    public int getDefensePoints() {
        return _defensePoints;
    }

    public void setDefensePoints(int _defensePoints) {
        this._defensePoints = _defensePoints;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    @Override
    public String toString(){
        return "";
    }

    protected void initialize(Position position, MessageCallback messageCallback){

    }

    protected int attack(){
        return 0;
    }

    public int defend(){
        return 0;
    }

    // Should be automatically called once the unit finishes its turn
    public abstract void processStep();

    // What happens when the unit dies
    public abstract void onDeath();

    // This unit attempts to interact with another tile.
    public void interact(Tile tile){

    }

    public void visit(Empty e){
    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    // Combat against another unit.
    protected void battle(Unit u){

    }


    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), health.getHealthAmount(), getAttackPoints(), getDefensePoints());
    }
}
