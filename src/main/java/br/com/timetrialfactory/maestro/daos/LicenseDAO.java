package br.com.timetrialfactory.maestro.daos;

import java.util.Set;

import br.com.timetrialfactory.maestro.models.License;

public interface LicenseDAO {

	void saveLicense(License license);

	Set<License> listLicenses(Long userId);
}
