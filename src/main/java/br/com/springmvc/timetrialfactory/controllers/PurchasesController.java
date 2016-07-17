package br.com.springmvc.timetrialfactory.controllers;

import static br.com.springmvc.timetrialfactory.controllers.LicenseGenerator.generateLicense;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Transactional
public class PurchasesController {

	public String teste() {
		return generateLicense();
	}

}

class LicenseGenerator {

	private LicenseGenerator() {
	}

	static String generateLicense() {
		return UUID.randomUUID().toString();
	}
}
