package br.com.timetrialfactory.maestro.assembler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import br.com.timetrialfactory.maestro.dto.GameDTO;
import br.com.timetrialfactory.maestro.models.Game;

@Component
public class GameAssembler implements Assembler<GameDTO, Game> {

	@Override
	public GameDTO toObject(Game entity) {
		GameDTO object = null;
		if (entity != null) {
			object = new GameDTO();
			object.setId(entity.getId());
			object.setTitle(entity.getTitle());
			object.setPrice(entity.getPrice());
			object.setDescription(entity.getDescription());
			object.setDownloadLink(entity.getDownloadLink());
		}
		return object;
	}

	@Override
	public Game toEntity(GameDTO object) {
		Game entity = null;
		if (object != null) {
			entity = new Game();
			entity.setId(object.getId());
			entity.setTitle(object.getTitle());
			entity.setPrice(object.getPrice());
			entity.setDescription(object.getDescription());
			if (object.getDownloadLink() != null) {
				entity.setDownloadLink(object.getDownloadLink());
			}
		}
		return entity;
	}

	public Set<GameDTO> toObjectSet(List<Game> games) {
		Set<GameDTO> gameSet = null;
		if (games != null) {
			gameSet = new HashSet<GameDTO>();
			for (Game game : games) {
				gameSet.add(this.toObject(game));
			}
		}
		return gameSet;
	}

}
