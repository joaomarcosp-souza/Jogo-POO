package br.ifpr.paranavai.jogo.model;

import java.util.ArrayList;
import javax.persistence.Transient;

import br.ifpr.paranavai.jogo.Util.TamanhoTela;
import br.ifpr.paranavai.jogo.model.Character.Player;
import br.ifpr.paranavai.jogo.model.Enemies.Asteroide;
import br.ifpr.paranavai.jogo.model.Enemies.Meteorito;
import br.ifpr.paranavai.jogo.model.Enemies.Naves;

public class StageModel {
    @Transient
    private Player player;
    // LISTA PARA INIMIGOS
    @Transient
    private ArrayList<Naves> enemieShip;
    @Transient
    private ArrayList<Asteroide> asteroids;
    @Transient
    private ArrayList<Meteorito> enemieMeteor;

    private TamanhoTela screenSize;

    public StageModel() {
        screenSize = new TamanhoTela();
        screenSize.carregar();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Naves> getEnemieShip() {
        return enemieShip;
    }

    public void setEnemieShip(ArrayList<Naves> enemieShip) {
        this.enemieShip = enemieShip;
    }

    public ArrayList<Asteroide> getAsteroids() {
        return asteroids;
    }

    public void setAsteroids(ArrayList<Asteroide> asteroids) {
        this.asteroids = asteroids;
    }

    public ArrayList<Meteorito> getEnemieMeteor() {
        return enemieMeteor;
    }

    public void setEnemieMeteor(ArrayList<Meteorito> enemieMeteor) {
        this.enemieMeteor = enemieMeteor;
    }

    public TamanhoTela getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(TamanhoTela screenSize) {
        this.screenSize = screenSize;
    }
}
