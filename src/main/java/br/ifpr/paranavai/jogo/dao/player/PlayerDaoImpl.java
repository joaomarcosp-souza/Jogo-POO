package br.ifpr.paranavai.jogo.dao.player;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import br.ifpr.paranavai.jogo.Conexao.HibernateUtil;
import br.ifpr.paranavai.jogo.model.Character.Player;

public class PlayerDaoImpl implements PlayerDao {

    private Session session;

    public PlayerDaoImpl() {
        this.session = HibernateUtil.getSession();
    }

    @Override
    public List<Player> searchAll() {
        Query<Player> query = this.session.createQuery("from Player",
                Player.class);
        List<Player> players = query.getResultList();
        return players;
    }

    @Override
    public Player searchForId(Integer id) {
        return this.session.find(Player.class, id);
    }

    @Override
    public void modify(Player player) {
        try {
            session.beginTransaction();
            session.merge(player);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Player player) {
        try {
            session.beginTransaction();
            session.remove(player);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Player player) {
        try {
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
