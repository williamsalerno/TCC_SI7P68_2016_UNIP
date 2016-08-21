package br.com.springmvc.timetrialfactory.assembler;

import org.springframework.stereotype.Component;

import br.com.springmvc.timetrialfactory.dto.GameDTO;
import br.com.springmvc.timetrialfactory.models.Game;

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

}
