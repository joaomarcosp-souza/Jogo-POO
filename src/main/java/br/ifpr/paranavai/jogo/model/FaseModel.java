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
    @OneToMany(mappedBy = "naves_inimigas", cascade = CascadeType.ALL)
    private List<Naves> enemieShip;
    @Transient
    @OneToMany(mappedBy = "asteroides", cascade = CascadeType.ALL)
    private List<Asteroide> asteroids;
    @Transient
    @OneToMany(mappedBy = "starDestroyer", cascade = CascadeType.ALL)
    private List<StarDestroyer> starDestroyer;
    @Transient
    @OneToMany(mappedBy = "meteoritos_inimigos", cascade = CascadeType.ALL)
    private List<Meteorito> enemieMeteor;

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public List<Naves> getEnemieShip() {
        return enemieShip;
    }
    public void setEnemieShip(List<Naves> enemyShip) {
        this.enemieShip = enemyShip;
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
    public List<Meteorito> getEnemieMeteor() {
        return enemieMeteor;
    }
    public void setEnemieMeteor(List<Meteorito> enemyMeteor) {
        this.enemieMeteor = enemyMeteor;
    }
}
