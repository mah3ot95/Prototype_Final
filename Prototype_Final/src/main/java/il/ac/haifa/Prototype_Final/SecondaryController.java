package il.ac.haifa.Prototype_Final;

import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;

import il.ac.haifa.Prototype_Final.ocsf.Client;

public class SecondaryController {
	
	@FXML
    private HBox hbox;
    @FXML
    private ListView<TitledPane> questionsLV;
    @FXML
    private Button closeBtn;
    @FXML
    private Button reftreshBtn;
    private List<Question> questionsList;
    /*
    private static void updateQuestion(int index, Question q) {
    	FXMLLoader fxmlLoader = new FXMLLoader(QuestionFXML.class.getResource("question.fxml"));
    	try {
			questionsLV.getItems().set(index, fxmlLoader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	QuestionFXML temp = fxmlLoader.getController();
    	temp.setQuestion(q);
	}
	*/
	@FXML
	private void initialize() throws Exception 
    {
		display();
    }
	private void display() throws Exception 
    {
		questionsList = Client.getQuestions();
		for (Question q : questionsList ) {
			FXMLLoader fxmlLoader = new FXMLLoader(QuestionFXML.class.getResource("question.fxml"));
			questionsLV.getItems().add(fxmlLoader.load());
			QuestionFXML temp = fxmlLoader.getController();
			temp.setQuestion(q);
		}
    }
	@FXML
    void close(ActionEvent event) throws IOException{
		App.setRoot("primary");
    }
	
    @FXML
    void refresh(ActionEvent event) {
    	try {
			//initialize();
    		questionsLV.getItems().clear();
    		display();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}