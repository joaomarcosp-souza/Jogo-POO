package br.ifpr.paranavai.jogo.model.Enemies;

import java.awt.Color;
import java.awt.Graphics;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.swing.ImageIcon;

import br.ifpr.paranavai.jogo.model.GraphicsElements;
import br.ifpr.paranavai.jogo.model.Character.Player;

import java.awt.Font;

@Entity
@Table(name = "tb_naves")
public class Naves extends GraphicsElements {

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    private static final String PATH_IMAGE = "/Sprites/Inimigos/tfighter.png";

    // CONSTRUTOR PADRÃO
    public Naves() {
        super();
    }

    // CONSTRUTOR COM PARAMETRO
    public Naves(int posicaoEmX, int posicaoEmY, int INIMIGOSVIDA) {
        super.setPositionInX(posicaoEmX);
        super.setPositionInY(posicaoEmY);
        super.setLife(INIMIGOSVIDA);
        super.setInitialSpeed(5);
        super.setSpeed(super.getInitialSpeed());
        super.setVisibility(true);
    }

    @Override
    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource(PATH_IMAGE));
        super.setImage(loading.getImage());
        super.setWidthImage(super.getImage().getWidth(null));
        super.setHeightImage(super.getImage().getHeight(null));
    }

    @Override
    public void update() {
        super.setPositionInX((int) (super.getPositionInX() - super.getSpeed()));
        if (super.getPositionInX() < -50) {
            super.setVisibility(false);
        }

        // VERIFICANDO SE A NAVE INIMIGA ESTA NASCENDO DENTRO DOS LIMETES DA TELA
        if (super.getPositionInY() < 0) {
            super.setPositionInY(150);
        } else if (super.getPositionInY() > super.getScreenResolution().HEIGHT_SCREEN - super.getHeightImage()) {
            super.setPositionInY(super.getScreenResolution().HEIGHT_SCREEN - super.getHeightImage());
        }
    }

    // MÉTODO DE VIDA NAVES
    public void vidas(Graphics g) {
        int cubeSize = 10; // TAMANHO DO CUBO
        int CubeSpacing = 5; // ESPAÇAMENTO ENTRE OS CUBOS
        int offsetRectangle = 44;
        // CALCULANDO A POSIÇÃO DE CADA CUBO
        int maxLife = super.getLife();
        int cubePositionX = super.getPositionInX() - ((cubeSize + CubeSpacing) * maxLife) / 2;
        int cubePositionY = super.getPositionInY() + super.getHeightImage() + 5;
        // UM CUBO PARA CADA VIDA
        for (int i = 0; i < maxLife; i++) {
            // CALCULA A POSIÇÃO DO CUBO ATUAL
            int cuboPosX = cubePositionX + (cubeSize + CubeSpacing) * i;
            // DESENHADO O CUBO(VIDA)
            g.setColor(Color.RED);
            g.fillRect((cuboPosX + offsetRectangle), cubePositionY, cubeSize, cubeSize);
        }
    }

    // INFORMA A VELOCIDADE ATUAL DA NAVE
    public void inimigoDados(Graphics g) {
        // VELOCIDADE
        String shipSpeed = "INIMIGOS VELOCIDADE: " + super.getSpeed();
        g.setFont(super.getPixel().deriveFont(Font.PLAIN, 17));
        g.setColor(new Color(255, 209, 0));
        g.drawString(shipSpeed, 20, 50);
    }

}
