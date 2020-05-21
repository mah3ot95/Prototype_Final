package il.ac.haifa.Prototype_Final;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.event.ActionEvent;

public class QuestionFXML {
	@FXML
    private void initialize() 
    {
		//question = new Question("question", "ans_a", "ans_b", "ans_c", "ans_d", "correct_ans");
		//display();
    }
	
    private void display() {
		titleTP.setText("Q "+question.getId());
		questionTF.setText(question.getQuestion());
		questionTF.setDisable(true);
		aTF.setDisable(true);
		a_ansTF.setText(question.getAns_a());
		a_ansTF.setDisable(true);
		bTF.setDisable(true);
		b_ansTF.setText(question.getAns_b());
		b_ansTF.setDisable(true);
		cTF.setDisable(true);
		c_ansTF.setText(question.getAns_c());
		c_ansTF.setDisable(true);
		dTF.setDisable(true);
		d_ansTF.setText(question.getAns_d());
		d_ansTF.setDisable(true);
		correct_ansTF.setDisable(true);
		ansTF.setText(question.getCorrect_ans());
		ansTF.setDisable(true);
		
		saveBtn.setDisable(true);
	}
    Question question;


    @FXML
    private TextField questionTF;

    @FXML
    private TextField aTF;

    @FXML
    private TextField a_ansTF;

    @FXML
    private TextField bTF;

    @FXML
    private TextField b_ansTF;

    @FXML
    private TextField cTF;

    @FXML
    private TextField c_ansTF;

    @FXML
    private TextField dTF;

    @FXML
    private TextField d_ansTF;

    @FXML
    private TextField correct_ansTF;

    @FXML
    private TextField ansTF;

    @FXML
    private Button editBtn;

    @FXML
    private Button saveBtn;

    @FXML
    void edit(ActionEvent event) {
    	questionTF.setDisable(false);
    	a_ansTF.setDisable(false);
    	b_ansTF.setDisable(false);
    	c_ansTF.setDisable(false);
    	d_ansTF.setDisable(false);
    	ansTF.setDisable(false);
    	
    	editBtn.setDisable(true);
    	saveBtn.setDisable(false);
    	
    	System.out.println("client is updating question #" + question.getId());
    }
    @FXML
    private TitledPane titleTP;

    @FXML
    void save(ActionEvent event) throws Exception {
    	question.setAns_a(a_ansTF.getText());
    	question.setAns_b(b_ansTF.getText());
    	question.setAns_c(c_ansTF.getText());
    	question.setAns_d(d_ansTF.getText());
    	question.setCorrect_ans(ansTF.getText());
    	question.setQuestion(questionTF.getText());
    	
    	questionTF.setDisable(true);
    	a_ansTF.setDisable(true);
    	b_ansTF.setDisable(true);
    	c_ansTF.setDisable(true);
    	d_ansTF.setDisable(true);
    	ansTF.setDisable(true);
    	
    	saveBtn.setDisable(true);
    	editBtn.setDisable(false);
    	
    	System.out.println("client sent a request to server to update question #" + question.getId());
    	App.getClient_App().sendToServer(question);
    }
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
		display();
	}
    
}
