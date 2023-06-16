package br.ifpr.paranavai.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.Rectangle;

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
import br.ifpr.paranavai.jogo.modelo.Jogador.Personagem;
import br.ifpr.paranavai.jogo.modelo.Jogador.Tiro;
import br.ifpr.paranavai.jogo.modelo.inimigos.Inimigo_boss;
import br.ifpr.paranavai.jogo.modelo.inimigos.Inimigo_meteorito;

import java.awt.Font;
import java.awt.FontMetrics;

public class Fase extends JPanel implements ActionListener {
    private Image fundo;
    private Image fundo_2;
    private Image explosao;
    private Image fundo_morte;
    private Image caveira_fundo;
    private int POSICAO_CAVEIRA = 450;
    private Personagem personagem;
    private int vidas = 3;
    private List<Inimigo_naves> inimigo_naves;
    private List<Inimigo_meteorito> inimigo_meteorito;
    private List<Inimigo_boss> inimigo_boss;
    private int larguraTela = 1200; // TAMANHO DA TELA
    private int alturaTela = 700;
    private boolean jogando;

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);

        // IMAGEM DE FUNDO
        ImageIcon carregando = new ImageIcon("recursos\\sprites_Fundos\\planeta.jpg");
        this.fundo = carregando.getImage();

        // IMAGEM SEGUNDO FUNDO
        ImageIcon fundo_2 = new ImageIcon("recursos\\sprites_Fundos\\fundo_dois.jpg");
        this.fundo_2 = fundo_2.getImage();

        ImageIcon explosaoIcon = new ImageIcon("recursos\\sprites_tiros\\explosao.png");
        this.explosao = explosaoIcon.getImage();
        // INICIALIZANDO O PERSONAGEM
        personagem = new Personagem();
        personagem.carregar();
        // INICIALIZANDO O METODO DE LEITURA DO TECLADO
        addKeyListener(new TecladoAdapter());

        // ATUALIZANDO O PERSONAGEM E REDENSENHADO NA TELA EM INTERVALOS REGULARES.
        Timer timer = new Timer(5, this);
        timer.start(); // START

        // INICIALIZANDO MÉTODOS INIMIGOS
        inicializa_Nave_Inimiga(); // NAVES
        inicializa_Metorito_Inimigo(); // METEORITOS
        inicializa_boss(); // BOSS(TESTE)
        jogando = true;

    }

    // INICIANDO POSIÇÃO DAS NAVES INIMIGAS ALEATORIAMENTE
    public void inicializa_Nave_Inimiga() {
        inimigo_naves = new ArrayList<Inimigo_naves>();
        // int larguraInimigo = 30; // Tamanho inimigos
        int alturaInimigo = 30;

        // INTERVALO (EM MILISSEGUNDOS) PARA CONTROLAR A TAXA DE // SPAWN DE INIMIGOS
        Timer timer = new Timer(800, new ActionListener() {
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

    // INICIANDO POSIÇÃO DO METEORITO ALEATORIAMENTE
    public void inicializa_Metorito_Inimigo() {
        inimigo_meteorito = new ArrayList<Inimigo_meteorito>();
        int alturaInimigo = 40;

        // TIMER PARA SPAWNAR O METEORITO
        Timer timer_Meteorito = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int posicaoEmY = -alturaInimigo;
                int posicaoEmX = (int) (Math.random() * (larguraTela - alturaInimigo));
                inimigo_meteorito.add(new Inimigo_meteorito(posicaoEmX, posicaoEmY));
            }
        });
        timer_Meteorito.setRepeats(true);
        timer_Meteorito.start();
    }// FIM MÉTODO METEORITO

    // INICIALIZANDO A POSIÇÃO DO BOSS ALEATORIAMENTE
    public void inicializa_boss() {
        inimigo_boss = new ArrayList<Inimigo_boss>();
        int alturaInimigo = 30;

        // TIMER PARA SPAWNAR O BOSS (TESTE)
        Timer timer_inimigo_boss = new Timer(8000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmX = larguraTela;
                int posicaoEmY = (int) (Math.random() * (500 - alturaInimigo));
                inimigo_boss.add(new Inimigo_boss(posicaoEmX, posicaoEmY));
            }
        });
        timer_inimigo_boss.setRepeats(true); // SETAR PARA REPETIR OU NÃO
        timer_inimigo_boss.start(); // STARTANDO
    }// FIM MÉTODO BOSS

    public void paint(Graphics g) {
        // CHAMA O MÉTODO PAINT DA SUPERCLASSE PARA LIMPAR A TELA ANTES DE A REDESENHAR
        super.paint(g);
        Graphics2D graficos = (Graphics2D) g;

        if (jogando == true) {
            graficos.drawImage(this.fundo, 0, 0, null);

            if (personagem.getPontos() >= 200) {
                graficos.drawImage(this.fundo_2, 0, 0, null);
            }

            // CARREGANDO TIRO PERSONAGEM
            List<Tiro> tiros = personagem.getTiros();
            for (int i = 0; i < tiros.size(); i++) {
                Tiro tiro = tiros.get(i);
                tiro.carregar();
                graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), null);
            }
            // CARREGANDO A IMAGEM DO PERSNAGEM
            // ESTA AQUI EMBAIXO PARA A IMG FICAR ACIMA DA IMAGEM DO TIROW
            graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(),
                    null);

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

            // Desenhar a imagem de explosão
            for (int i = 0; i < tiros.size(); i++) {
                Tiro tiro = tiros.get(i);
                if (!tiro.isVisibilidade()) {
                    // Exibir a explosão na posição do tiro
                    graficos.drawImage(explosao, tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), null);
                }
            }

            // CARREGANDO INIMIGO BOSS
            // for (int i = 0; i < inimigo_boss.size(); i++) {
            // Inimigo_boss boss = inimigo_boss.get(i);
            // boss.carregar();
            // graficos.drawImage(boss.getImagem_boss(), boss.getPosicaoEmX(),
            // boss.getPosicaoEmY(), null);
            // } // FIM BOSS

            // CARREGANDO OS METODOS DAS INFORMAÇÃO DO JOGO(VIDA E PONTUAÇÃO)
            personagem.pontos(graficos);
            vidas(graficos);
        }

        if (jogando == false) {
            int larguraTela = 1300;
            String enter = "PRESSIONE 'ENTER' PARA RESETAR.";
            FontMetrics fm = g.getFontMetrics();
            // PEGA O TAMANHO DA STRING E FAZ O CALCULO PARA CENTRALIZAR
            int enterWidth = fm.stringWidth(enter);
            int x = (larguraTela - enterWidth) / 2;
            int y = 105 + (alturaTela - fm.getHeight()) / 2;
            // IMG FUNDO
            g.drawImage(this.fundo, 0, 0, null);
            if (personagem.getPontos() > 200) {
                g.drawImage(this.fundo_2, 0, 0, null);
            }
            // GIF DA CAVEIRA
            ImageIcon caveira = new ImageIcon("recursos\\sprites_fundos\\caveira.gif");
            this.caveira_fundo = caveira.getImage();
            g.drawImage(this.caveira_fundo, this.POSICAO_CAVEIRA, 0, null);
            // PONTOS
            personagem.pontos(graficos);
            // IMAGEM 'YOU DIED'
            ImageIcon death = new ImageIcon("recursos\\sprites_fundos\\Morte.png");
            this.fundo_morte = death.getImage();
            g.drawImage(this.fundo_morte, 0, 0, null);
            g.drawString(enter, (x - 103), y);
        }
        g.dispose();
    }

    // MÉTODO PARA ATUALIZAÇÃO DAS IMAGENS
    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atuzaliza();
        repaint();
        // VERIFICANDO SE O INIMIGO ESTA VISIVEL E ATUALIZANDO A SUA POSICAÇÃO ATRAVES
        // DO METODO 'ATUALIZAR', AO FICAR INVISIVEL O INIMIGO E EXCLUIDO
        // A CLASSE ITERATOR E COMO UM PONTEIRO EM C QUE PERMITE INTERAJIR COM UM CERTO
        // OBJETO(INIMIGO) DE UMA LISTA DE FORMA ESPECIFICA E EM SEQUENCIA
        Iterator<Inimigo_naves> iterator_naves = inimigo_naves.iterator();
        while (iterator_naves.hasNext()) {
            Inimigo_naves ini = iterator_naves.next();
            if (ini.isVisibilidade()) {
                ini.atualizar();
            } else {
                iterator_naves.remove();
            }
        } // FIM ITERATOR NAVES

        // ATUALIZA A POSIÇÃO DO METORITO
        Iterator<Inimigo_meteorito> iterator_meteorito = inimigo_meteorito.iterator();
        while (iterator_meteorito.hasNext()) {
            Inimigo_meteorito mete = iterator_meteorito.next();
            if (mete.isVisibilidade()) {
                mete.atualizar();
            } else {
                iterator_meteorito.remove();
            }
        } // FIM

        // ATUALIZA POSICAÇÃO DO TIRO
        List<Tiro> tiros = personagem.getTiros();
        Iterator<Tiro> iterator_tiro = tiros.iterator();
        while (iterator_tiro.hasNext()) {
            Tiro tiro = iterator_tiro.next();
            if (tiro.isVisibilidade()) {
                tiro.atualizar();
            } else {
                iterator_tiro.remove();
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

        checarColisoes();
        repaint();
    }

    // MÉTODO DA VIDA DO JOGADOR
    public void vidas(Graphics life) {
        // DEFINE A STRING DE EXIBIÇÃO
        String vida = " ";
        // CRIA UM ESTILO DE FONTE
        Font estilo = new Font("Consolas", Font.BOLD, 10);
        // CRIA A MÉTRICA DA FONTE
        FontMetrics metrica = this.getFontMetrics(estilo);

        // OBTÉM O TAMANHO DA STRING NA TELA
        int width = metrica.stringWidth(vida);
        // CALCUPA A DISTÂNCIA DA BORDA PARA POSICIONAR A STRING
        int distancia = (15 * vidas) + (5 * vidas) + 100 + width;

        // PARA CADA VIDA DO JOGADOR, DESENHA UMA IMAGEM DA VIDA,
        // ALTERANDO A POSIÇÃO COM BASE NOS CALCULOS PARA DEFINIR
        // A POSIÇÃO DE CADA UMA EM (x,y)
        for (int i = 0; i < vidas; i++) {
            life.drawImage(personagem.getImagem_vida(), (larguraTela + 50) - width, 10, this);
            width += personagem.getAlturaImagem_Vida() + 5;
        }
        // SETA A COR DA FONTE
        life.setColor(Color.WHITE);
        // SETA O ESTILO DE FONTE
        life.setFont(estilo);
        // DESENHA A STRING COM A POSIÇÃO (x,y)
        life.drawString(vida, personagem.getLarguraImagem_Vida() - distancia, 10);
    }

    public void checarColisoes() {
        Rectangle forma_Nave_Personagem = personagem.getBounds();
        Rectangle Forma_Inimigo_Nave;
        Rectangle Forma_Inimig_Meteorito;
        Rectangle forma_Tiro;
        // COLISÃO DA NAVE DO PERSOANGEM COM A NAVE INIMIGA
        for (int i = 0; i < inimigo_naves.size(); i++) {
            Inimigo_naves temp_nave = inimigo_naves.get(i);
            Forma_Inimigo_Nave = temp_nave.getBounds();
            if (forma_Nave_Personagem.getBounds().intersects(Forma_Inimigo_Nave.getBounds())) {
                personagem.setVisibilidade(false);
                temp_nave.setVisibilidade(false);
                if (vidas == 1) {
                    jogando = false;
                } else {
                    vidas--;
                }
            }
        }
        // COLISÃO DA NAVE DO PERSOANGEM COM OS METEORITOS
        for (int k = 0; k < inimigo_meteorito.size(); k++) {
            Inimigo_meteorito temp_mete = inimigo_meteorito.get(k);
            Forma_Inimig_Meteorito = temp_mete.getBounds();
            if (forma_Nave_Personagem.getBounds().intersects(Forma_Inimig_Meteorito.getBounds())) {
                personagem.setVisibilidade(false);
                temp_mete.setVisibilidade(false);
                if (vidas == 1) {
                    jogando = false;
                } else {
                    vidas--;
                }
            }
        }
        // COLISÃO DOS TIROS COM A NAVE INIMIGA
        List<Tiro> tiros = personagem.getTiros();
        // FOR TIRO
        for (int i = 0; i < tiros.size(); i++) {
            Tiro temp_Tiro = tiros.get(i);
            forma_Tiro = temp_Tiro.getBounds();
            boolean colisao_NAVE = false; // VARIAVEL QUE VAI VERIFICAR A COLISÃO
            boolean colisao_METEORITO = false;
            // FOR NAVE INIMIGA
            for (int j = 0; j < inimigo_naves.size(); j++) {
                Inimigo_naves temp_nave = inimigo_naves.get(j);
                Forma_Inimigo_Nave = temp_nave.getBounds();
                // FOR METEORITO
                for (int b = 0; b < inimigo_meteorito.size(); b++) {
                    Inimigo_meteorito temp_mete = inimigo_meteorito.get(b);
                    Forma_Inimig_Meteorito = temp_mete.getBounds();
                    if (forma_Tiro.getBounds().intersects(Forma_Inimigo_Nave.getBounds())) {
                        temp_nave.setVisibilidade(false);
                        temp_Tiro.setVisibilidade(false);
                        colisao_NAVE = true; // COLISÃO DEU CERTO
                        break; // SAI DO LOOP INTERNO
                    }
                    if (forma_Tiro.getBounds().intersects(Forma_Inimig_Meteorito.getBounds())) {
                        temp_mete.setVisibilidade(false);
                        temp_Tiro.setVisibilidade(false);
                        repaint();
                        colisao_METEORITO = true;
                        break;
                    }
                }
                if (colisao_NAVE) {
                    break; // SAI DO LOOP EXTERNO
                }
                if (colisao_METEORITO) {
                    break;
                }
            }
            if (colisao_NAVE) {
                // AUMENTA OS PONTOS SE A COLISÃO FOI DETECTADA PARA A NAVE INIMIGA
                personagem.setPontos(personagem.getPontos() + 10);
            }
            if (colisao_METEORITO) {
                // AUMENTA OS PONTOS SE A COLISÃO FOI DETECTADA PARA O METEORITO
                personagem.setPontos(personagem.getPontos() + 20);
            }
        }
    }

    public void resetar(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (jogando == true) {
            //SO ESTA IMPEDINDO QUE O ENTER FUNCIONE ENQUANTO O JOGO ESTA FUCIONANDO
        } else {
            if (tecla == KeyEvent.VK_ENTER) {
                vidas = 3;
                jogando = true;
                personagem.setPontos(0);
            }
        }

    }

    // CHAMANDO A LEITURA DOS TECLADOS
    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            personagem.tecla_Precionada(e);
            resetar(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            personagem.tecla_solta(e);
        }

    }
}