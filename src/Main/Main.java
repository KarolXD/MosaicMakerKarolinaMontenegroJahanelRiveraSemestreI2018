/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import GUI.MosaicMaker;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author karol
 */
public class Main extends Application implements EventHandler<ActionEvent> {

    private Pane pane;
    private Button enter, exit;
    private Label texto;
    public static TextField fieldName;
    private Scene scene;
    private MosaicMaker print;

    //Iniciamos los components
    @Override
    public void start(Stage primaryStage) {

        pane = new Pane();
        //le añadimos color al Pane
        pane.setStyle("-fx-background-color:  #138D75 ");
        pane.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));

        scene = new Scene(pane, 170, 100);
        Image aceppt = new Image("/images/accept.png");
        ImageView myImageView = new ImageView(aceppt);

        Image exitt = new Image("/images/del.png");
        ImageView myImageView2 = new ImageView(exitt);

        this.texto = new Label("Write your name :");
        this.texto.relocate(/*Izz*/10, /*DERE*/ 10);
        this.texto.setMinSize(100, 20);
        this.texto.setTextFill(Color.BURLYWOOD);
        this.texto.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));

        Main.fieldName = new TextField();
        Main.fieldName.relocate(10, 30);
        Main.fieldName.setMinSize(20, 20);

        this.enter = new Button("Accept", myImageView);
        this.enter.setOnAction(this);
        this.enter.relocate(75, 60);
        this.enter.setMinSize(50, 20);
        this.enter.setTextFill(Color.BURLYWOOD);
        this.enter.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        this.exit = new Button("Exit", myImageView2);
        this.exit.setOnAction(this);
        this.exit.relocate(10, 60);
        this.exit.setMinSize(30, 20);
        this.exit.setTextFill(Color.BURLYWOOD);
        this.exit.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        pane.getChildren().addAll(texto);
        pane.getChildren().add(fieldName);
        pane.getChildren().addAll(enter, myImageView);
        pane.getChildren().addAll(exit, myImageView2);

        //CONSTRUYENDO LA VENTANA///
        primaryStage.setTitle("Mosaic Maker ");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
//Este metodo verifica si el usuario no deja el campo de texto vacio, en caso ded dejarlo vacio, se envia un sms de advertencia
//En caso de escribir correctamente en el campo de texto, procedemos al juego!

    public void verifiqueField(String name) {
        if (name.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Alert");
            alert.setHeaderText("YOU LEFT THIS FIELD BLANK");
            alert.getDialogPane().setPrefSize(200, 100);
            alert.showAndWait();

        } else if (!name.equals("")) {

             print = new MosaicMaker();
            Stage stage = new Stage();
            print.start(stage);

        }

    }

    //MAIN
    public static void main(String arv[]) {
        launch(arv);
    }

    
    //EVENTOS
    @Override
    public void handle(ActionEvent event) {
        if ((Button) event.getSource() == enter) {
            int name = fieldName.getText().length();
           if(name<= 8){
            String namee=fieldName.getText();   
           verifiqueField(namee);
           }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert");
            alert.setHeaderText("The name is so EXTENSIVE");
            alert.getDialogPane().setPrefSize(200, 100);
            alert.showAndWait();

           }
            
           
        } else if ((Button) event.getSource() == exit) {
            System.exit(0);
        }
    }

}
