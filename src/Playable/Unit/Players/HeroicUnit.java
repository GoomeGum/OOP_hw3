package Playable.Unit.Players;

import Playable.Unit.Enemies.Enemy;

import java.util.List;

public interface HeroicUnit {
    List<Enemy> abilityCast(List<Enemy> enemiesInRange);
}
