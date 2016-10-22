package br.com.timetrialfactory.maestro.services;

import java.util.List;
import java.util.Set;

import br.com.timetrialfactory.maestro.dto.LicenseDTO;
import br.com.timetrialfactory.maestro.models.LoggedUser;
import br.com.timetrialfactory.maestro.models.ShoppingItem;

public interface LicenseService {

	void saveLicense(LoggedUser user, List<ShoppingItem> games);

	Set<LicenseDTO> listUserLicenses(Long userIs);
}
