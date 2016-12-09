package service;

import java.util.List;

import model.*;
import repository.*;

public class Service {
	private RepositoryCountries repositoryCountries = new RepositoryCountries();
	private RepositoryLanguages repositoryLanguages = new RepositoryLanguages();
	
	public void insertNewLanguage(String nLanguage, String country) {
		repositoryLanguages.insertNewLanguage(nLanguage);
		repositoryCountries.insertNewCountry(country, nLanguage);
	}
	
	public void insertNewCountry(String language, String country) {
		repositoryCountries.insertNewCountry(country, language);
	}
	
	public void deleteTable(String language) {
		repositoryCountries.deleteCountry(language);
		repositoryLanguages.deleteLannguage(language);
	}

	public List<Country> listAllCountries() {
		return repositoryCountries.listAllCountries();
	}
	
	public List<Language> listAllLanguages() {
		return repositoryLanguages.listAllLanguages();
	}

	public RepositoryCountries getRepository() {
		return repositoryCountries;
	}

	public void setRepository(RepositoryCountries repositoryCountries) {
		this.repositoryCountries = repositoryCountries;
	}	
}
