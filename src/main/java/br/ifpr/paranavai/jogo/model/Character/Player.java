package br.ifpr.paranavai.jogo.model.Character;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.ImageIcon;

import br.ifpr.paranavai.jogo.model.Base;
import br.ifpr.paranavai.jogo.model.Character.Bullets.Shoot;
import br.ifpr.paranavai.jogo.model.Character.Bullets.SpecialShoot;

import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.Rectangle;

@Entity
@Table(name = "tb_Jogador")
public class Player extends Base {
    @Column(name= "pontos", unique = true, nullable = false)
    private int score;
    @Column(name = "pontos_inimigos_mortos", unique = true, nullable = false)
    private int scoreDeadEnemys;
    @Column(name = "quantidade_super")
    private int superQuantity;
    @Column(name= "ultimo_tiro")
    private long lastBullet;
    @Transient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personagem")
    private ArrayList<Shoot> bullets;
    @Transient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personagem")
    private ArrayList<SpecialShoot> specialBullet;
    // MOVITEMENTAÇÃO
    @Column(name= "deslocamento_X")
    private int displacementX;
    @Column(name = "deslocamento_Y")
    private int displacementY;
    @Column(name = "posicao_vida_X")
    private int positionLifeInX;
    @Column(name = "posicao_vida_Y")
    private int positionLifeInY;
    @Column(name= "altura_vida_img")
    private int heightLifeImage;
    @Column(name= "verifica_saude_restaurada")
    private int healthRestoreCheck;
    //
    @Column(name= "jogando")
    private boolean playing;
    private Rectangle lifeHitBox;
    private boolean healthRestored;
    // LISTA
    @Column(name="pode_atirar")
    private boolean CanShoot;
    // VARIAVEIS CONSTANTES
    @Transient
    private Image IMAGE_LIFE;
    private final long DELAY_BULLET;
    private final int ANGLE = 15;
    private final int MAX_LIFE = 4;
    private final int INITIAL_LIFE = 4;
    private static final int INITIAL_SCORE = 0;
    private final int INITIAL_POSITION_X = 100;
    private final int INITIAL_POSITION_Y = super.getScreenResolution().HEIGHT_SCREEN / 2;
    // CAMINHO PARA AS IMAGENS
    private static final String IMAGE_PATH_LIFE = "src/main/resources/Sprites/Personagem/coracao.png";
    private static final String IMAGE_PATH_PLAYER = "src/main/resources/Sprites/Personagem/Personagem.gif";

    public Player() {
        super.setLife(INITIAL_LIFE);
        super.setPositionInX(INITIAL_POSITION_X);
        super.setPositionInY(INITIAL_POSITION_Y);
        super.setInitialSpeed(4);
        super.setSpeed(super.getInitialSpeed());
        super.setVisibility(true);

        this.DELAY_BULLET = 300;
        this.playing = false;
        this.score = INITIAL_SCORE;
        this.healthRestored = false;
        this.positionLifeInY = (int) (Math.random() * 200);
        this.positionLifeInX = (int) (Math.random() * 150);

        bullets = new ArrayList<Shoot>();
        specialBullet = new ArrayList<SpecialShoot>();
    }

    @Override
    public void load() {
        // IMAGEM NAVE
        ImageIcon loading = new ImageIcon(IMAGE_PATH_PLAYER);
        super.setImage(loading.getImage());
        super.setWidthImage(super.getImage().getWidth(null));
        super.setHeightImage(super.getImage().getHeight(null));
        // IMAGEM VIDA
        ImageIcon loadingImageLife = new ImageIcon(IMAGE_PATH_LIFE);
        this.IMAGE_LIFE = loadingImageLife.getImage();
        this.heightLifeImage = this.IMAGE_LIFE.getHeight(null);
    }

    @Override
    public void update() {
        super.setPositionInX(super.getPositionInX() + displacementX);
        super.setPositionInY(super.getPositionInY() + displacementY);
    }

