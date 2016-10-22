package br.com.timetrialfactory.maestro.assembler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.timetrialfactory.maestro.dto.LicenseDTO;
import br.com.timetrialfactory.maestro.models.License;

@Component
public class LicenseAssembler implements Assembler<LicenseDTO, License> {

	@Autowired
	private GameAssembler gameAssembler;

	@Autowired
	private UserAssembler userAssembler;

	@Override
	public LicenseDTO toObject(License entity) {
		LicenseDTO object = null;
		if (entity != null) {
			object = new LicenseDTO();
			object.setCode(entity.getCode());
			object.setGame(gameAssembler.toObject(entity.getGame()));
			object.setUser(userAssembler.toObject(entity.getUser()));
			object.setCheckedCode(entity.getCheckedCode());
		}
		return object;
	}

	@Override
	public License toEntity(LicenseDTO object) {
		License entity = null;
		if (object != null) {
			entity = new License();
			entity.setCode(object.getCode());
			entity.setGame(gameAssembler.toEntity(object.getGame()));
			entity.setUser(userAssembler.toEntity(object.getUser()));
			entity.setCheckedCode(object.getCheckedCode());
		}
		return entity;
	}

	public Set<LicenseDTO> toObjectList(List<License> list) {
		Set<LicenseDTO> object = null;
		if (list != null) {
			object = new HashSet<LicenseDTO>();
			for (License license : list) {
				LicenseDTO dto = new LicenseDTO();
				if (license.getGame() != null) {
					dto.setGame(gameAssembler.toObject(license.getGame()));
				}
				if (license.getUser() != null) {
					dto.setUser(userAssembler.toObject(license.getUser()));
				}
				dto.setCode(license.getCode());
				dto.setCheckedCode(license.getCheckedCode());
				object.add(dto);
			}
		}
		return object;
	}

}
