package br.com.timetrialfactory.maestro.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.timetrialfactory.maestro.assembler.GameAssembler;
import br.com.timetrialfactory.maestro.daos.GameDAO;
import br.com.timetrialfactory.maestro.models.Game;

@Service("gameService")
@Transactional
public class GameServiceImpl implements GameService {

	@Autowired
	private GameDAO dao;
	
	@Autowired
	private GameAssembler assembler;

	@Override
	public Set<Game> listGames() {
		return assembler.toSet(dao.listGames());
	}

	@Override
	public void saveGame(Game game) {
		if (game != null) {
			dao.saveGame(game);
		}
	}

	@Override
	public void updateGame(Game game){
		if (game != null) {
			dao.updateGame(game);
		}
	}

	@Override
	public void deleteGame(Game game) {
		if (game != null) {
			dao.deleteGame(game);
		}
	}

	@Override
	public void insertGameInCart(Game game) {
		dao.findById(game.getId());
	}

	@Override
	public Game findGameById(Long id) {
		return dao.findById(id);
	}

}
