package com.algosenpai.app.commands;

import com.algosenpai.app.logic.Logic;
import com.algosenpai.app.ui.Ui;
import com.algosenpai.app.ui.components.DialogBox;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

public class HelpCommandTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HistoryCommandTest.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap, 500, 650);
        stage.setScene(scene);
        Logic logic = new Logic();
        fxmlLoader.<Ui>getController().setLogic(logic);
        stage.setResizable(false);
        stage.setTitle("AlgoSenpai Adventures");
        stage.show();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() throws Exception {
        FxToolkit.hideStage();
    }

    @Test
    void testHelpMousePress() {
        clickOn("#userInput").write("help 1");
        clickOn("#sendButton");
        VBox container = find();
        DialogBox dialogBox = (DialogBox) container.getChildren().get(1);
        String actualText = dialogBox.getDialog().getText();
        Assertions.assertEquals("help 1", actualText);
    }

    @Test
    void testHelpKeyPress() {
        clickOn("#userInput").write("help 1").press(KeyCode.ENTER);
        VBox container = find();
        DialogBox dialogBox = (DialogBox) container.getChildren().get(1);
        String actualText = dialogBox.getDialog().getText();
        Assertions.assertEquals("help 1", actualText);
    }

    @Test
    void testHelpWithSpace() {
        clickOn("#userInput").write(" help 1 ").clickOn("#sendButton");
        VBox container = find();
        DialogBox dialogBox = (DialogBox) container.getChildren().get(1);
        String actualText = dialogBox.getDialog().getText();
        Assertions.assertEquals(" help 1 ", actualText);
    }

    @Test
    void testHelpOutputChapter1() {
        clickOn("#userInput").write("help 1").clickOn("#sendButton");
        VBox container = find();
        DialogBox dialogBox = (DialogBox) container.getChildren().get(2);
        String actualText = dialogBox.getDialog().getText();
        Assertions.assertEquals("Try solving these problems on Kattis:\n"
                + "cups, lineup, mjehuric, sidewayssorting", actualText);
    }

    @Test
    void testHelpOutputChapter2() {
        clickOn("#userInput").write("help 2").clickOn("#sendButton");
        VBox container = find();
        DialogBox dialogBox = (DialogBox) container.getChildren().get(2);
        String actualText = dialogBox.getDialog().getText();
        Assertions.assertEquals("Try solving these problems on Kattis:\n"
                + "coconut, integerlists, joinstrings", actualText);
    }

    <T extends Node> T find() {
        return lookup("#dialogContainer").query();
    }
}