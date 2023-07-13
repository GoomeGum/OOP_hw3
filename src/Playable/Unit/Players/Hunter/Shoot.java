package Playable.Unit.Players.Hunter;

public class Shoot {
    public int _range;
    public int _arrowsCount;
    public int _ticksCount = 0;

    private final int arrowsCountModifier = 10;

    public Shoot(int range, int arrowsCount) {
        _range = range;
        _arrowsCount = arrowsCount;
    }

    public void set_arrowsCount(int _arrowsCount) {
        this._arrowsCount = _arrowsCount;
    }

    public int get_arrowsCount() {
        return _arrowsCount;
    }

    public void levelUp(int playerLevel) {
        set_arrowsCount(get_arrowsCount() + arrowsCountModifier * playerLevel);
    }

    public void shootArrow() {
        _arrowsCount--;
    }
    public void processStep(int level)
    {
        if(_ticksCount==10)
        {
            _arrowsCount = _arrowsCount+level;
            _ticksCount = 0;
        }
        else
            _ticksCount ++;
    }
}
