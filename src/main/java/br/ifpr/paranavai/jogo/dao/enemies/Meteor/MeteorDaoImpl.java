package br.ifpr.paranavai.jogo.dao.enemies.Meteor;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import br.ifpr.paranavai.jogo.Conexao.HibernateUtil;
import br.ifpr.paranavai.jogo.model.Enemies.Meteorito;

public class MeteorDaoImpl implements MeteorDao{
    
    private Session session;

    public MeteorDaoImpl() {
        this.session = HibernateUtil.getSession();
    }

    @Override
    public List<Meteorito> searchAll() {
        Query<Meteorito> query = this.session.createQuery("from Ships",
                Meteorito.class);
        List<Meteorito> meteoritos = query.getResultList();
        return meteoritos;
    }

    @Override
    public Meteorito searchForId(Integer id) {
        return this.session.find(Meteorito.class, id);
    }

    @Override
    public void modify(Meteorito meteorito) {
        try {
            session.beginTransaction();
            session.merge(meteorito);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Meteorito meteorito) {
        try {
            session.beginTransaction();
            session.remove(meteorito);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Meteorito meteorito) {
        try {
            session.beginTransaction();
            session.persist(meteorito);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
