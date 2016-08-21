package br.com.springmvc.timetrialfactory.daos;

import java.util.List;

import br.com.springmvc.timetrialfactory.models.License;

public interface LicenseDAO {

	void saveLicense(License license);

	List<License> listLicenses();
}
