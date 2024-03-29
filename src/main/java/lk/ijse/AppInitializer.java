package lk.ijse;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        session.close();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/login-form.fxml"))));
        stage.show();
    }
}