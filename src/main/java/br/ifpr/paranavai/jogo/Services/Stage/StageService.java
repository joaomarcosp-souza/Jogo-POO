package br.ifpr.paranavai.jogo.Services.Stage;

import java.awt.Graphics;

public interface StageService {
    public abstract void drawnBullets(Graphics graphics);
    public abstract void dranwEnemies(Graphics graphics);
    public abstract void dranwEnemiesScore(Graphics graphics);
    public abstract void spawnAsteroids();
    public abstract void spawnEnemieShip();
    public abstract void spawnEnemieMeteor();
    public abstract void controlEnemies();
    public abstract void controlBullets();
    public abstract void speedEnemieControl();
    public abstract void screenCollisions();
    public abstract void EnemieShipCollisions();
    public abstract void EnemieMeteorCollisions();
    public abstract void saveGame();
    public abstract void loadLastSaveElements();

}
