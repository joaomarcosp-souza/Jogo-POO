package br.ifpr.paranavai.jogo.modelo.Jogador;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.Image;

public class Personagem extends EntidadeJogador {
    private int vida;
    private int pontos;
    private int velocidade;
    private long delayTiro;
    private long ultimoTiro;
    private List<Tiro> tiros;
    private int deslocamentoEmX;
    private int deslocamentoEmY;
    private int alturaImagemVida;
    private boolean jogando;
    private int qtdeSuper;
    private List<SuperTiro> supertiro;
    private boolean vidaGanha = false;
    // POSIÇÃO DOS SUPER TIROS
    private final int ANGULO = 15;
    // VARIAVEIS INICIAIS QUE NÃO SERÃO ALTERADAS
    private Image IMAGEM_VIDA;
    private final int VELOCIDADEINICIAL = 2;
    private static final int VIDAINICIAL = 4;
    private static final int PONTOSINICIAIS = 0;
    private static final int POSICAOINICIALX = 100;
    private final int POSICAOINICIALY = super.getTelaTamanho().ALTURA_TELA / 2;
    // CAMINHO PARA AS IMAGENS
    private static final String NAVEIMGJOGADOR = "recursos\\Sprites\\Personagem\\Personagem.gif";
    private static final String VIDAIMGJOGADOR = "recursos\\Sprites\\Personagem\\coracao.png";

    public Personagem() {
        super.setPosicaoEmX(POSICAOINICIALX);
        super.setPosicaoEmY(POSICAOINICIALY);
        super.setVisibilidade(true);

        this.delayTiro = 300;
        this.jogando = false;
        this.vida = VIDAINICIAL;
        this.pontos = PONTOSINICIAIS;
        this.velocidade = VELOCIDADEINICIAL;

        tiros = new ArrayList<Tiro>();
        supertiro = new ArrayList<SuperTiro>();
    }

    @Override
    public void carregar() {
        // IMAGEM NAVE
        ImageIcon carregando = new ImageIcon(NAVEIMGJOGADOR);
        super.setImagem(carregando.getImage());
        super.setLarguraImagem(super.getImagem().getWidth(null));
        super.setAlturaImagem(super.getImagem().getHeight(null));
        // IMAGEM VIDA
        ImageIcon carregaVida = new ImageIcon(VIDAIMGJOGADOR);
        this.IMAGEM_VIDA = carregaVida.getImage();
        this.alturaImagemVida = this.IMAGEM_VIDA.getHeight(null);
    }

    @Override
    public void atualizar() {
        super.setPosicaoEmX(super.getPosicaoEmX() + deslocamentoEmX);
        super.setPosicaoEmY(super.getPosicaoEmY() + deslocamentoEmY);
    }

    public void mover(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();
        if (tecla == KeyEvent.VK_UP) {
            deslocamentoEmY = -velocidade;
        }
        if (tecla == KeyEvent.VK_DOWN) {
            deslocamentoEmY = velocidade;
        }
        if (tecla == KeyEvent.VK_LEFT) {
            deslocamentoEmX = -velocidade;
        }
        if (tecla == KeyEvent.VK_RIGHT) {
            deslocamentoEmX = velocidade;
        }

        if (tecla == KeyEvent.VK_SHIFT) {
            super.setPosicaoEmX(super.getPosicaoEmX() + 200);
        }
    }

    public void parar(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();

        if (tecla == KeyEvent.VK_UP) {
            deslocamentoEmY = 0;
        }
        if (tecla == KeyEvent.VK_DOWN) {
            deslocamentoEmY = 0;
        }
        if (tecla == KeyEvent.VK_LEFT) {
            deslocamentoEmX = 0;
        }
        if (tecla == KeyEvent.VK_RIGHT) {
            deslocamentoEmX = 0;
        }
    }

