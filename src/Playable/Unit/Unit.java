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
    public void initialize(Position position, IMessageCallback messageCallback){
        this.setPosition(position);
        this.messageCallback = messageCallback;
    }
    public int get_currentHealth(){
        return health.getHealthAmount();
    }
    public int attackRoll()
    {
        int attack = (int)(Math.random()*this._attackPoints);
        messageCallback.send(getName() + " rolled "+ attack+ " attack points");
        return attack;
    }
    public int defenseRoll()
    {
        int defense= (int)(Math.random()*this._defensePoints);
        messageCallback.send(getName() + " rolled "+ defense+ " defense points");
        return defense;
    }
    public void dealDamage(int damage){
        health.dealDamage(damage);
        if (this.isDead())
            this.onDeath();
    }
    public boolean isDead(){
        return health.isDead();
    }

    public void TakePlace(Tile tile){
        Position temp=this._position;
        this.setPosition(tile.getPosition());
        tile.setPosition(temp);
    }
    public abstract void visit(Player p);
    public abstract void visit(Enemy e);
    public void visit(Empty e){
        TakePlace(e);
    }
    public void visit(Unit unit){}

    public void combat(Unit u){
        messageCallback.send(getName()+" engaged in combat with "+u.getName());
        int attackRoll =this.attackRoll();
        int defenseRoll = u.defenseRoll();
        int damage = attackRoll - defenseRoll;
        if(damage > 0) {
            u.dealDamage(damage);
            messageCallback.send(getName() + " dealt "+damage +" damage to "+u.getName());
            if(u.isDead())
                messageCallback.send(u._name + " is dead");
        }
        else
            messageCallback.send("No damage was done");
        messageCallback.send(u.getName()+ " finished combat with "+getName());
    }

    public abstract void processStep();

    // What happens when the unit dies
    public abstract void onDeath();

    public void accept(Unit unit){
        unit.visit(this);
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
        return describe();
    }
    public String describe() {
        return String.format("%s\t\tGame.Tile.Unit.Health: %s\t\tAttack: %d\t\tDefense: %d", getName(), health.getHealthAmount(), getAttackPoints(), getDefensePoints());
    }
}
