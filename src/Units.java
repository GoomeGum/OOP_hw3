public class Units {
    private String _name;
    private int _healthPool;
    private int _healthAmount;
    private int _attackPoints;
    private int _defensePoints;

    public Units(String name, int healthPool,int healthAmount, int attackPoints, int defensePoints ){
        _name = name;
        _healthPool = healthPool;
        _healthAmount = healthAmount;
        _attackPoints = attackPoints;
        _defensePoints = defensePoints;
    }

    public int get_attackPoints() {
        return _attackPoints;
    }

    public void set_attackPoints(int _attackPoints) {
        this._attackPoints = _attackPoints;
    }

    public int get_defensePoints() {
        return _defensePoints;
    }

    public void set_defensePoints(int _defensePoints) {
        this._defensePoints = _defensePoints;
    }

    public int get_healthAmount() {
        return _healthAmount;
    }

    public void set_healthAmount(int _healthAmount) {
        this._healthAmount = _healthAmount;
    }

    public int get_healthPool() {
        return _healthPool;
    }

    public void set_healthPool(int _healthPool) {
        this._healthPool = _healthPool;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }
    public void regenerateHealth(){
        this._healthAmount = this._healthPool;
    }
}
