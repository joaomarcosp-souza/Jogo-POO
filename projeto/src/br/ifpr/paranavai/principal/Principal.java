package br.ifpr.paranavai.principal;

import javax.swing.JFrame;

//import br.ifpr.paranavai.jogo.modelo.Fase;
import br.ifpr.paranavai.jogo.modelo.Telas.Tela_Login;

public class Principal extends JFrame {

    public Principal() {
    
        //Fase fase = new Fase();
        //super.add(fase);

        Tela_Login login = new Tela_Login();
        super.add(login);

        super.setTitle("Star Wars: Invader");
        super.setSize(1300, 700);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setVisible(true);
    }

    public static void main(String[] args) {
       new Principal();
    }
    
}