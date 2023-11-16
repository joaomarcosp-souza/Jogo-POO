package br.ifpr.paranavai.jogo.view;

import javax.swing.JFrame;
import org.hibernate.Session;
import br.ifpr.paranavai.jogo.Conexao.HibernateUtil;
import br.ifpr.paranavai.jogo.model.Stage;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Main extends JFrame {

    public Main() {
        Stage fase = new Stage();
        super.add(fase);
        super.setTitle("Star Wars: Invader");
        super.setVisible(true);
        // PEGA O TAMANHO DO MONITOR PRINCIPAL
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        // CRIA O TAMANHO DA JANELA JFRAME COM BASE NO TAMANHO DO MONITOR
        super.setSize(screenWidth, screenHeight);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
    }
    
    public static void main(String[] args) {
        Session sessao = HibernateUtil.getSession();
        sessao.beginTransaction();
        new Main();
    }
}