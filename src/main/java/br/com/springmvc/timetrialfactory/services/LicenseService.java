package br.com.springmvc.timetrialfactory.services;

import java.util.List;

import br.com.springmvc.timetrialfactory.models.License;
import br.com.springmvc.timetrialfactory.models.LoggedUser;
import br.com.springmvc.timetrialfactory.models.ShoppingItem;

public interface LicenseService {

	void saveLicense(LoggedUser user, List<ShoppingItem> games);

	License findByUserId(Long id);

	License findByGameId(Long id);

}
