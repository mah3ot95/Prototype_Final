package il.ac.haifa.Prototype_Final;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import il.ac.haifa.Prototype_Final.ocsf.Client;

/**
 * JavaFX App
 */
public class App extends Application {
	

    private static Scene scene;
    private static Client client_App;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"),550,400);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

	public static Client getClient_App() {
		return client_App;
	}

	public static void setClient_App(Client client_App) {
		App.client_App = client_App;
	}

}