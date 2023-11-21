package br.ifpr.paranavai.jogo.dao.player.specialShoot;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import br.ifpr.paranavai.jogo.conexao.HibernateUtil;
import br.ifpr.paranavai.jogo.model.Character.Bullets.SpecialShoot;

public class SpecialShootImpl implements SpecialShootDao{
    
    private Session session;

    public SpecialShootImpl() {
        this.session = HibernateUtil.getSession();
    }

    @Override
    public List<SpecialShoot> searchAll() {
        Query<SpecialShoot> query = this.session.createQuery("from SpecialShoot",
                SpecialShoot.class);
        List<SpecialShoot> players = query.getResultList();
        return players;
    }

    @Override
    public SpecialShoot searchForId(Integer id) {
        return this.session.find(SpecialShoot.class, id);
    }

    @Override
    public void modify(SpecialShoot specialShoot) {
        try {
            session.beginTransaction();
            session.merge(specialShoot);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(SpecialShoot specialShoot) {
        try {
            session.beginTransaction();
            session.remove(specialShoot);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(SpecialShoot specialShoot) {
        try {
            session.beginTransaction();
            session.persist(specialShoot);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
