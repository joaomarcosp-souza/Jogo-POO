package br.ifpr.paranavai.jogo.services.player.Bullets;

import java.util.List;

import br.ifpr.paranavai.jogo.dao.player.shoot.ShootDao;
import br.ifpr.paranavai.jogo.dao.player.shoot.ShootDaoImpl;
import br.ifpr.paranavai.jogo.model.Character.Bullets.Shoot;

public class ShootService {

    private static ShootDao dao = new ShootDaoImpl();

    public static List<Shoot> searchAll() {
        return dao.searchAll();
    }

    public static Shoot searchForId(Integer id) {
        return dao.searchForId(id);
    }

    public static void insert(Shoot shoot) {
        dao.insert(shoot);
    }

    public static void modify(Shoot shoot) {
        dao.modify(shoot);
    }

    public static void delete(Shoot shoot) {
        dao.delete(shoot);
    }
}
