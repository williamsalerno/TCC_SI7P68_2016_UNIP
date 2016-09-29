package br.com.timetrialfactory.maestro.daos.impl;

import static br.com.timetrialfactory.maestro.assembler.LicenseAssembler.toSet;

import java.util.Set;

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
	public Set<License> listLicenses(Long userId) {
		return toSet(getSession().createQuery("FROM License l WHERE l.user.id = :userId").setParameter("userId", userId)
				.list());
	}
}
