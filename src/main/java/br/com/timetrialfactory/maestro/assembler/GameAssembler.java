package br.com.timetrialfactory.maestro.assembler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import br.com.timetrialfactory.maestro.dto.GameDTO;
import br.com.timetrialfactory.maestro.models.Game;

@Component
public class GameAssembler implements Assembler<Game, GameDTO> {

	@Override
	public GameDTO toObject(Game entity) {
		GameDTO object = null;
		if (entity != null) {
			object = new GameDTO();
			object.setId(entity.getId());
			object.setPrice(entity.getPrice());
			object.setTitle(entity.getTitle());
			object.setDescription(entity.getDescription());
		}
		return object;
	}

	@Override
	public Game toEntity(GameDTO object) {
		Game entity = null;
		if (object != null) {
			entity = new Game();
			entity.setId(object.getId());
			entity.setPrice(object.getPrice());
			entity.setTitle(object.getTitle());
			entity.setDescription(object.getDescription());
		}
		return entity;
	}

	public static Set<Game> toSet(List<Game> listGames) {
		Set<Game> gameSet = new HashSet<>();
		if (listGames != null) {
			for (Game game : listGames) {
				gameSet.add(game);
			}
			return gameSet;
		}
		return gameSet;
	}

}
