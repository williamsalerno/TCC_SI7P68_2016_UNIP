package br.com.springmvc.timetrialfactory.daos;

import br.com.springmvc.timetrialfactory.models.License;

public interface LicenseDAO {

	void saveLicense(License license);

	License findById(Long id);
}
