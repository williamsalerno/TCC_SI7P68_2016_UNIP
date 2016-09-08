package br.com.timetrialfactory.maestro.services;

import static java.util.UUID.randomUUID;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.timetrialfactory.maestro.daos.LicenseDAO;
import br.com.timetrialfactory.maestro.models.License;
import br.com.timetrialfactory.maestro.models.LoggedUser;
import br.com.timetrialfactory.maestro.models.ShoppingItem;

@Service("licenseService")
@Transactional
public class LicenseServiceImpl implements LicenseService {

	@Autowired
	private LicenseDAO dao;

	@Override
	public void saveLicense(LoggedUser user, List<ShoppingItem> games) {
		if (user != null && games != null) {
			for (ShoppingItem gameInCart : games) {
				License license = new License();
				license.setCode(new LicenseGenerator().generateLicense());
				license.setUser(user.getLoggedUser());
				license.setGame(gameInCart.getGame());
				license.setCheckedCode(false);
				dao.saveLicense(license);
			}
		}
	}

	@Override
	public List<License> listUserLicenses(Long userId) {
		return dao.listLicenses(userId);
	}

	private final class LicenseGenerator {

		private LicenseGenerator() {
		}

		private String generateLicense() {
			return randomUUID().toString();
		}
	}

}
