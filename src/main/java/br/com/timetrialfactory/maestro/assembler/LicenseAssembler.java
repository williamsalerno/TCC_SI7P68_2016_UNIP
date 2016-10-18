package br.com.timetrialfactory.maestro.assembler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import br.com.timetrialfactory.maestro.dto.LicenseDTO;
import br.com.timetrialfactory.maestro.models.License;

@Component
public class LicenseAssembler{

	public Set<LicenseDTO> toObject(List<License> list) {
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

}
