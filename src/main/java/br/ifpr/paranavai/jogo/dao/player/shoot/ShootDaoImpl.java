package br.ifpr.paranavai.jogo.dao.player.shoot;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import br.ifpr.paranavai.jogo.conexao.HibernateUtil;
import br.ifpr.paranavai.jogo.model.Character.Bullets.Shoot;

public class ShootDaoImpl implements ShootDao{
    
    private Session session;

    public ShootDaoImpl() {
        this.session = HibernateUtil.getSession();
    }

    @Override
    public List<Shoot> searchAll() {
        Query<Shoot> query = this.session.createQuery("from Shot",
                Shoot.class);
        List<Shoot> players = query.getResultList();
        return players;
    }

    @Override
    public Shoot searchForId(Integer id) {
        return this.session.find(Shoot.class, id);
    }

    @Override
    public void modify(Shoot shoot) {
        try {
            session.beginTransaction();
            session.merge(shoot);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Shoot shoot) {
        try {
            session.beginTransaction();
            session.remove(shoot);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Shoot shoot) {
        try {
            session.beginTransaction();
            session.persist(shoot);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
