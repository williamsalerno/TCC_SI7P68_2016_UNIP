package br.com.timetrialfactory.maestro.daos.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.timetrialfactory.maestro.assembler.LicenseAssembler;
import br.com.timetrialfactory.maestro.daos.AbstractDAO;
import br.com.timetrialfactory.maestro.daos.LicenseDAO;
import br.com.timetrialfactory.maestro.models.License;

@Repository("licenseDao")
public class LicenseDAOImpl extends AbstractDAO<Long, License> implements LicenseDAO {

	@Autowired
	LicenseAssembler assembler;

	@Override
	public void saveLicense(License license) {
		persist(license);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<License> listLicenses(Long userId) {
		return getSession().createQuery("FROM License l WHERE l.user.id = :userId").setParameter("userId", userId).list();
	}
}
