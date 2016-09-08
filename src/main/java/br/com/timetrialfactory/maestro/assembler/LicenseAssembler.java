package br.com.timetrialfactory.maestro.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.timetrialfactory.maestro.dto.LicenseDTO;
import br.com.timetrialfactory.maestro.models.License;
import br.com.timetrialfactory.maestro.services.GameService;
import br.com.timetrialfactory.maestro.services.UserService;

@Component
public class LicenseAssembler implements Assembler<List<License>, List<LicenseDTO>> {

	@Autowired
	UserAssembler userAssembler;

	@Autowired
	GameAssembler gameAssembler;

	@Autowired
	UserService userService;

	@Autowired
	GameService gameService;

	@Override
	public List<LicenseDTO> toObject(List<License> list) {
		List<LicenseDTO> object = null;
		if (list != null) {
			object = new ArrayList<LicenseDTO>();
			for (License license : list) {
				LicenseDTO dto = new LicenseDTO();
				if (license.getGame() != null) {
					dto.setGameId(license.getGame().getId());
				}
				if (license.getUser() != null) {
					dto.setUserId(license.getUser().getId());
				}
				if (license.getCode() != null) {
					dto.setCode(license.getCode());
				}
				if (license.getCheckedCode() != null) {
					dto.setCheckedCode(license.getCheckedCode());
				}
				object.add(dto);
			}
		}
		return object;
	}

	@Override
	public List<License> toEntity(List<LicenseDTO> list) {
		List<License> entity = null;
		if (list != null) {
			entity = new ArrayList<License>();
			for (LicenseDTO dto : list) {
				License license = new License();
				license.setGame(gameAssembler.toObject(gameService.findGameById(dto.getGameId())));
				license.setUser(userAssembler.toObject(userService.findById(dto.getUserId())));
				license.setCode(dto.getCode());
				license.setCheckedCode(dto.isCheckedCode());
				entity.add(license);
			}
		}
		return entity;
	}

}
