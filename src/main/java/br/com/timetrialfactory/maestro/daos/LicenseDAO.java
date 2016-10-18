package br.com.timetrialfactory.maestro.daos;

import java.util.List;

import br.com.timetrialfactory.maestro.models.License;

public interface LicenseDAO {

	void saveLicense(License license);

	List<License> listLicenses(Long userId);
}
