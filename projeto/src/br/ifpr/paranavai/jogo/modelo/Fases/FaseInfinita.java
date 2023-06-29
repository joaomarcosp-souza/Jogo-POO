package br.ifpr.paranavai.jogo.modelo.Fases;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;

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

import br.ifpr.paranavai.jogo.modelo.Jogador.Personagem;
import br.ifpr.paranavai.jogo.modelo.Jogador.Tiro;
import br.ifpr.paranavai.jogo.modelo.Telas.TelaControles;
import br.ifpr.paranavai.jogo.modelo.Telas.TelaHistorico;
import br.ifpr.paranavai.jogo.modelo.Telas.TelaMenu;
import br.ifpr.paranavai.jogo.modelo.Telas.TelaMorte;
import br.ifpr.paranavai.jogo.modelo.inimigos.InimigoMeteorito;
import br.ifpr.paranavai.jogo.modelo.inimigos.InimigoNaves;

public class FaseInfinita extends JPanel implements ActionListener {
    private Image fundo;
    private Image fundo_fase_2;
    private List<InimigoNaves> inimigo_naves;
    private List<InimigoMeteorito> inimigo_meteorito;
    private int larguraTela = 1300; // TAMANHO DA TELA
    private boolean jogando;
    private Timer timer_inimigo_nave;
    private Timer timer_inimigo_Meteorito;
    private TelaMenu tela_menu;
    private Personagem personagem;
    private TelaHistorico tela_Historico;
    private TelaControles tela_Controles;
    private TelaMorte telaMorte;

    private int delayInimigoNave = 500;
    private int delayInimigoMeteorito = 2500;

    public FaseInfinita() {
        setFocusable(true);
        setDoubleBuffered(true);
        // IMAGEM DE FUNDO
        ImageIcon fundo_principal = new ImageIcon("recursos\\sprites_Fundos\\fundo_fase.jpg");
        this.fundo = fundo_principal.getImage();
        // IMAGEM SEGUNDO FUNDO
        ImageIcon fundo_fase_2 = new ImageIcon("recursos\\sprites_Fundos\\fundo_fase_dois.jpg");
        this.fundo_fase_2 = fundo_fase_2.getImage();
        //
        // ImageIcon explosao_img = new
        // ImageIcon("recursos\\sprites_tiros\\explosao.png");
        // INICIALIZANDO O PERSONAGEM
        personagem = new Personagem();
        personagem.carregar();
        //
        tela_menu = new TelaMenu();
        tela_menu.carregar();
        //
        tela_Historico = new TelaHistorico();
        tela_Historico.carregar();
        //
        tela_Controles = new TelaControles();
        tela_Controles.carregar();

        telaMorte = new TelaMorte();
        telaMorte.carregar();
        // INICIALIZANDO O METODO DE LEITURA DO TECLADO
        addKeyListener(new TecladoAdapter());
        // ATUALIZANDO O PERSONAGEM E REDENSENHADO NA TELA EM INTERVALOS REGULARES.
        Timer timer = new Timer(5, this);
        timer.start(); // START

        // INICIALIZANDO MÉTODOS INIMIGOS
        InicializaNaveInimiga(); // NAVES
        InicializaMeteorito(); // METEORITOS
        jogando = false;
    }

