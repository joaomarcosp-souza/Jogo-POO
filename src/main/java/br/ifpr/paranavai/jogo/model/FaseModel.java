package br.ifpr.paranavai.jogo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import br.ifpr.paranavai.jogo.model.Character.Player;
import br.ifpr.paranavai.jogo.model.Enemies.Asteroide;
import br.ifpr.paranavai.jogo.model.Enemies.Meteorito;
import br.ifpr.paranavai.jogo.model.Enemies.Naves;
import br.ifpr.paranavai.jogo.model.Enemies.StarDestroyer;

public class FaseModel {
    @Transient
    private Player player;
    // LISTA PARA INIMIGOS
    @Transient
    private List<Naves> enemyShip;
    @Transient
    private List<Asteroide> asteroids;
    @Transient
    private List<StarDestroyer> starDestroyer;
    @Transient
    private List<Meteorito> enemyMeteor;

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public List<Naves> getEnemyShip() {
        return enemyShip;
    }
    public void setEnemyShip(List<Naves> enemyShip) {
        this.enemyShip = enemyShip;
    }
    public List<Asteroide> getAsteroids() {
        return asteroids;
    }
    public void setAsteroids(List<Asteroide> asteroids) {
        this.asteroids = asteroids;
    }
    public List<StarDestroyer> getStarDestroyer() {
        return starDestroyer;
    }
    public void setStarDestroyer(List<StarDestroyer> starDestroyer) {
        this.starDestroyer = starDestroyer;
    }
    public List<Meteorito> getEnemyMeteor() {
        return enemyMeteor;
    }
    public void setEnemyMeteor(List<Meteorito> enemyMeteor) {
        this.enemyMeteor = enemyMeteor;
    }

    
}
