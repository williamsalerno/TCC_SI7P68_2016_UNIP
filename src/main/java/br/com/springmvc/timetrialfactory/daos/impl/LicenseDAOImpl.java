package br.com.springmvc.timetrialfactory.daos.impl;

import org.springframework.stereotype.Repository;

import br.com.springmvc.timetrialfactory.daos.AbstractDAO;
import br.com.springmvc.timetrialfactory.daos.LicenseDAO;
import br.com.springmvc.timetrialfactory.models.License;

@Repository("licenseDao")
public class LicenseDAOImpl extends AbstractDAO<Long, License> implements LicenseDAO {

	@Override
	public void saveLicense(License license) {
		persist(license);
	}

	@Override
	public License findById(Long id) {
		return getByKey(id);
	}

}
