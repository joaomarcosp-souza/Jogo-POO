package br.ifpr.paranavai.jogo.dao.player.specialShoot;

import java.util.List;

import br.ifpr.paranavai.jogo.model.Character.Bullets.SpecialShoot;

public interface SpecialShootDao {
    public List<SpecialShoot> searchAll();
    public SpecialShoot searchForId(Integer id);
    public void modify(SpecialShoot specialShoot);
    public void delete(SpecialShoot specialShoot);
    public void insert(SpecialShoot specialShoot);
}
