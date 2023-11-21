package br.ifpr.paranavai.jogo.dao.player.shoot;

import java.util.List;

import br.ifpr.paranavai.jogo.model.Character.Bullets.Shoot;

public interface ShootDao {
    public List<Shoot> searchAll();
    public Shoot searchForId(Integer id);
    public void modify(Shoot shoot);
    public void delete(Shoot shoot);
    public void insert(Shoot shoot);
}