    public void mover(KeyEvent keyboard) {
        int key = keyboard.getKeyCode();
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            this.displacementY = -((int) super.getSpeed());
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            this.displacementY = ((int) super.getSpeed());
        }
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            this.displacementX = -((int) super.getSpeed());
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            this.displacementX = ((int) super.getSpeed());
        }
    }

    public void parar(KeyEvent keyboard) {
        int key = keyboard.getKeyCode();

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            this.displacementY = 0;
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            this.displacementY = 0;
        }
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            this.displacementX = 0;
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            this.displacementX = 0;
        }
    }

    // MÉTODO ATIRAR TANTO 'TIRO NORMAL' QUANTO O 'SUPER TIRO'
    public void atirar(KeyEvent keyboard) {
        int key = keyboard.getKeyCode();
        long currentTime = System.currentTimeMillis();

        int playerCenterInX = super.getPositionInX() + super.getWidthImage() / 2;
        int playerCenterInY = super.getPositionInY() + super.getHeightImage() / 2;

        if (currentTime - lastBullet < DELAY_BULLET) {
            this.CanShoot = false;
            return;
        } else {
            if (key == KeyEvent.VK_SPACE) {
                Shoot bullet = new Shoot(playerCenterInX, playerCenterInY);
                this.bullets.add(bullet);
                this.CanShoot = true;
            }
            // SUPER TIRO
            if (key == KeyEvent.VK_R && this.getSuperQuantity() > 0) {
                SpecialShoot specialBullet1 = new SpecialShoot(playerCenterInX, playerCenterInY, this.ANGLE);
                SpecialShoot specialBullet2 = new SpecialShoot(playerCenterInX, playerCenterInY,
                        this.ANGLE - this.ANGLE);
                SpecialShoot specialBullet3 = new SpecialShoot(playerCenterInX, playerCenterInY, -this.ANGLE);
                // ADCIONA OS TIROS
                this.specialBullet.add(specialBullet1);
                this.specialBullet.add(specialBullet2);
                this.specialBullet.add(specialBullet3);
                this.superQuantity--;
            }
        }
        lastBullet = currentTime;
    }

    public void restauraVida(Graphics g) {
        if (this.getHealthRestoreCheck() > 0 && this.healthRestored == true && this.getLife() < MAX_LIFE) {
            g.drawImage(IMAGE_LIFE, this.positionLifeInY, this.positionLifeInX, null);
            if (this.getBounds().intersects((new Rectangle(positionLifeInY, positionLifeInX, 50, 50)))) {
                this.setLife(this.getLife() + 1);
                this.healthRestoreCheck--;
                this.healthRestored = false;
                // GERANDO NOVAS POSIÇÕES PRO RETANGULO E IMAGEM
                this.positionLifeInY = ((int) (Math.random() * super.getScreenResolution().WIDTH_SCREEN));
                this.positionLifeInX = ((int) (Math.random() * super.getScreenResolution().HEIGHT_SCREEN));
            }
        }
    }

    // MÉTODO PARA DESENHAR A VIDA DO JOGADOR
    public void desenhaVida(Graphics g) {
        int difference = 70;
        // PARA CADA VIDA DO JOGADOR, DESENHA UMA IMAGEM DA VIDA,
        // ALTERANDO A POSIÇÃO COM BASE NOS CALCULOS PARA DEFINIR
        for (int i = 0; i < super.getLife(); i++) {
            g.drawImage(IMAGE_LIFE, super.getScreenResolution().WIDTH_SCREEN - difference, 10, null);
            difference += heightLifeImage + 5;
        }
    }

    // MÉTODO PARA DESENHAR A PONTUAÇÃO DO JOGADOR
    public void pontuacao(Graphics g) {
        String scoreString = "PONTOS: " + score;
        g.setFont(super.getPixel().deriveFont(Font.PLAIN, 22));
        g.setColor(new Color(255, 209, 0));
        g.drawString(scoreString, 20, 25);
    }

    public void desenhaEliminacoes(Graphics g) {
        String scoreEnemysDeath = "INIMIGOS MORTOS: " + scoreDeadEnemys;
        g.setFont(super.getPixel().deriveFont(Font.PLAIN, 17));
        g.setColor(new Color(255, 209, 0));
        g.drawString(scoreEnemysDeath, 20, 70);
    }

    // GETTERS E SETTERS
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public ArrayList<Shoot> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Shoot> bullets) {
        this.bullets = bullets;
    }

    public ArrayList<SpecialShoot> getSpecialBullet() {
        return specialBullet;
    }

    public void setSpecialBullet(ArrayList<SpecialShoot> specialBullet) {
        this.specialBullet = specialBullet;
    }

    public int getSuperQuantity() {
        return superQuantity;
    }

    public void setSuperQuantity(int superQuantity) {
        this.superQuantity = superQuantity;
    }

    public int getHealthRestoreCheck() {
        return healthRestoreCheck;
    }

    public void setHealthRestoreCheck(int healthRestoreCheck) {
        this.healthRestoreCheck = healthRestoreCheck;
    }

    public int getInitialLife() {
        return INITIAL_LIFE;
    }

    public static int getInitialScore() {
        return INITIAL_SCORE;
    }

    public int getINITIAL_POSITION_X() {
        return INITIAL_POSITION_X;
    }

    public int getINITIAL_POSITION_Y() {
        return INITIAL_POSITION_Y;
    }

    public int getScoreDeadEnemys() {
        return scoreDeadEnemys;
    }

    public void setScoreDeadEnemys(int scoreDeadEnemys) {
        this.scoreDeadEnemys = scoreDeadEnemys;
    }

    public boolean isHealthRestored() {
        return healthRestored;
    }

    public void setHealthRestored(boolean healthRestored) {
        this.healthRestored = healthRestored;
    }

    public boolean isCanShoot() {
        return CanShoot;
    }

    public void setCanShoot(boolean canShoot) {
        CanShoot = canShoot;
    }

    public Rectangle getLifeHitBox() {
        return lifeHitBox;
    }

    public void setLifeHitBox(Rectangle lifeHitBox) {
        this.lifeHitBox = lifeHitBox;
    }
}