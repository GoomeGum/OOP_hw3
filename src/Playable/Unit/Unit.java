package Playable.Unit;

import Playable.*;
import Playable.Unit.Enemies.Enemy;
import Playable.Unit.Players.Player;

public abstract class Unit extends Tile {
    protected String _name;
   protected Health health;
    protected int _attackPoints;
    protected int _defensePoints;
    public static IMessageCallback messageCallback;

    public static Board board;
    public Unit(char tile, String name, int healthPool, int attackPoints, int defensePoints){
        super(tile);
        health = new Health(healthPool,healthPool);
        _name = name;
        _attackPoints = attackPoints;
        _defensePoints = defensePoints;

    }
    protected void initialize(Position position, IMessageCallback messageCallback){

    }
    public void dealDamage(int damage){
        health.dealDamage(damage);
    }
    public boolean isDead(){
        return health.isDead();
    }
    //visit
    protected void TakePlace(Tile tile){
        Position temp=this._position;
        this._position = tile.getPosition();
        tile.setPosition(temp);
    }
    public abstract void visit(Player p);
    public abstract void visit(Enemy e);
    public void visit(Empty e){
        TakePlace(e);
    }

    //actions
    /**protected void attack(Enemy e){
        messageCallback.send(getName()+" starts combat with "+e.getName());
        int damage = Math.max(_attackPoints - e._defensePoints,0);
        e.dealDamage(damage);
        messageCallback.send(damage + " damage has been done");
    }

    public int defend(Enemy e){
        return 0;
    }**/
    protected void attack(Unit u){
        messageCallback.send(getName()+" starts combat with "+u.getName());
        int damage = Math.max(_attackPoints - u._defensePoints,0);
        u.dealDamage(damage);
        messageCallback.send(damage + " damage has been done");
    }

    public int defend(Unit u){
        return 0;
    }




    // Should be automatically called once the unit finishes its turn
    public abstract void processStep();

    // What happens when the unit dies
    public abstract void onDeath();

    // This unit attempts to interact with another tile.
    public void interact(Tile tile){

    }

    public abstract void accept(Unit unit);
    // Combat against another unit.


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
        return describe();
    }
    public String describe() {
        return String.format("%s\t\tGame.Tile.Unit.Health: %s\t\tAttack: %d\t\tDefense: %d", getName(), health.getHealthAmount(), getAttackPoints(), getDefensePoints());
    }
}
