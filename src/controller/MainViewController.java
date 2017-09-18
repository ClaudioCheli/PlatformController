package controller;

import javafx.beans.value.ObservableValue;
import model.AppSystemInfo;
import socketCommunication.AndroidClientSocket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.Connection;
import model.Player;

import java.net.URL;

import java.util.ResourceBundle;

public class MainViewController implements Initializable{

    private Connection connection;
    private AndroidClientSocket clientSocket;

    @FXML
    private TextField positionXFieldRead;
    @FXML
    private TextField positionYFieldRead;
    @FXML
    private TextField speedFieldRead;
    @FXML
    private TextField jumpSpeedFieldRead;
    @FXML
    private TextField positionXFieldWrite;
    @FXML
    private TextField positionYFieldWrite;
    @FXML
    private TextField speedFieldWrite;
    @FXML
    private TextField jumpSpeedFieldWrite;
    @FXML
    private TextField portField;
    @FXML
    private TextField ipField;
    @FXML
    private TextField fpsField;

    @FXML
    private Label connectionStatusLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = Connection.getConnection();
        clientSocket = AndroidClientSocket.getClient();

        setConnectionStatusLabel(false);

        portField.setText(Integer.toString(connection.getPort()));
        ipField.setText(connection.getIp());

        portField.setOnAction((ActionEvent e) -> connection.setPort(Integer.parseInt(portField.getText())));
        ipField.setOnAction((ActionEvent e) -> connection.setIp(ipField.getText()));

        Player.yPosProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            positionYFieldRead.setText(Float.toString(newValue.floatValue()));
        });
        Player.xPosProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            positionXFieldRead.setText(Float.toString(newValue.floatValue()));
        });
        Player.speedProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            speedFieldRead.setText(Float.toString(newValue.floatValue()));
        });
        Player.jumpSpeedProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            jumpSpeedFieldRead.setText(Float.toString(newValue.floatValue()));
        });

        Player.fpsProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            fpsField.setText(Integer.toString(newValue.intValue()));
        });
    }

    @FXML
    private void connect() {
        Player.reset();
        AppSystemInfo.reset();
        clientSocket.connect();
    }

    @FXML
    private void disconnect() {
        clientSocket.disconnect();
        Player.reset();
        AppSystemInfo.reset();
    }

    @FXML
    private void Send() {

    }

    public void setConnectionStatusLabel(boolean status) {
        if (status) {
            connectionStatusLabel.setText("Connected");
            connectionStatusLabel.setTextFill(Color.web("#00FF00"));
        } else {
            connectionStatusLabel.setText("Not Connected");
            connectionStatusLabel.setTextFill(Color.web("#FF0000"));
        }
    }

}