    // INICIANDO POSIÇÃO DAS NAVES INIMIGAS ALEATORIAMENTE
    public void InicializaNaveInimiga() {
        inimigo_naves = new ArrayList<InimigoNaves>();

        // INTERVALO (EM MILISSEGUNDOS) PARA CONTROLAR A TAXA DE // SPAWN DE INIMIGOS
        timer_inimigo_nave = new Timer(delayInimigoNave, new ActionListener() {
            int y = 630;
            int alturaInimigo = 80;

            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmX = larguraTela; // Defina a posição inicial do novo inimigo
                int posicaoEmY = (int) (Math.random() * (y - alturaInimigo));
                if (posicaoEmY < 10 || posicaoEmY > 650) {
                    posicaoEmY = 50;
                }
                inimigo_naves.add(new InimigoNaves(posicaoEmX, posicaoEmY));
            }
        });
        timer_inimigo_nave.setRepeats(true);
    }

    // INICIANDO POSIÇÃO DO METEORITO ALEATORIAMENTE
    public void InicializaMeteorito() {
        inimigo_meteorito = new ArrayList<InimigoMeteorito>();
        int alturaInimigo = 100;
        // TIMER PARA SPAWNAR O METEORITO
        timer_inimigo_Meteorito = new Timer(delayInimigoMeteorito, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmY = -alturaInimigo;
                int posicaoEmX = (int) (Math.random() * (larguraTela - alturaInimigo));
                if (posicaoEmX < 10 || posicaoEmX > 1200) {
                    posicaoEmX = 100;
                }
                inimigo_meteorito.add(new InimigoMeteorito(posicaoEmX, posicaoEmY));
            }
        });
        timer_inimigo_Meteorito.setRepeats(true);
    }// FIM MÉTODO METEORITO

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graficos = (Graphics2D) g;

        if (tela_menu.isVisibilidade_menu() == true) {
            // CARRREGANDO OS COMPONENTES DA CLASSE 'MENU'
            tela_menu.titulo(graficos);
            tela_menu.menu(graficos);
            jogando = false;
        } else if (tela_Controles.isControle_visibilidade() == true) {
            // CARRREGANDO OS COMPONENTES DA CLASSE 'CONTROLES(EM IMPLEMENTAÇÃO)'
            tela_Controles.titulo_controle(graficos);
            jogando = false;
        } else if (tela_Historico.isHistorico_visibilidade() == true) {
            // CARRREGANDO OS COMPONENTES DA CLASSE 'HISTORICO(EM IMPLEMENTAÇÃO)'
            tela_Historico.titulo_Historico(graficos);
            jogando = false;
        }
        if (jogando == true) {
            // FUNDO DA FASE INFINITA(PRINCIPAL)
            graficos.drawImage(this.fundo, 0, 0, null);
            // SEGUNDO FUNDO DA FASE CASO A CONDIÇÃO SEJA VERDADEIRA
            if (personagem.getPontos() >= 200) {
                graficos.drawImage(this.fundo_fase_2, 0, 0, null);
            }
            // CARREGANDO TIRO PERSONAGEM
            List<Tiro> tiros = personagem.getTiros();
            for (int i = 0; i < tiros.size(); i++) {
                Tiro tiro = tiros.get(i);
                tiro.carregar();
                graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), null);
            }
            // CARREGANDO A IMAGEM DO PERSONAGEM
            graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(),
                    null);

            // COMEÇO METEORO E NAVES INIMIGAS
            for (int i = 0; i < inimigo_meteorito.size(); i++) {
                InimigoMeteorito mete = inimigo_meteorito.get(i);
                mete.carregar();
                graficos.drawImage(mete.getImagem_meteoro(), mete.getPosicaoEmX(), mete.getPosicaoEmY(),
                        null);
                if (!mete.isVisibilidade()) {
                    graficos.setColor(Color.white);
                    graficos.setFont(personagem.getPixel().deriveFont(Font.PLAIN, 35));
                    graficos.drawString("+20", mete.getPosicaoEmX() + 3, mete.getPosicaoEmY() + 3);
                }
            }

            for (int i = 0; i < inimigo_naves.size(); i++) {
                InimigoNaves ini = inimigo_naves.get(i);
                ini.carregar();
                graficos.drawImage(ini.getImagem(), ini.getPosicaoEmX(), ini.getPosicaoEmY(), null);
            }
            // FIM METEORO E NAVES INIMIGAS

            // HITBOX
            graficos.setColor(Color.RED);
            Rectangle hitboxPersonagem = personagem.getBounds();
            graficos.drawRect(hitboxPersonagem.x, hitboxPersonagem.y,
                    hitboxPersonagem.width, hitboxPersonagem.height);
            graficos.setColor(Color.BLUE);
            for (InimigoNaves ini : inimigo_naves) {
                Rectangle hitboxInimigo = ini.getBounds();
                graficos.drawRect(hitboxInimigo.x, hitboxInimigo.y, hitboxInimigo.width,
                        hitboxInimigo.height);
            }
            for (InimigoMeteorito mete : inimigo_meteorito) {
                Rectangle hitboxInimigo = mete.getBounds();
                graficos.drawRect(hitboxInimigo.x, hitboxInimigo.y, hitboxInimigo.width,
                        hitboxInimigo.height);
            } // FIM HITBOX

            // CARREGANDO OS COMPONENTES DA CLASSE PERSONAGEM(VIDA E PONTUAÇÃO)
            personagem.pontos(graficos);
            personagem.vidas(graficos);
        }
        // CARREGANDO A TELA DE MORTE CASO O JOGADOR MORRA(OBVIO)
        if (telaMorte.isTelaMorteVisibilidade() == true) {
            telaMorte.mensagem(graficos);
            telaMorte.opcoesMenu(graficos);
            personagem.pontos(graficos);
        }
        g.dispose();
    }

    // MÉTODO PARA ATUALIZAÇÃO DAS IMAGENS
    @Override
    public void actionPerformed(ActionEvent e) {
        // VEREFICA SE O INIMIGO ESTA VISIVEL E ATUALIZA A SUA POSICAÇÃO ATRAVES
        // DO METODO 'ATUALIZAR', AO FICAR INVISIVEL O INIMIGO E EXCLUIDO
        // A CLASSE ITERATOR E COMO UM PONTEIRO EM C QUE PERMITE INTERAJIR COM UM CERTO
        // OBJETO(INIMIGO) DE UMA LISTA DE FORMA ESPECIFICA E EM SEQUENCIA
        Iterator<InimigoNaves> iterator_naves = inimigo_naves.iterator();
        while (iterator_naves.hasNext()) {
            InimigoNaves ini = iterator_naves.next();
            if (ini.isVisibilidade()) {
                ini.atualizar();
            } else {
                iterator_naves.remove();
            }
        } // FIM NAVES

        // ATUALIZA A POSIÇÃO DO METEORO
        Iterator<InimigoMeteorito> iterator_meteorito = inimigo_meteorito.iterator();
        while (iterator_meteorito.hasNext()) {
            InimigoMeteorito mete = iterator_meteorito.next();
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

        // CARREGANDO DEMAIS METODOS DA CLASSE
        checarColisoes();
        gerenciarInimigos();
        // REPINTANDO OS OBJETOS
        personagem.atualizar();
        repaint();
    }

    public void gerenciarInimigos() {
        if (personagem.getPontos() >= 350) {
            int aumentoVelocidade = 5;
            inimigo_naves.forEach(temp_nave -> temp_nave.setVELOCIDADE(aumentoVelocidade));
        }
        if (personagem.getPontos() > 600) {
            int aumentoVelocidade = 6;
            inimigo_naves.forEach(temp_nave -> temp_nave.setVELOCIDADE(aumentoVelocidade));
            inimigo_meteorito.forEach(temp_mete -> temp_mete.setVELOCIDADE(aumentoVelocidade));
        }
        if (personagem.getPontos() > 750) {
            int aumentoVelocidade = 7;
            inimigo_naves.forEach(temp_nave -> temp_nave.setVELOCIDADE(aumentoVelocidade));
        }
        if (personagem.getPontos() > 1000) {
            int aumentoVelocidade = 8;
            inimigo_naves.forEach(temp_nave -> temp_nave.setVELOCIDADE(aumentoVelocidade));
            inimigo_meteorito.forEach(temp_mete -> temp_mete.setVELOCIDADE(aumentoVelocidade));
        }

        if (jogando == false) {
            // PARA O SPAWN DE INIMIGOS
            timer_inimigo_nave.stop();
            // LIMPA A LISTA DE INMIGOS CASO O JOGADOR ESTEJA MORTO
            inimigo_naves.clear();
            inimigo_meteorito.clear();
            // RESETA A POSIÇÃO DO JOGADOR
            if (personagem.getPosicaoEmX() != personagem.getPOSICAO_INICIAL_EM_X()
                    || personagem.getPosicaoEmY() != personagem.getPOSICAO_INICIAL_EM_Y()) {
                personagem.setPosicaoEmX(personagem.getPOSICAO_INICIAL_EM_X());
                personagem.setPosicaoEmY(personagem.getPOSICAO_INICIAL_EM_Y());
            }
        } else {
            timer_inimigo_nave.start();
            if (personagem.getPontos() > 200) {
                timer_inimigo_Meteorito.start();
            }
        }
    }

    public void checarColisoes() {
        Rectangle forma_Nave_Personagem = personagem.getBounds();
        Rectangle Forma_Inimigo_Nave;
        Rectangle Forma_Inimig_Meteorito;
        int telaLargura = 1250; // Largura da tela
        int telaAltura = 665; // Altura da tela

        // VERIFICA COLISÃO COM A BORDA EM 'X'
        if (personagem.getPosicaoEmX() < 0) {
            personagem.setPosicaoEmX(0); // POSIÇÃO MÍNIMA X
        } else if (personagem.getPosicaoEmX() + personagem.getAlturaImagem() > telaLargura) {
            int maximoEmX = telaLargura - personagem.getAlturaImagem(); // CALCULA A POSIÇÃO MÁXIMA
            personagem.setPosicaoEmX(maximoEmX);
        }
        // VERIFICA COLISÃO COM A BORDA EM 'Y'
        if (personagem.getPosicaoEmY() < 0) {
            personagem.setPosicaoEmY(0); // POSIÇÃO MÍNIMA Y
        } else if (personagem.getPosicaoEmY() + personagem.getAlturaImagem() > telaAltura) {
            int maximoEmY = telaAltura - personagem.getAlturaImagem();
            personagem.setPosicaoEmY(maximoEmY);
        }
        // COLISÃO DA NAVE DO PERSOANGEM COM A NAVE INIMIGA
        for (int i = 0; i < inimigo_naves.size(); i++) {
            InimigoNaves temp_nave = inimigo_naves.get(i);
            Forma_Inimigo_Nave = temp_nave.getBounds();
            if (forma_Nave_Personagem.getBounds().intersects(Forma_Inimigo_Nave.getBounds())) {
                if (personagem.getVIDAS() == 1) {
                    jogando = false;
                    telaMorte.setTelaMorteVisibilidade(true);
                } else {
                    personagem.setVIDAS(personagem.getVIDAS() - 1);
                }
                personagem.setVisibilidade(false);
                temp_nave.setVisibilidade(false);
            }
        }
        for (int k = 0; k < inimigo_meteorito.size(); k++) {
            InimigoMeteorito temp_mete = inimigo_meteorito.get(k);
            Forma_Inimig_Meteorito = temp_mete.getBounds();
            if (forma_Nave_Personagem.getBounds().intersects(Forma_Inimig_Meteorito.getBounds())) {
                if (personagem.getVIDAS() == 1) {
                    jogando = false;
                    telaMorte.setTelaMorteVisibilidade(true);
                } else {
                    personagem.setVIDAS(personagem.getVIDAS() - 1);
                }
                temp_mete.setVisibilidade(false);
                personagem.setVisibilidade(false);
            }
        }
        // VEREFICA COLISÃO COM INIMIGOS METEORITO
        for (InimigoNaves inimigoNave : inimigo_naves) {
            Rectangle formaInimigoNave = inimigoNave.getBounds();
            for (Tiro tiro : personagem.getTiros()) {
                Rectangle formaTiro = tiro.getBounds();
                if (formaTiro.intersects(formaInimigoNave)) {
                    personagem.setPontos(personagem.getPontos() + 10);
                    if (personagem.getPontos() < 200) {
                        inimigoNave.setVisibilidade(false);
                    } else if (personagem.getPontos() > 200) {
                        if (inimigoNave.getVIDASINIMIGOS() == 1) {
                            inimigoNave.setVisibilidade(false);
                        } else {
                            inimigoNave.setVIDASINIMIGOS(inimigoNave.getVIDASINIMIGOS() - 1);
                        }
                    }
                    tiro.setVisibilidade(false);
                }
            }
        }

        // VEREFICA COLISÃO COM INIMIGOS METEORITO
        for (InimigoMeteorito inimigoMeteorito : inimigo_meteorito) {
            Rectangle formaInimigoMeteorito = inimigoMeteorito.getBounds();
            for (Tiro tiro : personagem.getTiros()) {
                Rectangle formaTiro = tiro.getBounds();
                if (formaTiro.intersects(formaInimigoMeteorito)) {
                    inimigoMeteorito.setVisibilidade(false);
                    tiro.setVisibilidade(false);
                    personagem.setPontos(personagem.getPontos() + 20);
                }
            }
        }

    }

    // OPC DOS MENU INICIAL
    public void MenuInicial(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_ENTER) {
            if (tela_menu.getCursor() == 0) { // TELA JOGO FASE - IMPLEMENTAR
                System.out.println("Tela Fase - Implementando");
            } else if (tela_menu.getCursor() == 1) { // TELA JOGO
                jogando = true;
                tela_menu.setVisibilidade_menu(false);
            } else if (tela_menu.getCursor() == 2) { // TELA CONTROLES
                tela_menu.setVisibilidade_menu(false);
                tela_Controles.setControle_visibilidade(true);
            } else if (tela_menu.getCursor() == 3) { // TELA HISTORICO - IMPLEMENTAR
                tela_menu.setVisibilidade_menu(false);
                tela_Historico.setHistorico_visibilidade(true);
            }
        }
    }

    public void VisibilidadeTelas(KeyEvent e) {
        int tecla = e.getKeyCode();

        if (tela_Controles.isControle_visibilidade() == true) {
            if (tecla == KeyEvent.VK_ESCAPE) {
                tela_Controles.setControle_visibilidade(false);
                tela_menu.setVisibilidade_menu(true);
            }
        }
        if (tela_Historico.isHistorico_visibilidade() == true) {
            if (tecla == KeyEvent.VK_ESCAPE) {
                tela_Historico.setHistorico_visibilidade(false);
                tela_menu.setVisibilidade_menu(true);
            }
        }
    }

    public void MenuTelaMorte(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (telaMorte.isTelaMorteVisibilidade() == true) {
            if (tecla == KeyEvent.VK_ENTER) {
                if (telaMorte.getCursor() == 0) {
                    jogando = true;
                    personagem.setPontos(0);
                    personagem.setVIDAS(3);
                    telaMorte.setTelaMorteVisibilidade(false);
                }
                if (telaMorte.getCursor() == 1) {
                    personagem.setPontos(0);
                    personagem.setVIDAS(3);
                    telaMorte.setTelaMorteVisibilidade(false);
                    tela_menu.setVisibilidade_menu(true);
                }
            }

        }
    }

    // CHAMANDO A LEITURA DOS TECLADOS
    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (tela_menu.isVisibilidade_menu() == true) {
                tela_menu.tecla_menu(e);
                MenuInicial(e);
            }
            if (tela_Controles.isControle_visibilidade() == true) {
                VisibilidadeTelas(e);
            }
            if (tela_Historico.isHistorico_visibilidade() == true) {
                VisibilidadeTelas(e);
            }
            if (jogando == true) {
                personagem.mover(e);
                personagem.atirar(e);
            }
            if (telaMorte.isTelaMorteVisibilidade() == true) {
                MenuTelaMorte(e);
                telaMorte.menuMorto(e);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            personagem.parar(e);
        }
    }
}