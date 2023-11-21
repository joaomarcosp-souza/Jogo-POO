package br.ifpr.paranavai.jogo.services.player.Bullets;

import java.util.List;

import br.ifpr.paranavai.jogo.dao.player.specialShoot.SpecialShootDao;
import br.ifpr.paranavai.jogo.dao.player.specialShoot.SpecialShootImpl;
import br.ifpr.paranavai.jogo.model.Character.Bullets.SpecialShoot;

public class SpecialService {

    private static SpecialShootDao dao = new SpecialShootImpl();

    public static List<SpecialShoot> searchAll() {
        return dao.searchAll();
    }

    public static SpecialShoot searchForId(Integer id) {
        return dao.searchForId(id);
    }

    public static void insert(SpecialShoot special) {
        dao.insert(special);
    }

    public static void modify(SpecialShoot special) {
        dao.modify(special);
    }

    public static void delete(SpecialShoot special) {
        dao.delete(special);
    }
}
