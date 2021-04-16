/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.anagrammiModel.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtWord"
    private TextField txtWord; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalcola"
    private Button btnCalcola; // Value injected by FXMLLoader

    @FXML // fx:id="txtCorrect"
    private TextArea txtCorrect; // Value injected by FXMLLoader

    @FXML // fx:id="TxtWrong"
    private TextArea txtWrong; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML
    void doCalcolo(ActionEvent event) {
    	txtCorrect.clear();
    	txtWrong.clear();
    	
    	String word = txtWord.getText();
    	
    	if (word.length() < 2) {
			txtCorrect.appendText("Parola inserita troppo corta");
			txtWrong.appendText("Parola inserita troppo corta");
		}
    	
    	//	Se è troppo lunga, il tempo di calcolo è eccessivo
    	if (word.length() > 8) {
			txtCorrect.appendText("Parola inserita troppo lunga");
			txtWrong.appendText("Parola inserita troppo lunga");
		}
    	
    	Set<String> anagrammi = this.model.calcolaAnagrammi(word);
    	
    	for (String anagramma : anagrammi) {
			if(this.model.isCorrect(anagramma))
				txtCorrect.appendText(anagramma +"\n");
			else
				txtWrong.appendText(anagramma +"\n");
		}
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtWord.clear();
    	txtCorrect.clear();
    	txtWrong.clear();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtWord != null : "fx:id=\"txtWord\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorrect != null : "fx:id=\"txtCorrect\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtWrong != null : "fx:id=\"TxtWrong\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		
	}
}
