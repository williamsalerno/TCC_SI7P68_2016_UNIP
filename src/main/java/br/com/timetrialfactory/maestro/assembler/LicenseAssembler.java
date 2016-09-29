package br.com.timetrialfactory.maestro.assembler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.timetrialfactory.maestro.dto.LicenseDTO;
import br.com.timetrialfactory.maestro.models.License;
import br.com.timetrialfactory.maestro.services.GameService;
import br.com.timetrialfactory.maestro.services.UserService;

@Component
public class LicenseAssembler implements Assembler<Set<License>, Set<LicenseDTO>> {

	@Autowired
	private UserAssembler userAssembler;

	@Autowired
	private GameAssembler gameAssembler;

	@Autowired
	private UserService userService;

	@Autowired
	private GameService gameService;

	@Override
	public Set<LicenseDTO> toObject(Set<License> list) {
		Set<LicenseDTO> object = null;
		if (list != null) {
			object = new HashSet<LicenseDTO>();
			for (License license : list) {
				LicenseDTO dto = new LicenseDTO();
				if (license.getGame() != null) {
					dto.setGameId(license.getGame().getId());
				}
				if (license.getUser() != null) {
					dto.setUserId(license.getUser().getId());
				}
				dto.setCode(license.getCode());
				dto.setCheckedCode(license.getCheckedCode());
				object.add(dto);
			}
		}
		return object;
	}

	@Override
	public Set<License> toEntity(Set<LicenseDTO> list) {
		Set<License> entity = null;
		if (list != null) {
			entity = new HashSet<License>();
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

	public static Set<License> toSet(List<License> listLicenses) {
		Set<License> licenseSet = new HashSet<License>();
		if (listLicenses != null) {
			for (License license : listLicenses) {
				licenseSet.add(license);
			}
			return licenseSet;
		}
		return licenseSet;
	}

}
