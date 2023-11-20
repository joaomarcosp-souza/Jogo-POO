package br.ifpr.paranavai.jogo.model;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import br.ifpr.paranavai.jogo.Util.Sounds;
import br.ifpr.paranavai.jogo.Util.ScreenSize;
import br.ifpr.paranavai.jogo.model.Character.Player;
import br.ifpr.paranavai.jogo.model.Enemies.Asteroide;
import br.ifpr.paranavai.jogo.model.Enemies.Meteorito;
import br.ifpr.paranavai.jogo.model.Enemies.Naves;
import br.ifpr.paranavai.jogo.model.Screens.Controles;
import br.ifpr.paranavai.jogo.model.Screens.FimDeJogo;
import br.ifpr.paranavai.jogo.model.Screens.Historico;
import br.ifpr.paranavai.jogo.model.Screens.MenuInicial;
import br.ifpr.paranavai.jogo.model.Screens.Pausar;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class StageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_stageModel", unique = true)
    @JoinColumn(referencedColumnName = "id_elementos")
    private Integer id_stage;
    @Transient
    @JoinColumn(name = "id_elementos")
    private Player player;
    // LISTA PARA INIMIGOS
    @Transient
    @JoinColumn(referencedColumnName = "id_elementos")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id_elementos")
    private ArrayList<Naves> enemieShip;
    @Transient
   @JoinColumn(referencedColumnName = "id_elementos")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id_elementos")
    private ArrayList<Asteroide> asteroids;
    @Transient
    @JoinColumn(referencedColumnName = "id_elementos")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id_elementos")
    private ArrayList<Meteorito> enemieMeteor;

    private ScreenSize screenSize;
    private Sounds sounds;

    private Pausar screenPaused;
    private FimDeJogo screenEndGame;
    private MenuInicial screenMenu;
    private Historico screenHistory;
    private Controles screenControls;
    private ScreenSize screenResolution;

    private boolean enemyKilled;

    public StageModel() {
        screenSize = new ScreenSize();
        screenSize.carregar();
        sounds = new Sounds();

        screenMenu = new MenuInicial();
        screenMenu.load();

        screenHistory = new Historico();
        screenHistory.load();

        screenControls = new Controles();
        screenControls.load();

        screenEndGame = new FimDeJogo();
        screenEndGame.load();

        screenResolution = new ScreenSize();
        screenResolution.carregar();

        screenPaused = new Pausar();
        screenPaused.load();
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

    public ScreenSize getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(ScreenSize screenSize) {
        this.screenSize = screenSize;
    }

    public Sounds getSounds() {
        return sounds;
    }

    public void setSounds(Sounds sounds) {
        this.sounds = sounds;
    }

    public boolean isEnemyKilled() {
        return enemyKilled;
    }

    public void setEnemyKilled(boolean enemyKilled) {
        this.enemyKilled = enemyKilled;
    }

    public Pausar getScreenPaused() {
        return screenPaused;
    }

    public void setScreenPaused(Pausar screenPaused) {
        this.screenPaused = screenPaused;
    }

    public FimDeJogo getScreenEndGame() {
        return screenEndGame;
    }

    public void setScreenEndGame(FimDeJogo screenEndGame) {
        this.screenEndGame = screenEndGame;
    }

    public MenuInicial getScreenMenu() {
        return screenMenu;
    }

    public void setScreenMenu(MenuInicial screenMenu) {
        this.screenMenu = screenMenu;
    }

    public Historico getScreenHistory() {
        return screenHistory;
    }

    public void setScreenHistory(Historico screenHistory) {
        this.screenHistory = screenHistory;
    }

    public Controles getScreenControls() {
        return screenControls;
    }

    public void setScreenControls(Controles screenControls) {
        this.screenControls = screenControls;
    }

    public ScreenSize getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(ScreenSize screenResolution) {
        this.screenResolution = screenResolution;
    }

}
