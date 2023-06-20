package br.ifpr.paranavai.jogo.modelo.Jogador;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;

public class Personagem {
    private int pontos;
    private int posicaoEmX, posicaoEmY;
    private int deslocamentoEmX, deslocamentoEmY;
    private int larguraImagem, alturaImagem, larguraImagem_Vida, alturaImagem_Vida;
    private Image imagem;
    private List<Tiro> tiros;
    private boolean visibilidade;
    private Image imagem_vida;
    private Font pixel = null;

    private int VIDAS = 3;
    private int largureTela = 1200;

    private static final int POSICAO_INICIAL_EM_X = 100;
    private static final int POSICAO_INICIAL_EM_Y = 300;
    private static final int VELOCIDADE = 5;

    public Personagem() {
        this.posicaoEmX = POSICAO_INICIAL_EM_X;
        this.posicaoEmY = POSICAO_INICIAL_EM_Y;
        this.visibilidade = true;

        tiros = new ArrayList<Tiro>();

        try {
            // CARREGA A FONTE A PARTIR DO ARQUIVO
            pixel = Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\fontes\\pixel_fonte.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void carregar() {
        // IMAGEM PERSONAGEM
        ImageIcon carregador = new ImageIcon("recursos\\sprites_personagem\\xwing.png");
        this.imagem = carregador.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
        // IMAGEM VIDAS
        ImageIcon carrega_vida = new ImageIcon("recursos\\sprites_personagem\\heart.png");
        this.imagem_vida = carrega_vida.getImage();
        this.alturaImagem_Vida = this.imagem_vida.getWidth(null);
        this.larguraImagem_Vida = this.imagem_vida.getHeight(null);
    }
    // Classe para atualizar a posicao
    public void atualiza() {
        // Atualiza a posição do personagem
        posicaoEmX += deslocamentoEmX;
        posicaoEmY += deslocamentoEmY;
    }
    // MÉTODO TIRO
    public void tiro_simples() {
        this.tiros.add(new Tiro(posicaoEmX + larguraImagem / 2, posicaoEmY + (alturaImagem / 2)));
    }
    //
    public Rectangle getBounds() {
        return new Rectangle(posicaoEmX, posicaoEmY, larguraImagem, alturaImagem);
    }

    // MÉTODO DE PONTUAÇÃO DO JOGADOR
    public void pontos(Graphics pont) {
        // TITULO DO JOGO
        String pont_string = "PONTOS: " + pontos;
        Font fonte = pixel;
        // ESTILO DA FONTEd
        fonte = fonte.deriveFont(Font.BOLD, 20);
        // COR FONTE
        pont.setColor(new Color(255, 209, 70)); // COR DO TITULO AMARELO
        pont.setFont(fonte);
        // DESENHA A STRING COM A POSIÇÃO (x,y)
        pont.drawString(pont_string, 10, 20);
    }

    // MÉTODO DA VIDA DO JOGADOR
    public void vidas(Graphics life) {
        JPanel panel = new JPanel();
        // DEFINE A STRING DE EXIBIÇÃO
        String vida = "Vidas";
        // CRIA UM ESTILO DE FONTE
        Font estilo = new Font("Consolas", Font.BOLD, 10);
        // CRIA A MÉTRICA DA FONTE
        FontMetrics metrica = panel.getFontMetrics(estilo);
        // OBTÉM O TAMANHO DA STRING NA TELA
        int width = metrica.stringWidth(vida);
        // CALCUPA A DISTÂNCIA DA BORDA PARA POSICIONAR A STRING
        int distancia = (15 * VIDAS) + (5 * VIDAS) + 100 + width;
        // PARA CADA VIDA DO JOGADOR, DESENHA UMA IMAGEM DA VIDA,
        // ALTERANDO A POSIÇÃO COM BASE NOS CALCULOS PARA DEFINIR
        // A POSIÇÃO DE CADA UMA EM (x,y)
        for (int i = 0; i < VIDAS; i++) {
            life.drawImage(imagem_vida, (largureTela + 30) - width, 10, null);
            width += alturaImagem_Vida + 5;
        }
        // SETA A COR DA FONTE
        life.setColor(Color.WHITE);
        // SETA O ESTILO DE FONTE
        life.setFont(estilo);
        // DESENHA A STRING COM A POSIÇÃO (x,y)
        life.drawString(vida, larguraImagem_Vida - distancia, 10);
    }

    // COMEÇO DO METODO MOVIMENTO
    public void tecla_Precionada(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();

        // TECLA TIRO
        if (tecla == KeyEvent.VK_SPACE) {
            tiro_simples();
        }

        // TECLAS MOVIMENTAÇÃO
        if (tecla == KeyEvent.VK_W || tecla == KeyEvent.VK_UP) {
            deslocamentoEmY = -VELOCIDADE;
        }
        if (tecla == KeyEvent.VK_S || tecla == KeyEvent.VK_DOWN) {
            deslocamentoEmY = VELOCIDADE;
        }

        if (tecla == KeyEvent.VK_A || tecla == KeyEvent.VK_LEFT) {
            deslocamentoEmX = -VELOCIDADE;
        }
        if (tecla == KeyEvent.VK_D || tecla == KeyEvent.VK_RIGHT) {
            deslocamentoEmX = VELOCIDADE;
        }
    }

    public void tecla_solta(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();

        if (tecla == KeyEvent.VK_W || tecla == KeyEvent.VK_UP) {
            deslocamentoEmY = 0;
        }

        if (tecla == KeyEvent.VK_S || tecla == KeyEvent.VK_DOWN) {
            deslocamentoEmY = 0;
        }

        if (tecla == KeyEvent.VK_A || tecla == KeyEvent.VK_LEFT) {
            deslocamentoEmX = 0;
        }

        if (tecla == KeyEvent.VK_D || tecla == KeyEvent.VK_RIGHT) {
            deslocamentoEmX = 0;
        }
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getVIDAS() {
        return VIDAS;
    }

    public void setVIDAS(int vidas) {
        this.VIDAS = vidas;
    }

    public List<Tiro> getTiros() {
        return tiros;
    }

    public void setTiros(List<Tiro> tiros) {
        this.tiros = tiros;
    }

    public boolean isVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    public int getPosicaoEmX() {
        return posicaoEmX;
    }

    public void setPosicaoEmX(int posicaoEmX) {
        this.posicaoEmX = posicaoEmX;
    }

    public int getPosicaoEmY() {
        return posicaoEmY;
    }

    public void setPosicaoEmY(int posicaoEmY) {
        this.posicaoEmY = posicaoEmY;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getDeslocamentoEmX() {
        return deslocamentoEmX;
    }

    public void setDeslocamentoEmX(int deslocamentoEmX) {
        this.deslocamentoEmX = deslocamentoEmX;
    }

    public int getDeslocamentoEmY() {
        return deslocamentoEmY;
    }

    public void setDeslocamentoEmY(int deslocamentoEmY) {
        this.deslocamentoEmY = deslocamentoEmY;
    }

    public static int getPosicaoInicialEmX() {
        return POSICAO_INICIAL_EM_X;
    }

    public static int getPosicaoInicialEmY() {
        return POSICAO_INICIAL_EM_Y;
    }
}
