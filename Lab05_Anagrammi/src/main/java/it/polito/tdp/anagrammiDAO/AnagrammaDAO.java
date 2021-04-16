package it.polito.tdp.anagrammiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AnagrammaDAO {
	
	public boolean isCorrect(String anagramma) {
		
		String sql = "SELECT nome FROM parola WHERE parola.nome=?";
		boolean correct;
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, anagramma);
			
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				correct = true;
			}
			else {
				correct = false;
			}
			
			conn.close();
			return correct;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	/**
	 * Si fa uso del metodo ricorsivo calcola()
	 * Da una parola inserita, si ottengono tutti gli anagrammi
	 * @param word - parola di cui trovare gli anagrammi
	 * @return - Set contenente gli anagrammi
	 */
	public Set<String> calcolaAnagrammi(String word) {
		
		Set<String> anagrammi = new HashSet<>();
		String parziale = "";
		
		calcola(parziale, word, 0, anagrammi);
		
		return anagrammi;
	} 
	
	/**
	 * RICORSIONE
	 * @param parziale - soluzione parziale
	 * @param word - parola iniziale da cui si vogliono calcolare gli anagrammi
	 * @param livello - livello della ricorsione, il passo
	 * @param anagrammi - Set in cui si aggiungono i risultati
	 */
	public void calcola(String parziale, String word, int livello, Set<String> anagrammi) {
		
		//	Condizione di terminazione: quando l'anagramma sarà lungo quanto la parola
		if (livello == word.length()) {
			//	Aggiungo al Set la soluzione parziale, che in realtà sarà già quella finale
			anagrammi.add(parziale);
			return ;
		}
		
		//	Se non siamo arrivati alla fine, dobbiamo costruirci la soluzione
		for (int i = 0; i < word.length(); i++) {
			
			//	Se il numero di caratteri contenuti nella soluzione parziale è minore del numero di caratteri contenuti nella parola inserita
			//	... significa che non siamo ancora arrivati alla fine della costruzione dell'anagramma
			if (charCounter(parziale, word.charAt(i)) < charCounter(word, word.charAt(i))) {

				// Aggiungo alla soluzione (parziale) i caratteri trovati nella parola inserita
				parziale += word.charAt(i);

				calcola(parziale, word, livello + 1, anagrammi);	//	Metodo ricorsivo: passo al livello superiore
				parziale = parziale.substring(0, parziale.length() - 1); // scarico l'ultimo char, che non ci interessa
				
			}
		}
		
	}
	
	/**
	 * Conta il numero di caratteri contenuti in una stringa
	 * @param word - parola da cui si vogliono ricavare gli anagrammi
	 * @param c - carattere cercato
	 * @return - numero c contenuti in word
	 */
	public static int charCounter(String word, char c) {
		int count = 0;
		
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == c) {	//	!!! charAt()
				count++;
			}
		}
		
		return count;
	}
	
}
