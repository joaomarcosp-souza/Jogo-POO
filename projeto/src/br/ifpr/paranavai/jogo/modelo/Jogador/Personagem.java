package br.ifpr.paranavai.jogo.modelo.Jogador;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import br.ifpr.paranavai.jogo.modelo.Base;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.Rectangle;

public class Personagem extends Base {

    private int pontos;
    private int inimigosMortos;
    // VARIAVEIS RESPAWN DA VIDA
    private int vidaMaxima = 4;
    private Rectangle vidaHitBox;
    private int alturaImagemVida;
    private int qtdeRestauraVida;
    private boolean vidaRestaurada;
    private int posicaoVidaX;
    private int posicaoVidaY;
    // TIRO
    private long ultimoTiro;
    private int qtdeSuper;
    private boolean jogando;
    private List<Tiro> tiros;
    // MOVITEMENTAÇÃO
    private List<SuperTiro> supertiro;
    private int deslocamentoEmX, deslocamentoEmY;
    // VARIAVEIS FINAIS
    private final long delayTiro;
    private Image IMAGEM_VIDA;
    private final int ANGULO = 15;
    private final int VIDA_INICIAL = 4;
    private static final int PONTOS_INICIAIS = 0;
    private final int POSICAO_INICIALX = 100;
    private final int POSICAO_INICIALY = super.getTelaTamanho().ALTURA_TELA / 2;
    // CAMINHO PARA AS IMAGENS
    private static final String NAVEIMGJOGADOR = "recursos\\Sprites\\Personagem\\Personagem.gif";
    private static final String VIDAIMGJOGADOR = "recursos\\Sprites\\Personagem\\coracao.png";

    public Personagem() {
        super.setVida(VIDA_INICIAL);
        super.setPosicaoEmX(POSICAO_INICIALX);
        super.setPosicaoEmY(POSICAO_INICIALY);
        super.setVelocidadeInicial(4);
        super.setVelocidade(super.getVelocidadeInicial());
        super.setVisibilidade(true);

        this.delayTiro = 300;
        this.jogando = false;
        this.pontos = PONTOS_INICIAIS;
        this.vidaRestaurada = false;
        this.posicaoVidaX = (int) (Math.random() * 200);
        this.posicaoVidaY = (int) (Math.random() * 150);

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
        if (tecla == KeyEvent.VK_UP || tecla == KeyEvent.VK_W) {
            this.deslocamentoEmY = -((int) super.getVelocidade());
        }
        if (tecla == KeyEvent.VK_DOWN || tecla == KeyEvent.VK_S) {
            this.deslocamentoEmY = ((int) super.getVelocidade());
        }
        if (tecla == KeyEvent.VK_LEFT || tecla == KeyEvent.VK_A) {
            this.deslocamentoEmX = -((int) super.getVelocidade());
        }
        if (tecla == KeyEvent.VK_RIGHT || tecla == KeyEvent.VK_D) {
            this.deslocamentoEmX = ((int) super.getVelocidade());
        }
    }

