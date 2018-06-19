/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ki105projekat;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class KI105Projekat extends Application {

    RotateTransition rotateTransition;
    TranslateTransition translateTransition;
    ScaleTransition scaleTransition;

    // fleg za ispitivanje da li je animacija startovana
    boolean started = false;

    //pretpostavka za trajanja animacija 10 secundi
    int SECOUND_MILLIS = 10000;

    Rectangle rectangle;

    BorderPane root = new BorderPane();

    @Override
    public void start(Stage primaryStage) {

        FlowPane northPane = new FlowPane(Orientation.HORIZONTAL);
        TextField txtStranica = new TextField("Stranica kvadrata");
        TextField txtBrzina = new TextField("Brzina animacije");

        //popunjavanje liste opcija combo boxa
        final ComboBox comboAnimation = new ComboBox();
        comboAnimation.getItems().addAll("Rotacija", "Translacija", "Skaliranje");

        comboAnimation.setPromptText("Choose animation");

        Button btnControl = new Button("Start/pause");
        // sever
        northPane.getChildren().addAll(txtStranica, txtBrzina, comboAnimation, btnControl);

        rectangle = new Rectangle(150, 150);
        rectangle.setFill(Color.RED);

        // jug
        // 3 labele i 3 
        Label l1 = new Label("Red");
        Label l2 = new Label("Green");
        Label l3 = new Label("Blue");
        TextField txt3 = new TextField();
        TextField txt4 = new TextField();
        TextField txt5 = new TextField();

        BorderPane bp2 = new BorderPane();
        GridPane gp = new GridPane();
        gp.add(l1, 1, 1);
        gp.add(l2, 1, 2);
        gp.add(l3, 1, 3);
        gp.add(txt3, 2, 1);
        gp.add(txt4, 2, 2);
        gp.add(txt5, 2, 3);

        bp2.setCenter(gp);

        root.setTop(northPane);
        root.setCenter(rectangle);
        root.setBottom(bp2);

        btnControl.setOnAction(e -> {
            if (!started) {
                String selection = (String) comboAnimation.getSelectionModel().getSelectedItem();
                int stranicaKvadrata = Integer.parseInt(txtStranica.getText());
                rectangle.setHeight(stranicaKvadrata);
                rectangle.setWidth(stranicaKvadrata);
                int red = Integer.parseInt(txt3.getText());
                int green = Integer.parseInt(txt4.getText());
                int blue = Integer.parseInt(txt5.getText());
                rectangle.setFill(Color.rgb(red, green, blue));
                int brzinaAnimacije = Integer.parseInt(txtBrzina.getText());
                switch (selection) {
                    case "Rotacija":
                        rotateAnimation(rectangle, brzinaAnimacije);
                        break;
                    case "Translacija":
                        translateAnimation(rectangle, brzinaAnimacije);
                        break;
                    case "Skaliranje":
                        scaleAnimation(rectangle, brzinaAnimacije);

                }
            } else {
                String selection = (String) comboAnimation.getSelectionModel().getSelectedItem();
                switch (selection) {
                    case "Rotacija":
                        rotateTransition.stop();
                        break;
                    case "Translacija":
                        translateTransition.stop();
                        break;
                    case "Skaliranje":
                        scaleTransition.stop();

                }
            }
            started = !started;
        });

        Scene scene = new Scene(root, 500, 500);

        primaryStage.setTitle("Animacija");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

    public void rotateAnimation(Rectangle rectangle, int speed) {
        rotateTransition = new RotateTransition(Duration.millis(SECOUND_MILLIS / speed), rectangle);

        rotateTransition.setCycleCount(Timeline.INDEFINITE);

        rotateTransition.setByAngle(360);
        rotateTransition.play();
    }

    public void translateAnimation(Rectangle rectangle, int speed) {
        System.out.println("Trahnslacija");

        translateTransition = new TranslateTransition(Duration.millis(SECOUND_MILLIS / speed), rectangle);

        translateTransition.setCycleCount(Timeline.INDEFINITE);

        translateTransition.setFromX(root.getLayoutX());
        translateTransition.setToX(root.getWidth());
        translateTransition.play();

    }

    public void scaleAnimation(Rectangle rectangle, int speed) {

        scaleTransition = new ScaleTransition(Duration.millis(SECOUND_MILLIS / speed), rectangle);

        scaleTransition.setCycleCount(Timeline.INDEFINITE);

        scaleTransition.setByX(1);
        scaleTransition.setByY(1);
        scaleTransition.play();

    }

}
