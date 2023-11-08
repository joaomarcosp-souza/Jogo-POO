package br.ifpr.paranavai.jogo.modelo.model;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.awt.Font;
import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import br.ifpr.paranavai.jogo.modelo.Util.TamanhoTela;
import java.awt.FontFormatException;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_jogador", unique = true, nullable = false)
    private Integer idBase;

    @Column(name= "vida")
    private int life;
    @Column(name = "image")
    private Image image;
    @Column(name = "velocidade")
    private Font pixel = null;
    @Column(name = "visibilidade")
    private boolean visibility;
    @Column(name = "posicaoX")    
    private int positionInX;
    @Column(name = "posicaoY")
    private int positionInY;
    @Column(name = "largura_imagem")
    private int widthImage;
    @Column(name= "altura_imagem")
    private int heightImage;
    @Column(name= "velocidade")
    private double speed;
    private TamanhoTela screenResolution;
    private double initialSpeed;

    public Base() {
        this.visibility = true;
        //PUXANDO INFORMAÇÕES DA TELA 
        screenResolution = new TamanhoTela();
        screenResolution.carregar();
        // CARREGANDO UMA NOVA FONTE
        try {
            // CARREGA A FONTE A PARTIR DO ARQUIVO
            pixel = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Fontes/pixel_fonte.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    // MÉTODOS ABSTRATO BASICO PARA 'CARREGAR' É 'ATUALIZAR'
    public abstract void carregar();
    
    // CAIXA DE COLISÃO
    public Rectangle getBounds() {
        return new Rectangle(positionInX, positionInY, widthImage, heightImage);
    }
    //NÃO E UM MÉTODO ABSTRAT, POR CAUSA DAS CLASSE DE TELA
    public void atualizar(){}

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Font getPixel() {
        return pixel;
    }

    public void setPixel(Font pixel) {
        this.pixel = pixel;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public TamanhoTela getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(TamanhoTela screenResolution) {
        this.screenResolution = screenResolution;
    }

    public double getInitialSpeed() {
        return initialSpeed;
    }

    public void setInitialSpeed(double initialSpeed) {
        this.initialSpeed = initialSpeed;
    }

    public int getPositionInX() {
        return positionInX;
    }

    public void setPositionInX(int positionInX) {
        this.positionInX = positionInX;
    }

    public int getPositionInY() {
        return positionInY;
    }

    public void setPositionInY(int positionInY) {
        this.positionInY = positionInY;
    }

    public int getWidthImage() {
        return widthImage;
    }

    public void setWidthImage(int widthImage) {
        this.widthImage = widthImage;
    }

    public int getHeightImage() {
        return heightImage;
    }

    public void setHeightImage(int heightImage) {
        this.heightImage = heightImage;
    };

    //GETTERS E SETTERS
    
}
