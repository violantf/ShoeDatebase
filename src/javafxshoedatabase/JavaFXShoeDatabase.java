/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxshoedatabase;

import java.util.Optional;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author owner
 */
public class JavaFXShoeDatabase extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Main.fxml"));
        
        Scene scene = new Scene(root);
        
        scene.getStylesheets().add(JavaFXShoeDatabase.class.getResource("Style.css").toExternalForm());
        
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Shoe Organizer: Visual Database");
        
        stage.setOnCloseRequest((WindowEvent) -> {
            System.out.println("on Hiding Event");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> result = alert.showAndWait();
            if((result.isPresent()) && (result.get() == ButtonType.OK)) {
            } else { 
                WindowEvent.consume();
                System.out.println("Close Cancelled");
            }
        }); 
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
