public class Health {
    private int _healthPool;
    private int _healthAmount;

    public Health(int healthPool,int healthAmount){
        _healthAmount = healthAmount;
        _healthPool = healthPool;
    }

    public int getHealthAmount() {
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
    public void regenerateHealth(){
        this._healthAmount = this._healthPool;
    }
    public void healthLevelUp(int playerLevel,int modifier) {
        set_healthPool(get_healthPool() + (modifier * playerLevel));
        regenerateHealth();
    }
}
