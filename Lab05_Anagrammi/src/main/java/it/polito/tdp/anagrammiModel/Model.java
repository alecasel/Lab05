package it.polito.tdp.anagrammiModel;

import java.util.Set;

import it.polito.tdp.anagrammiDAO.AnagrammaDAO;

public class Model {
	
	private AnagrammaDAO anagrammaDAO;
	
	public Model() {
		anagrammaDAO = new AnagrammaDAO();
	}
	
	public Set<String> calcolaAnagrammi(String word) {
		return anagrammaDAO.calcolaAnagrammi(word);
	}
	
	public boolean isCorrect(String anagramma) {
		return anagrammaDAO.isCorrect(anagramma);
	}
}
