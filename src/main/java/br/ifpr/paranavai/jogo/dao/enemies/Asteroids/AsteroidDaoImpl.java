package br.ifpr.paranavai.jogo.dao.enemies.Asteroids;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import br.ifpr.paranavai.jogo.Conexao.HibernateUtil;
import br.ifpr.paranavai.jogo.model.Enemies.Asteroide;

public class AsteroidDaoImpl implements AsteroidDao {

    private Session session;

    public AsteroidDaoImpl() {
        this.session = HibernateUtil.getSession();
    }

    @Override
    public List<Asteroide> searchAll() {
        Query<Asteroide> query = this.session.createQuery("from Asteroids",
                Asteroide.class);
        List<Asteroide> meteoritos = query.getResultList();
        return meteoritos;
    }

    @Override
    public Asteroide searchForId(Integer id) {
        return this.session.find(Asteroide.class, id);
    }

    @Override
    public void modify(Asteroide asteroide) {
        try {
            session.beginTransaction();
            session.merge(asteroide);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Asteroide asteroide) {
        try {
            session.beginTransaction();
            session.remove(asteroide);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Asteroide asteroide) {
        try {
            session.beginTransaction();
            session.persist(asteroide);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
