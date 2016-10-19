package br.com.timetrialfactory.maestro.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.timetrialfactory.maestro.assembler.GameAssembler;
import br.com.timetrialfactory.maestro.daos.GameDAO;
import br.com.timetrialfactory.maestro.dto.GameDTO;

@Service("gameService")
@Transactional
public class GameServiceImpl implements GameService {

	@Autowired
	private GameDAO dao;

	@Autowired
	private GameAssembler assembler;

	private Set<GameDTO> gamesList;

	@Override
	public Set<GameDTO> listGames() {
		Set<GameDTO> gameSet = new HashSet<GameDTO>();
		if (gamesList == null) {
			gamesList = assembler.toObjectSet(dao.listGames());
		}
		if (gamesList != null) {
			for (GameDTO game : gamesList) {
				gameSet.add(game);
			}
			return gameSet;
		}
		return gameSet;
	}

	@Override
	public void saveGame(GameDTO game) {
		if (game != null) {
			dao.saveGame(assembler.toEntity(game));
		}
	}

	@Override
	public void updateGame(GameDTO game) {
		if (game != null) {
			dao.updateGame(assembler.toEntity(game));
		}
	}

	@Override
	public void deleteGame(GameDTO game) {
		if (game != null) {
			dao.deleteGame(assembler.toEntity(game));
		}
	}

	@Override
	public void insertGameInCart(GameDTO game) {
		dao.findById(game.getId());
	}

	@Override
	public GameDTO findGameById(Long id) {
		Set<GameDTO> gamesToVerify = this.listGames();
		GameDTO gameFound = null;
		if (gamesToVerify != null) {
			for (GameDTO game : gamesToVerify) {
				if (game.getId() == id) {
					gameFound = game;
				}
			}
		}
		return gameFound;
	}

}
