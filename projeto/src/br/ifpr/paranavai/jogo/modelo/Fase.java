package br.ifpr.paranavai.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import br.ifpr.paranavai.jogo.modelo.inimigos.Inimigo_naves;
import br.ifpr.paranavai.jogo.modelo.inimigos.Inimigo_boss;
import br.ifpr.paranavai.jogo.modelo.inimigos.Inimigo_meteorito;

import java.awt.Font;
import java.awt.FontMetrics;

//import java.awt.Dimension;
//import java.awt.Toolkit;

public class Fase extends JPanel implements ActionListener {
    private Image fundo;
    private Personagem personagem;
    private List<Inimigo_naves> inimigo_naves;
    private List<Inimigo_meteorito> inimigo_meteorito;
    private List<Inimigo_boss> inimigo_boss;

    private int larguraTela = 1200; // Definindo o tamanho da Tela

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);

        // Imagem de fundo
        ImageIcon carregando = new ImageIcon("recursos\\sprites_Fundos\\planeta.jpg");
        this.fundo = carregando.getImage();

        // Inicializa o Persnagem
        personagem = new Personagem();
        personagem.carregar();

        //
        addKeyListener(new TecladoAdapter());

        // Timer
        Timer timer = new Timer(5, this); // atualizando o personagem e redensenhado na tela em intervalos regulares.
        timer.start(); // Iniciando

        // Metodos Informações

        // Metodos Inimigos
        inicializa_Nave_Inimiga(); // Iniciando as Naves
        inicializa_Metorito_Inimigo(); // Iniciando os Meteorito
        inicializa_boss();

    }

    // Inicializa NAVE inimiga
    public void inicializa_Nave_Inimiga() {

        // COMEÇO DA INICIALIZAÇÃO DAS NAVES
        inimigo_naves = new ArrayList<Inimigo_naves>();

        // int larguraInimigo = 30; // Tamanho inimigos
        int alturaInimigo = 30;

        Timer timer = new Timer(900, new ActionListener() { // intervalo (em milissegundos) para controlar a taxa de
                                                            // spawn de inimigos
            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmX = larguraTela; // Defina a posição inicial do novo inimigo
                int posicaoEmY = (int) (Math.random() * (600 - alturaInimigo));
                inimigo_naves.add(new Inimigo_naves(posicaoEmX, posicaoEmY));
            }
        });
        timer.setRepeats(true);
        timer.start(); // FIM DOS INIMIGOS NAVE
    }

    // Inicializa METEORIOTO
    public void inicializa_Metorito_Inimigo() {
        inimigo_meteorito = new ArrayList<Inimigo_meteorito>();

        int alturaInimigo = 50;

        // Obtenha a dimensão da tela
        // Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // int larguraTela = (int) screenSize.getWidth();

        Timer timer_Meteorito = new Timer(1200, e -> {
            int posicaoEmY = -alturaInimigo;
            int posicaoEmX = (int) (Math.random() * (larguraTela - alturaInimigo));
            inimigo_meteorito.add(new Inimigo_meteorito(posicaoEmX, posicaoEmY));
        });
        timer_Meteorito.setRepeats(true);
        timer_Meteorito.start();
    }

    // Inicializa BOSS
    public void inicializa_boss() {
        inimigo_boss = new ArrayList<Inimigo_boss>();

        int alturaInimigo = 30;

        Timer timer_inimigo_boss = new Timer(500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmX = larguraTela;
                int posicaoEmY = (int) (Math.random() * (500 - alturaInimigo));
                inimigo_boss.add(new Inimigo_boss(posicaoEmX, posicaoEmY));
            }
        });
        timer_inimigo_boss.setRepeats(true);
        timer_inimigo_boss.start();
    }

    // A SER FEITO AINDA
    public void colisao() {

    }

    public void paint(Graphics g) {
        super.paint(g); // Chama o método paint da superclasse para limpar a tela antes de a redesenhar

        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(this.fundo, 0, 0, null);
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), null);

        // CARREGANDO INIMIGO NAVE
        for (int i = 0; i < inimigo_naves.size(); i++) {
            Inimigo_naves ini = inimigo_naves.get(i);
            ini.carregar();
            graficos.drawImage(ini.getImagem(), ini.getPosicaoEmX(), ini.getPosicaoEmY(), null);
        } // FIM NAVE

        // CARREGANDO INIMIGO METEORITO
        for (int i = 0; i < inimigo_meteorito.size(); i++) {
            Inimigo_meteorito mete = inimigo_meteorito.get(i);
            mete.carregar();
            graficos.drawImage(mete.getImagem_meteoro(), mete.getPosicaoEmX(), mete.getPosicaoEmY(), null);
        } // FIM METEORITO

        // CARREGANDO INIMIGO BOSS
        // for (int i = 0; i < inimigo_boss.size(); i++) {
        // Inimigo_boss boss = inimigo_boss.get(i);
        // boss.carregar();
        // graficos.drawImage(boss.getImagem_boss(), boss.getPosicaoEmX(),
        // boss.getPosicaoEmY(), null);
        // } // FIM BOSS

        informacaos(graficos);
        desenharVidas(graficos);

        g.dispose();
    }

    // 
    public void informacaos(Graphics pont) {
        // DEFINE A STRING DE EXIBIÇÃO
            String pontos = "Pontuação: " + 1 + 1;

            // CRIA UM ESTILO DE FONTE
            Font fonte = new Font("Consoles", Font.BOLD, 25);
            // SETA A COR DA FONTE
            pont.setColor(getBackground().RED);
            // SETA O ESTILO DE FONTE
            pont.setFont(fonte);

            // DESENHA A STRING COM A POSIÇÃO (x,y)
            pont.drawString(pontos, 10, 30);
    }

    // MÉTODO PARA DESENHAR VIDAS NA TELA
    public void desenharVidas(Graphics life) {
        // DEFINE A STRING DE EXIBIÇÃO
        String vida = "Vidas";
        // CRIA UM ESTILO DE FONTE
        Font estilo = new Font("Consolas", Font.BOLD, 10);
        // CRIA A MÉTRICA DA FONTE
        FontMetrics metrica = this.getFontMetrics(estilo);

        // OBTÉM O TAMANHO DA STRING NA TELA
        int width = metrica.stringWidth(vida);
        // CALCUPA A DISTÂNCIA DA BORDA PARA POSICIONAR A STRING
        int distancia = (15 * personagem.getVidas()) + (5 * personagem.getVidas()) + 100 + width;

        // PARA CADA VIDA DO JOGADOR, DESENHA UMA IMAGEM DA VIDA,
        // ALTERANDO A POSIÇÃO COM BASE NOS CALCULOS PARA DEFINIR
        // A POSIÇÃO DE CADA UMA EM (x,y)
        for (int i = 0; i < personagem.getVidas(); i++) {
            life.drawImage(personagem.getImagem_vida(), (larguraTela + 50) - width, 10, this);
            width += personagem.getAlturaImagem_Vida() + 5;
        }

        // SETA A COR DA FONTE
        life.setColor(getBackground().RED);
        // SETA O ESTILO DE FONTE
        life.setFont(estilo);
        // DESENHA A STRING COM A POSIÇÃO (x,y)
        life.drawString(vida, personagem.getLarguraImagem_Vida() - distancia, 10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atuzaliza();
        repaint();

        // Verificando se o inimigo esta visivel e atualizando a sua posicação atraves
        // do metodo 'Atualizar', ao ficar invisivel o inimigo e excluido
        // A classe Iterator e como um ponteiro em C que permite interajir com um certo
        // objeto(inimigo) de uma lista de forma especifica e em sequencia
        Iterator<Inimigo_naves> iterator_naves = inimigo_naves.iterator();
        while (iterator_naves.hasNext()) {
            Inimigo_naves ini = iterator_naves.next();
            if (ini.isVisivel()) {
                ini.atualizar();
            } else {
                iterator_naves.remove();
            }
        } // FIM ITERATOR NAVES

        // Atualiza a posição do metorito
        Iterator<Inimigo_meteorito> iterator_meteorito = inimigo_meteorito.iterator();
        while (iterator_meteorito.hasNext()) {
            Inimigo_meteorito mete = iterator_meteorito.next();
            if (mete.isVisibilidade()) {
                mete.atualizar();
            } else {
                iterator_meteorito.remove();
            }
        } // FIM

        // METODO DE ATUALIZAÇÃO DO BOSS
        Iterator<Inimigo_boss> iterator_boss = inimigo_boss.iterator();
        while (iterator_boss.hasNext()) {
            Inimigo_boss boss = iterator_boss.next();
            if (boss.isVisibilidade()) {
                boss.atualizar();
            } else {
                iterator_boss.remove();
            }
        }

    }


    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            personagem.tecla_Precionada(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            personagem.tecla_solta(e);
        }
    }
}