    // MÉTODO ATIRAR TANTO 'TIRO NORMAL' QUANTO O 'SUPER TIRO'
    public void atirar(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();
        long tempoAtual = System.currentTimeMillis();

        int centroPersonagemX = super.getPosicaoEmX() + super.getLarguraImagem() / 2;
        int centroPersonagemY = super.getPosicaoEmY() + super.getAlturaImagem() / 2;

        if (tempoAtual - ultimoTiro < delayTiro) {
            return;
        } else {
            if (tecla == KeyEvent.VK_SPACE) {
                Tiro tiro = new Tiro(centroPersonagemX, centroPersonagemY);
                this.tiros.add(tiro);
            }
            // SUPER TIRO
            if (tecla == KeyEvent.VK_R && this.getQtdeSuper() > 0) {
                SuperTiro superTiro1 = new SuperTiro(centroPersonagemX, centroPersonagemY, this.ANGULO);
                SuperTiro superTiro2 = new SuperTiro(centroPersonagemX, centroPersonagemY, this.ANGULO - this.ANGULO);
                SuperTiro superTiro3 = new SuperTiro(centroPersonagemX, centroPersonagemY, -this.ANGULO);
                // ADCIONA OS TIROS
                this.supertiro.add(superTiro1);
                this.supertiro.add(superTiro2);
                this.supertiro.add(superTiro3);
                this.qtdeSuper--;
            }
        }
        ultimoTiro = tempoAtual;

    }

    // MÉTODO PARA DESENHAR A PONTUAÇÃO DO JOGADOR
    public void desenhaPontos(Graphics g) {
        String pontosSTR = "PONTOS: " + pontos;
        g.setFont(super.getPixel().deriveFont(Font.PLAIN, 22));
        g.setColor(new Color(255, 209, 0));
        g.drawString(pontosSTR, 20, 25);
    }

    public void restauraVida() {
        int restoVida = 800;
        int MaximoVida = 4;
        // RESTAURA A VIDA (A IMPLEMENTAR)
        if (this.getPontos() % restoVida == 0 && this.getVida() < MaximoVida && !vidaGanha && this.getPontos() > 0) {
            this.setVida(this.getVida() + 1);
            vidaGanha = true;
        }

        if (this.getPontos() % restoVida != 0) {
            vidaGanha = false;
        }
    }

    // MÉTODO PARA DESENHAR A VIDA DO JOGADOR
    public void desenhaVida(Graphics g) {
        int diferenca = 70;
        // PARA CADA VIDA DO JOGADOR, DESENHA UMA IMAGEM DA VIDA,
        // ALTERANDO A POSIÇÃO COM BASE NOS CALCULOS PARA DEFINIR
        for (int i = 0; i < this.vida; i++) {
            g.drawImage(IMAGEM_VIDA, getTelaTamanho().LARGURA_TELA - diferenca, 10, null);
            diferenca += alturaImagemVida + 5;
        }
    }

    // MÉTODOS GETTERS E SETTERS
    public List<Tiro> getTiros() {
        return tiros;
    }

    public void setTiros(List<Tiro> tiros) {
        this.tiros = tiros;
    }

    public int getPOSICAOINICIALX() {
        return POSICAOINICIALX;
    }

    public int getPOSICAOINICIALY() {
        return POSICAOINICIALY;
    }

    public int getVIDAINICIAL() {
        return VIDAINICIAL;
    }

    public List<SuperTiro> getSupertiro() {
        return supertiro;
    }

    public void setSupertiro(List<SuperTiro> supertiro) {
        this.supertiro = supertiro;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public static int getPontosiniciais() {
        return PONTOSINICIAIS;
    }

    public boolean isJogando() {
        return jogando;
    }

    public void setJogando(boolean jogando) {
        this.jogando = jogando;
    }

    public int getQtdeSuper() {
        return qtdeSuper;
    }

    public void setQtdeSuper(int qtdeSuper) {
        this.qtdeSuper = qtdeSuper;
    }
}