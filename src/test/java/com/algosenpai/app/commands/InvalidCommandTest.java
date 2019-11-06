package com.algosenpai.app.commands;

import com.algosenpai.app.logic.Logic;
import com.algosenpai.app.logic.command.Command;
import com.algosenpai.app.stats.UserStats;
import com.algosenpai.app.storage.Storage;
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

import java.io.IOException;

public class InvalidCommandTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(UndoCommandTest.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap, 500, 650);
        stage.setScene(scene);
        UserStats stats = UserStats.parseString(Storage.loadData("UserData.txt"));
        Logic logic = new Logic(stats);
        fxmlLoader.<Ui>getController().setLogic(logic, stats);
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
    void testInvalidMousePress() {
        clickOn("#userInput").write("me nu");
        clickOn("#sendButton");
        VBox container = find();
        DialogBox dialogBox = (DialogBox) container.getChildren().get(1);
        String actualText = dialogBox.getDialog().getText();
        Assertions.assertEquals("me nu", actualText);
    }

    @Test
    void testInvalidKeyPress() {
        clickOn("#userInput").write("printt").press(KeyCode.ENTER);
        VBox container = find();
        DialogBox dialogBox = (DialogBox) container.getChildren().get(1);
        String actualText = dialogBox.getDialog().getText();
        Assertions.assertEquals("printt", actualText);
    }

    @Test
    void testInvalidMultipleSpaces() throws IOException {
        UserStats stats = new UserStats("./UserData.txt");
        Logic logic = new Logic(stats);
        Command command = logic.executeCommand("se le ct");
        String actualText = command.execute();
        Assertions.assertEquals("OOPS!!! Error occurred. Please input a valid command. "
                                          + "Did you mean... select?", actualText);
    }

    @Test
    void testInvalidUnknownCharacterAbsentFromAllCommands() throws IOException {
        UserStats stats = new UserStats("./UserData.txt");
        Logic logic = new Logic(stats);
        Command command = logic.executeCommand("f");
        String actualText = command.execute();
        Assertions.assertEquals("OOPS!!! Error occurred. Please input a valid command. Did you mean... "
                                          + "menu, quiz, undo, help, save, exit?", actualText);
    }

    @Test
    void testInvalidWithMultipleMinEditDistance() throws IOException {
        UserStats stats = new UserStats("./UserData.txt");
        Logic logic = new Logic(stats);
        Command command = logic.executeCommand("la");
        String actualText = command.execute();
        Assertions.assertEquals("OOPS!!! Error occurred. Please input a valid command. Did you mean... "
                                          + "clear, help, save?", actualText);
    }

    @Test
    void testInvalidWithMaximumEditDistance() throws IOException {
        UserStats stats = new UserStats("./UserData.txt");
        Logic logic = new Logic(stats);
        Command command = logic.executeCommand("fffgggjjjkkk");
        String actualText = command.execute();
        Assertions.assertEquals("OOPS!!! Error occurred. Please input a valid command. Did you mean... "
                                          + "hello, menu, quiz, select, result, history, undo, clear, help, "
                                          + "volume, print, archive, save, reset, exit?", actualText);
    }

    <T extends Node> T find() {
        return lookup("#dialogContainer").query();
    }
}