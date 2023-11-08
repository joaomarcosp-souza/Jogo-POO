package br.ifpr.paranavai.jogo.modelo.Test;


import org.hibernate.Session;

import br.ifpr.paranavai.jogo.modelo.Conexao.HibernateUtil;
import br.ifpr.paranavai.jogo.modelo.model.Character.Player;

public class TesteHibernate {
    public static void main(String[] args) {
        Session sessao = HibernateUtil.getSession();
        sessao.beginTransaction();
        Player local = new Player();
        sessao.save(local);
        sessao.getTransaction().commit();
        HibernateUtil.encerraSession();
    }

}
