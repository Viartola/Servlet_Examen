package service;

import java.util.List;

import model.*;
import repository.*;

public class Service {
	private Repository repository = new Repository();
	
	public void insertNewLanguage(String nLanguage, String country) {
		repository.insertarTablaIdiomas(nLanguage);
		repository.insertarTablaPaises(country, nLanguage);
	}
	
	public void insertNewCountry(String language, String country) {
		repository.insertarTablaPaises(country, language);
	}
	
	public void createTables() {
		repository.crearTablaPaises();
		repository.crearTablaIdiomas();
	}
	
	public void deleteTable(String language) {
		repository.BorrarTabla(language);
	}

	public List<Country> listAllCountries() {
		return repository.listarPaises();
	}
	
	public List<Language> listAllLanguages() {
		return repository.listarIdiomas();
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}
	
}
