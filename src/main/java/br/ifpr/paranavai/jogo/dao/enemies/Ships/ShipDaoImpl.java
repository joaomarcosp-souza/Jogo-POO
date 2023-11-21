package br.ifpr.paranavai.jogo.dao.enemies.Ships;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

import br.ifpr.paranavai.jogo.Conexao.HibernateUtil;
import br.ifpr.paranavai.jogo.model.Enemies.Naves;

public class ShipDaoImpl implements ShipDao{

    private Session session;

    public ShipDaoImpl() {
        this.session = HibernateUtil.getSession();
    }

    @Override
    public List<Naves> searchAll() {
        Query<Naves> query = this.session.createQuery("from Naves",
                Naves.class);
        List<Naves> naves = query.getResultList();
        return naves;
    }


    @Override
    public Naves searchForId(Integer id) {
        return this.session.find(Naves.class, id);
    }

    @Override
    public void modify(Naves naves) {
        try {
            session.beginTransaction();
            session.merge(naves);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Naves naves) {
        try {
            session.beginTransaction();
            session.remove(naves);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Naves naves) {
        try {
            session.beginTransaction();
            session.persist(naves);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
