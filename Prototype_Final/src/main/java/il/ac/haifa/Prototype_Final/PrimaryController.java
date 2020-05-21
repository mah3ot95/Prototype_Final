package il.ac.haifa.Prototype_Final;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class PrimaryController {
    @FXML
    private Button showquestionsBtn;

    @FXML
    private Button exitBtn;

    @FXML
    void exit(ActionEvent event) {
    	System.out.println("client disconnected");
    	System.exit(0);
    }

    @FXML
    void show_questions(ActionEvent event) throws IOException {
    	System.out.println("asked server for questions");
    	App.getClient_App().sendToServer("show_questions");
    	App.setRoot("secondary");
    }

}