    public void parar(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();

        if (tecla == KeyEvent.VK_UP || tecla == KeyEvent.VK_W) {
            this.deslocamentoEmY = 0;
        }
        if (tecla == KeyEvent.VK_DOWN || tecla == KeyEvent.VK_S) {
            this.deslocamentoEmY = 0;
        }
        if (tecla == KeyEvent.VK_LEFT || tecla == KeyEvent.VK_A) {
            this.deslocamentoEmX = 0;
        }
        if (tecla == KeyEvent.VK_RIGHT || tecla == KeyEvent.VK_D) {
            this.deslocamentoEmX = 0;
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

    public void restauraVida(Graphics g) {
        if (this.getQtdeRestauraVida() > 0 && this.vidaRestaurada == true && this.getVida() < vidaMaxima) {
            g.drawImage(IMAGEM_VIDA, this.posicaoVidaX, this.posicaoVidaY, null);
            if (this.getBounds().intersects((new Rectangle(posicaoVidaX, posicaoVidaY, 50, 50)))) {
                this.setVida(this.getVida() + 1);
                this.qtdeRestauraVida--;
                this.vidaRestaurada = false;
                // GERANDO NOVAS POSIÇÕES PRO RETANGULO E IMAGEM
                this.posicaoVidaX = ((int) (Math.random() * super.getTelaTamanho().LARGURA_TELA));
                this.posicaoVidaY = ((int) (Math.random() * super.getTelaTamanho().ALTURA_TELA));
            }
        }
    }

    // MÉTODO PARA DESENHAR A VIDA DO JOGADOR
    public void desenhaVida(Graphics g) {
        int diferenca = 70;
        // PARA CADA VIDA DO JOGADOR, DESENHA UMA IMAGEM DA VIDA,
        // ALTERANDO A POSIÇÃO COM BASE NOS CALCULOS PARA DEFINIR
        for (int i = 0; i < super.getVida(); i++) {
            g.drawImage(IMAGEM_VIDA, super.getTelaTamanho().LARGURA_TELA - diferenca, 10, null);
            diferenca += alturaImagemVida + 5;
        }
    }

    // MÉTODO PARA DESENHAR A PONTUAÇÃO DO JOGADOR
    public void pontuacao(Graphics g) {
        String pontosSTR = "PONTOS: " + pontos;
        g.setFont(super.getPixel().deriveFont(Font.PLAIN, 22));
        g.setColor(new Color(255, 209, 0));
        g.drawString(pontosSTR, 20, 25);
    }

    public void desenhaEliminacoes(Graphics g) {
        String qtdeMorte = "INIMIGOS MORTOS: " + inimigosMortos;
        g.setFont(super.getPixel().deriveFont(Font.PLAIN, 17));
        g.setColor(new Color(255, 209, 0));
        g.drawString(qtdeMorte, 20, 70);
    }

    // MÉTODOS GETTERS E SETTERS
    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getInimigosMortos() {
        return inimigosMortos;
    }

    public void setInimigosMortos(int inimigosMortos) {
        this.inimigosMortos = inimigosMortos;
    }

    public int getQtdeSuper() {
        return qtdeSuper;
    }

    public void setQtdeSuper(int qtdeSuper) {
        this.qtdeSuper = qtdeSuper;
    }

    public boolean isJogando() {
        return jogando;
    }

    public void setJogando(boolean jogando) {
        this.jogando = jogando;
    }

    public int getVidaInicial() {
        return VIDA_INICIAL;
    }

    public static int getPontosIniciais() {
        return PONTOS_INICIAIS;
    }

    public List<Tiro> getTiros() {
        return tiros;
    }

    public void setTiros(List<Tiro> tiros) {
        this.tiros = tiros;
    }

    public List<SuperTiro> getSupertiro() {
        return supertiro;
    }

    public void setSupertiro(List<SuperTiro> supertiro) {
        this.supertiro = supertiro;
    }

    public int getPOSICAO_INICIALX() {
        return POSICAO_INICIALX;
    }

    public int getPOSICAO_INICIALY() {
        return POSICAO_INICIALY;
    }

    public Rectangle getVidaHitBox() {
        return vidaHitBox;
    }

    public void setVidaHitBox(Rectangle vidaHitBox) {
        this.vidaHitBox = vidaHitBox;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public int getPosicaoVidaX() {
        return posicaoVidaX;
    }

    public void setPosicaoVidaX(int posicaoVidaX) {
        this.posicaoVidaX = posicaoVidaX;
    }

    public int getPosicaoVidaY() {
        return posicaoVidaY;
    }

    public void setPosicaoVidaY(int posicaoVidaY) {
        this.posicaoVidaY = posicaoVidaY;
    }

    public int getQtdeRestauraVida() {
        return qtdeRestauraVida;
    }

    public void setQtdeRestauraVida(int qtdeRestauraVida) {
        this.qtdeRestauraVida = qtdeRestauraVida;
    }

    public boolean isVidaRestaurada() {
        return vidaRestaurada;
    }

    public void setVidaRestaurada(boolean vidaRestaurada) {
        this.vidaRestaurada = vidaRestaurada;
    }

}