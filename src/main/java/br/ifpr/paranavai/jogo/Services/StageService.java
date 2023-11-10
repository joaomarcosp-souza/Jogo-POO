package br.ifpr.paranavai.jogo.Services;

import java.awt.Graphics;

public interface StageService {
    public abstract void drawnBullets(Graphics graphics);
    public abstract void dranwEnemies(Graphics graphics);
    public abstract void spawnAsteroids();
    public abstract void spawnEnemieShip();
    public abstract void spawnEnemieMeteor();
}
