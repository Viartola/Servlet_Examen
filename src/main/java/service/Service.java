package service;

import java.util.List;

import model.*;
import repository.*;

public class Service {
	private Repository repository = new Repository();
	
	public void insertarNuevoIdioma(String nLanguage, String country) {
		repository.insertarTablaIdiomas(nLanguage);
		repository.insertarTablaPaises(country, nLanguage);
	}
	
	public void insertarPais(String language, String country) {
		repository.insertarTablaPaises(country, language);
	}
	
	public void crearTablas() {
		repository.crearTablaPaises();
		repository.crearTablaIdiomas();
	}
	
	public void BorrarIdioma(String language) {
		repository.BorrarTabla(language);
	}

	public List<Paises> listAllCountries() {
		return repository.listarPaises();
	}
	
	public List<Idiomas> listAllLanguages() {
		return repository.listarIdiomas();
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}
	
}
