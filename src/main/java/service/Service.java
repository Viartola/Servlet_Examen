package service;

import java.util.List;

import model.*;
import repository.*;

public class Service {
	private RepositoryCountry repositoryCountry = new RepositoryCountry();
	private RepositoryLanguage repositoryLanguage = new RepositoryLanguage();
	
	public void insertNewLanguage(String nLanguage, String country) {
		repositoryLanguage.insertNewLanguage(nLanguage);
		repositoryCountry.insertNewCountry(country, nLanguage);
	}
	
	public void insertNewCountry(String language, String country) {
		repositoryCountry.insertNewCountry(country, language);
	}
	
	public void deleteTable(String language) {
		repositoryCountry.deleteCountry(language);
		repositoryLanguage.deleteLannguage(language);
	}

	public List<Country> listAllCountries() {
		return repositoryCountry.listAllCountries();
	}
	
	public List<Language> listAllLanguages() {
		return repositoryLanguage.listAllLanguages();
	}

	public RepositoryCountry getRepository() {
		return repositoryCountry;
	}

	public void setRepository(RepositoryCountry repositoryCountry) {
		this.repositoryCountry = repositoryCountry;
	}	
}
