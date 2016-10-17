package br.com.timetrialfactory.maestro.assembler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import br.com.timetrialfactory.maestro.models.Game;

@Component
public class GameAssembler {

	public Set<Game> toSet(List<Game> listGames) {
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
