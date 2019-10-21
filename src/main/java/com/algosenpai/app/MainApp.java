package com.algosenpai.app;


import com.algosenpai.app.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.algosenpai.app.logic.Logic;
import com.algosenpai.app.logic.Parser;

import java.io.IOException;

/**
 * A one scene chatbot GUI.
 * There are two fxml files, MainWindow and DialogBox.
 */
public class MainApp extends Application {

    //Initialise the different components here
    private Parser parser = new Parser();
    private Logic logic = new Logic(parser);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<Ui>getController().setLogic(logic);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}