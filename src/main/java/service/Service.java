package service;

import java.util.List;

import model.*;
import repository.*;

public class Service {
	private RepositoryCountries repositoryCountries = new RepositoryCountries();
	private RepositoryLanguage repositoryLanguage = new RepositoryLanguage();
	
	public void insertNewLanguage(String nLanguage, String country) {
		repositoryLanguage.insertNewLanguage(nLanguage);
		repositoryCountries.insertNewCountry(country, nLanguage);
	}
	
	public void insertNewCountry(String language, String country) {
		repositoryCountries.insertNewCountry(country, language);
	}
	
	public void deleteTable(String language) {
		repositoryCountries.deleteCountry(language);
		repositoryLanguage.deleteLannguage(language);
	}

	public List<Country> listAllCountries() {
		return repositoryCountries.listAllCountries();
	}
	
	public List<Language> listAllLanguages() {
		return repositoryLanguage.listAllLanguages();
	}

	public RepositoryCountries getRepository() {
		return repositoryCountries;
	}

	public void setRepository(RepositoryCountries repositoryCountries) {
		this.repositoryCountries = repositoryCountries;
	}	
}
