/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Main.JavaFxApplications;
import domain.Beans;
import data.MosaicFile;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.control.Button;
import javafx.stage.*;
import javax.imageio.ImageIO;
import java.io.*;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class MosaicMaker extends Application implements EventHandler<ActionEvent> {

    private int contador2 = 0;
    private int contador1 = 0;
    private int tempWidLines;
    private TextField fieldWidth;
    private Label labelWeigt, labelColor, name, nameCanvas1, nameCanvas2;
    private Button save, cleanCanvas1, search, cleanCanvas2, saveProject, openProject, openProject2;
    private Canvas canvas, canvas2;
    private Pane pane;
    private Scene scene;
    private ImageView myImageView, paste, copy, cut, rotateL, rotateR, flipL, flipR, delete, paste2, clean, savee, imageView, hii, clean2, open, saveProje, openn2;
    private Image image;
    private final int heigth = 700;
    private final int weigth = 700;
    private final int weigth2 = 700;
    private ComboBox combo;
    private Group root, root2;
    private MenuItem menu1, menu2, menu3, menu4, menu33, menu44, menu5, menu6, menu7, menu8, menu9;
    private ContextMenu cm, contextMenu2;
    private GraphicsContext gc, ggc;
    private Alert alert;
    private PixelReader pixel;
    private SnapshotParameters snapshot;
    private int ejeX, ejeY, ejeX2, ejeY2;
    private WritableImage writableImage;
    private WritableImage writableImage2;
    private BufferedImage bufferedImage;
    private MosaicFile mosaikFile;
    Beans beans;

    @Override

    /*Iniciamos componentes*/
    public void start(Stage primaryStage) {
        try {

            Image searchhh = new Image("/images/search.png");
            myImageView = new ImageView(searchhh);

            Image buttonSave = new Image("/images/saa.png");
            savee = new ImageView(buttonSave);

            Image cleaan = new Image("/images/clean.png");
            clean = new ImageView(cleaan);

            Image cleaan2 = new Image("/images/clean.png");
            clean2 = new ImageView(cleaan2);

            Image hi = new Image("/images/hi.png");
            hii = new ImageView(hi);

            Image open1 = new Image("/images/open1.png");
            open = new ImageView(open1);

            Image open2 = new Image("/images/open1.png");
            openn2 = new ImageView(open2);

            Image saveAll = new Image("/images/save_all.png");
            saveProje = new ImageView(saveAll);

            this.snapshot = new SnapshotParameters();

            pane = new Pane();//Instanciamos el JFRAME

            pane.setStyle("-fx-background-color:  #C0392B ");
            pane.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));

            scene = new Scene(pane, 1800, 845); //INSTANCIAMOS LA SCENA, CON TAMAÑO Y COLOR DE FONDO

            canvas = new Canvas(weigth, heigth);//instanciamos el JPanel
            canvas.relocate(0, 0);//le ponemos una ubicacion dentro del JFRAME

            canvas2 = new Canvas(weigth, heigth);
            canvas2.relocate(900, 0); //800,0

            this.labelWeigt = new Label("Width:");
            this.labelWeigt.relocate(/*Izz*/20, /*DERE*/ 707);//370,300  470 400
            this.labelWeigt.setMinSize(100, 20);//150,20
            this.labelWeigt.setTextFill(Color.BURLYWOOD);
            this.labelWeigt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));

            this.fieldWidth = new TextField();
            this.fieldWidth.relocate(20, 730);//720
            // this.fieldWidth.setMinSize(20, 20);
            this.fieldWidth.setMaxSize(90, 0);

            this.labelColor = new Label("Choose color:");
            this.labelColor.relocate(/*IZQ*/200, /*DERE*/ 740);//370,300  470 400
            this.labelColor.setMinSize(100, 20);//150,20
            this.labelColor.setTextFill(Color.BURLYWOOD);
            this.labelColor.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));

            this.nameCanvas1 = new Label(" CANVAS 1");
            this.nameCanvas1.relocate(/*IZQ*/200, /*DERE*/ 700);//370,300  470 400
            this.nameCanvas1.setMinSize(100, 20);//150,20
            this.nameCanvas1.setTextFill(Color.WHITE);
            this.nameCanvas1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 23));

            this.nameCanvas2 = new Label(" CANVAS 2");
            this.nameCanvas2.relocate(/*IZQ*/1200, /*DERE*/ 700);//370,300  470 400
            this.nameCanvas2.setMinSize(100, 20);//150,20
            this.nameCanvas2.setTextFill(Color.WHITE);
            this.nameCanvas2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 23));

            this.name = new Label("¡Hii " + Main.fieldName.getText() + "!", hii);
            this.name.relocate(/*IZQ*/600, /*DERE*/ 800);//370,300  470 400
            this.name.setMinSize(20, 20);//150,20
            this.name.setTextFill(Color.BURLYWOOD);
            this.name.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));

            combo = new ComboBox();

            combo.getItems().addAll(
                    "WHITE",
                    "BLACK",
                    "BLUE",
                    "FUCHSIA",
                    "GREEN",
                    "SALMON",
                    "DARKBLUE",
                    "CORAL",
                    "BLUEVIOLET",
                    "SIENNA"
            );
            this.combo.relocate(200, 770);//325 320
            this.combo.setMinSize(20, 20);//150,20

            this.search = new Button("Search", myImageView);
            this.search.setOnAction(this);
            this.search.relocate(20, 770);//750
            this.search.setMinSize(50, 20);
            this.search.setTextFill(Color.BURLYWOOD);
            this.search.setFont(Font.font("Lucida Handwriting", FontWeight.BOLD, FontPosture.REGULAR, 14));

            this.saveProject = new Button("Save projet", saveProje);
            this.saveProject.setOnAction(this);
            this.saveProject.relocate(1200, 750);
            this.saveProject.setMinSize(50, 20);
            this.saveProject.setTextFill(Color.BURLYWOOD);
            this.saveProject.setFont(Font.font("Lucida Handwriting", FontWeight.BOLD, FontPosture.REGULAR, 14));

            this.openProject = new Button("Open projet Canvas 2", open);
            this.openProject.setOnAction(this);//canvas 2
            this.openProject.relocate(1010, 748);
            this.openProject.setMinSize(50, 20);
            this.openProject.setTextFill(Color.BURLYWOOD);
            this.openProject.setFont(Font.font("Lucida Handwriting", FontWeight.BOLD, FontPosture.REGULAR, 12));

            this.openProject2 = new Button("Open projet Canvas 1 ", openn2);
            this.openProject2.setOnAction(this);//canvas 1
            this.openProject2.relocate(350, 770);
            this.openProject2.setMinSize(50, 20);
            this.openProject2.setTextFill(Color.BURLYWOOD);
            this.openProject2.setFont(Font.font("Lucida Handwriting", FontWeight.BOLD, FontPosture.REGULAR, 12));

            this.cleanCanvas1 = new Button("Clean", clean);
            this.cleanCanvas1.setOnAction(this);
            this.cleanCanvas1.relocate(110, 770);
            this.cleanCanvas1.setMinSize(50, 20);
            this.cleanCanvas1.setTextFill(Color.BURLYWOOD);
            this.cleanCanvas1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));

            this.cleanCanvas2 = new Button("Clean", clean2);
            this.cleanCanvas2.setOnAction(this);
            this.cleanCanvas2.relocate(1450, 750);
            this.cleanCanvas2.setMinSize(50, 20);
            this.cleanCanvas2.setTextFill(Color.BURLYWOOD);
            this.cleanCanvas2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));

            this.save = new Button("Save", savee);
            this.save.setOnAction(this);
            this.save.relocate(1350, 750);
            this.save.setMinSize(50, 20);
            this.save.setTextFill(Color.BURLYWOOD);
            this.save.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));

            gc = canvas.getGraphicsContext2D();
            gc.setFill(Color.BURLYWOOD);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

            ggc = canvas2.getGraphicsContext2D();
            ggc.setFill(Color.BURLYWOOD);
            ggc.fillRect(0, 0, canvas2.getWidth(), canvas2.getHeight());

            ///add components
            pane.getChildren().addAll(openProject, open);
            pane.getChildren().addAll(openProject2, openn2);
            pane.getChildren().addAll(saveProject, saveProje);
            pane.getChildren().add(labelWeigt);
            pane.getChildren().add(labelColor);
            pane.getChildren().add(fieldWidth);
            pane.getChildren().add(combo);
            pane.getChildren().addAll(search, myImageView);
            pane.getChildren().addAll(cleanCanvas1, clean);
            pane.getChildren().addAll(cleanCanvas2, clean2);
            pane.getChildren().add(canvas);  //anadimos el canvas al pane
            pane.getChildren().add(canvas2);  //anadimos el canvas al pane
            pane.getChildren().addAll(save, savee);
            pane.getChildren().addAll(name, hii);
            pane.getChildren().addAll(nameCanvas1);
            pane.getChildren().addAll(nameCanvas2);

            //CONSTRUYENDO LA VENTANA
            primaryStage.setTitle("Mosaic Maker ");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("error to read");
        }

    }

    /*Este metodo guarda la imagen pixeleada, en el directorio que seleccionemos, por medio de la clase FILECHOOSER*/
    public void saveMosaik() {
        writableImage = new WritableImage((int) canvas2.getWidth(), (int) canvas2.getHeight());//capturamos el ancho y largo de la imagen
        canvas2.snapshot(null, writableImage);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("images files (*.jpg)", "*.png");

        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {

                ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);

                System.out.println("snapshot saved: " + file.getAbsolutePath());

            } catch (IOException ex) {

            }

        } else {

        }
    }

    /*Este método guarda todo el proceso de nuestro proyecto, tambièn crea un archivo, en el cuàl se almacenará la información del tamaño 
    de las líneas*/
    public void saveAll() {
        File fileCar = new File("./FieldText.dat");

        try {
            mosaikFile = new MosaicFile(fileCar);

            saveMProjectt();
        } catch (IOException ex) {
            System.out.println("Incorrect date");
        }
    }

    /*Este método guarda todo el proceso de nuestro proyecto, se guarda como archivo en extenciòn .dat*/
    public void saveMProjectt() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("images files (*.dat)", "*.dat");

        fileChooser.getExtensionFilters().add(extFilter);

        File path = fileChooser.showSaveDialog(null);
        if (path != null) {
            try {
                int widthLines = Integer.parseInt(fieldWidth.getText());
                tempWidLines = widthLines;

                String temp1 = String.valueOf(path);
                String file1 = temp1.substring(temp1.lastIndexOf('/') + 1, temp1.lastIndexOf('.')) + "(canvas2)" + ".dat";
                File pathTem1 = new File(file1);

                writableImage2 = new WritableImage((int) canvas2.getWidth(), (int) canvas2.getHeight());
                canvas2.snapshot(snapshot, writableImage2);
                RenderedImage renderedImage2 = SwingFXUtils.fromFXImage(writableImage2, null);
                ImageIO.write(renderedImage2, "png", pathTem1);//guardar en la direccion establecida

                writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                canvas.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);

                String temp = String.valueOf(path);
                String file = temp.substring(temp.lastIndexOf('/') + 1, temp.lastIndexOf('.')) + "(canvas1) " + widthLines + ".dat";
                File pathTem = new File(file);

                String tempWidth = String.valueOf(widthLines);
                beans = new Beans(tempWidth);
                mosaikFile.addEndRecord(beans);

                ImageIO.write(renderedImage, "png", pathTem);

                // ImageIO.write(renderedImage2, "png", pathTem);//guardar en la direccion establecida
            } catch (Exception ex) {

            }

        } else {

        }
    }

    /*Este método busca en el directorio archivos, con el fin de abrirlos y almacenarlos en el canvas 2*/
    public void openProjectCanvas2() {

        try {
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("Image Files", "*.dat", "*.dat");//dat
            FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
            FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
            fileChooser.getExtensionFilters().addAll(extFilterJPG);//, extFilterjpg, extFilterPNG, extFilterpng);

            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
            bufferedImage = ImageIO.read(file);
            if (file != null) {
                try {

                    image = SwingFXUtils.toFXImage(bufferedImage, writableImage2);

                    ggc = canvas2.getGraphicsContext2D();
                    ggc.getCanvas();
                    ggc.setStroke(ggc.getStroke());
                    ggc.setLineWidth(4);

                    ggc.drawImage(image, 0, 0);

                    gc.setStroke(gc.getStroke());
                    gc.setLineWidth(4);

                } catch (Exception es) {

                }
            } else {

            }
        } catch (IOException e) {

        }
    }

    /*Este método busca en el directorio archivos, con el fin de abrirlos y almacenarlos en el canvas 1*/
    public void openProjectCanvas1() {

        try {
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("Image Files", "*.dat", "*.dat");//dat
            FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
            FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
            fileChooser.getExtensionFilters().addAll(extFilterJPG);//, extFilterjpg, extFilterPNG, extFilterpng);

            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
            bufferedImage = ImageIO.read(file);
            if (file != null) {
                try {

                    Image ima = SwingFXUtils.toFXImage(bufferedImage, null);
                   
                    gc = canvas.getGraphicsContext2D();
                    gc.drawImage(ima, 0, 0);
                    gc.getCanvas();
                    gc.setStroke(gc.getStroke());
                    gc.setLineWidth(4);
                    /*modificar un segmento de la ruta*/
                    String temp = file.getName();//obtenemos el nombre de la ruta, la almacenamos temporalmente en una variable

                    for (int i = temp.length(); i >= 0; i--) {//recorremos esa variable desde el tamano 
                        if (i > 0 && temp.substring(i - 1, i).equals(" ")) {//si hubiera algun espacio vacio, entra en la condicional

                            String getFinalValor = temp.substring(i, temp.length() - 4);//hacemos un sustring para obtener el valor del espacio en blanco, hasta el tamano de la palabra
               

                            int widthV = Integer.parseInt(getFinalValor);//almacenamos la informacio en una variable entera
                 
                            drawShapesCanvas11(gc, widthV);

                            fieldWidth.setText(String.valueOf(widthV));

                        }

                    }

                } catch (Exception es) {

                }
            } else {

            }
        } catch (IOException e) {

        }
    }

    /*La función de este metodo es buscar en el directorio  una imagen deseada, y almacenarla en el canvas 1 cuadriculada  */
    public void searchMosaik() throws IOException {
        try {
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg");//"JPG files (*.JPG)", "*.JPG");
            FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
            FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
            fileChooser.getExtensionFilters().addAll(extFilterJPG);//, extFilterjpg, extFilterPNG, extFilterpng);

            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
            bufferedImage = ImageIO.read(file);
            if (file != null) {
                try {

                    image = SwingFXUtils.toFXImage(bufferedImage, null);
                    this.pixel = image.getPixelReader(); //recibe los pixeles de la imagen
                    //this.imageView = new ImageView(this.image); 

                } catch (Exception es) {

                }
            } else {

            }
        } catch (IOException e) {
            System.err.println("Mush selected an image");
        }
    }

    /*Este metodo dibuja la cuadricula a base de lineas*/
    private void drawShapesCanvas1(GraphicsContext gc, int withLiness) {
        try {
            chooseColor(gc);//color a escogencia del usuario
            gc.setLineWidth(3);//ancho de las lineas
            withLiness = Integer.parseInt(fieldWidth.getText());//capturamos lo que hay en el campo de texto

            gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());//creamos un cuadrado con el tamaño del canvas
            gc.drawImage(image, 0, 0);//imagen antes de pegar la cuadricula al canvas
            popMenu(withLiness);//llamamos al popmenu, el cual contiene cada uno de los menuItem 
            for (int i = 0; i <= weigth; i++) { //recorremos el ancho del canvas
                for (int j = 0; j <= heigth; j++) {//recorremos el largo del canvas
                    gc.strokeLine(weigth, withLiness * i, 0, withLiness * i);//dibujamos las lineas horizontales
                    gc.strokeLine(withLiness * j, weigth, withLiness * j, 0);//dibujamos las lineas verticales

                }
            }
            pane.getChildren().add(root);//añadimos el componente root al pane
        } catch (NumberFormatException e) {

        }
    }

    /*Este método dibuja las lineas*/
    private void drawShapesCanvas11(GraphicsContext gc, int withLiness) {
        try {
           // gc = canvas.getGraphicsContext2D();
            //chooseColor(gc);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(3);//ancho de las lineas

            gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());//creamos un cuadrado con el tamaño del canvas

            popMenu(withLiness);//llamamos al popmenu, el cual contiene cada uno de los menuItem 
            for (int i = 0; i <= weigth; i++) { //recorremos el ancho del canvas
                for (int j = 0; j <= heigth; j++) {//recorremos el largo del canvas
                    gc.strokeLine(weigth, withLiness * i, 0, withLiness * i);//dibujamos las lineas horizontales
                    gc.strokeLine(withLiness * j, weigth, withLiness * j, 0);//dibujamos las lineas verticales

                }
            }
            pane.getChildren().add(root);//añadimos el componente root al pane
        } catch (NumberFormatException e) {

        }
    }

    /*Para que se pinten las lineas del canvas 1 al canvas 2*/
    private void drawShapesCanvas2(GraphicsContext gc, int widthLines) {
        try {
            chooseColor(gc);//El color a escogencia del usuario
            gc.setLineWidth(1);//ancho de las lineas
            //int row = Integer.parseInt(fieldWidth.getText());//atrapamos lo que hay en el campo de texto
            // widthLines = Integer.parseInt(fieldWidth.getText());
            gc.strokeRect(0, 0, canvas2.getWidth(), canvas2.getHeight());//creamos un cuadrado del tamaño del canvas 2

            popMenu2(widthLines);//llamamos al popmenu, cada canvas tiene un popmenu diferente
            for (int i = 0; i <= weigth2; i++) {//recorremos el ancho del canvas 2
                for (int j = 0; j <= heigth; j++) { //recorremos el largo del canvas 2
                    gc.strokeLine(weigth2, widthLines * i, 0, widthLines * i); //dibujamos las lineas horizontales
                    gc.strokeLine(widthLines * j, weigth2, widthLines * j, 0);//dibujamos las lineas verticales

                }
            }
            pane.getChildren().add(root2);//root
        } catch (NumberFormatException e) {

        }
    }

    /*Este método es para añadirle color al canvas 1*/
    public void canvas1Color() {
        try {
            gc = canvas.getGraphicsContext2D();
            gc.setFill(Color.BURLYWOOD);//escogemos color
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        } catch (Exception e) {
        }
    }

    /*Este método es para añadirle color al canvas 2*/
    public void canvas2Color() {
        try {
            ggc = canvas2.getGraphicsContext2D();
            ggc.setFill(Color.BURLYWOOD);
            ggc.fillRect(0, 0, canvas2.getWidth(), canvas2.getHeight());
        } catch (Exception e) {
        }
    }


    /*Escogencia  de colores en el comboBox, para dibujar las líneas */
    public void chooseColor(GraphicsContext c) {
        try {
            if (combo.getSelectionModel().getSelectedItem().equals("WHITE")) {
                c.setStroke(Color.WHITE);
            }
            if (combo.getSelectionModel().getSelectedItem().equals("BLACK")) {
                c.setStroke(Color.BLACK);
            }
            if (combo.getSelectionModel().getSelectedItem().equals("BLUE")) {
                c.setStroke(Color.BLUE);
            }
            if (combo.getSelectionModel().getSelectedItem().equals("FUCHSIA")) {
                c.setStroke(Color.FUCHSIA);
            }
            if (combo.getSelectionModel().getSelectedItem().equals("GREEN")) {
                c.setStroke(Color.GREEN);
            }
            if (combo.getSelectionModel().getSelectedItem().equals("SALMON")) {
                c.setStroke(Color.SALMON);
            }
            if (combo.getSelectionModel().getSelectedItem().equals("DARKBLUE")) {
                c.setStroke(Color.DARKBLUE);
            }
            if (combo.getSelectionModel().getSelectedItem().equals("CORAL")) {
                c.setStroke(Color.CORAL);
            }
            if (combo.getSelectionModel().getSelectedItem().equals("BLUEVIOLET")) {
                c.setStroke(Color.BLUEVIOLET);
            }
            if (combo.getSelectionModel().getSelectedItem().equals("SIENNA")) {
                c.setStroke(Color.SIENNA);
            }
        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("Incorrect date");
            alert.getDialogPane().setPrefSize(200, 100);
            alert.showAndWait();

        }
    }

    /*Este metodo verifica si el usuario deja algun campo de texto vacio, en caso de hacerlo, lanza un mensaje de advertencia
    sino, seleccionamos la imagen deseada*/
    public boolean validateField(String width, String combo) throws IOException {
        boolean input = false;
        int x = Integer.parseInt(width);
        try {

            if (width.equals("") || combo.equals("")) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Date null");
                alert.getDialogPane().setPrefSize(200, 100);
                alert.showAndWait();
                input = false;
            } else {
                gc = canvas.getGraphicsContext2D(); //instanciamos el graphics contects
                ggc = canvas2.getGraphicsContext2D();

                searchMosaik();
                drawShapesCanvas1(gc, x);
                drawShapesCanvas2(ggc, x);
                input = true;
            }

        } catch (Exception ex) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("INCORREC DATE");
            alert.getDialogPane().setPrefSize(200, 100);
            alert.showAndWait();
        }

        return input;
    }

    /*Este popMenu, contiene varios menuItem, como copiar,cortar y pegar, los cuales son la base de nuestro proyecto, */
    public void popMenu(int x) {
        try {
            root = new Group();
            cm = new ContextMenu();

            Image cutt = new Image("/images/cut.png");
            this.cut = new ImageView(cutt);

            Image copyy = new Image("/images/copy.png");
            this.copy = new ImageView(copyy);

            Image pasteee = new Image("/images/paste.png");
            this.paste2 = new ImageView(pasteee);

            Image de = new Image("/images/delete.png");
            this.delete = new ImageView(de);

            menu1 = new MenuItem("Cut", cut);
            menu2 = new MenuItem("Copy ", copy);
            menu4 = new MenuItem("Paste ", paste2);
            menu3 = new MenuItem("Exit", delete);
            cm.getItems().add(menu1);
            cm.getItems().add(menu2);
            cm.getItems().add(menu4);
            cm.getItems().add(menu3);

            canvas.setOnMouseClicked((MouseEvent mouseEvent) -> {

                ejeX = (int) mouseEvent.getX();
                ejeY = (int) mouseEvent.getY();

                ///EVENTOS
                canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ee) -> {
                    if (ee.getButton() == MouseButton.SECONDARY) {
                        /* Cada menuItem se muestra en pantalla*/
                        canvas.setOnContextMenuRequested(e -> cm.show(canvas, ee.getScreenX(), ee.getScreenY()));
                    } else if (ee.getButton() == MouseButton.PRIMARY) {

                    }

                });
                //EVENTOS, CUANDO SE SELECCIONE EN CUALQUIER MENUITEM//
            });
            menu1.setOnAction((ActionEvent event) -> {
                      /*desactivamos el botòn de cortar, solo se muestra */
                menu1.setDisable(true);
            });
            menu2.setOnAction((ActionEvent event) -> {

                try {

                    copyImage(x);
                } catch (Exception e) {
                    int widthLines = Integer.parseInt(fieldWidth.getText());
                    //dividimos el ejeX y ejeY  por el tamano de las lineas
                    int splitX = ejeX / widthLines;
                    int splitY = ejeY / widthLines;
                    //Les hacemos un substring para obtener solo un numero
                    String temp = String.valueOf(splitX);
                    String temp2 = String.valueOf(splitY);
                    String subs = temp.substring(0, 1);
                    String subs2 = temp2.substring(0, 1);
                     //pasamos el valor generado a entero
                    int valorX = Integer.parseInt(subs);
                    int valorY = Integer.parseInt(subs2);
                    //multiplicamos ese valor, por el tamano de las lineas
                    int operationX = valorX * widthLines;
                    int operationY = valorY * widthLines;
                    gc.setStroke(Color.RED);//anadimos color a el tamano de las lineas
                    gc.strokeRoundRect(operationX, operationY, widthLines, widthLines, 10, 10);//para que se repinten los pixeles de un color en especifico
                }

            });
            menu4.setOnAction((ActionEvent event) -> {
                menu4.setDisable(true);
            });

            menu3.setOnAction((ActionEvent event) -> {//salir

            });
        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert");
            alert.setHeaderText("Incorrect date");
            alert.getDialogPane().setPrefSize(200, 100);
            alert.showAndWait();
        }

    }

    /*Este método copia los pixeles de la imagen, y los almacenamos en writable*/
    public void copyImage(int widthLines) {
        try {

            gc = canvas.getGraphicsContext2D();

            gc.setStroke(Color.SPRINGGREEN);

            if (ejeX < widthLines && ejeY < widthLines) {

                gc.strokeRoundRect(0, 0, widthLines, widthLines, 10, 10);
                this.writableImage = new WritableImage(this.pixel, 0, 0, widthLines, widthLines); //Lee los pixeles (imagen, xInicio, yInicio, xFin, yFin)

            } else if (ejeX < widthLines) {//|

                int splitY = ejeY / widthLines;
                int splitX = ejeX / widthLines;
                String temp2 = String.valueOf(splitY);
                String temp = String.valueOf(splitX);
                String subs2 = temp2.substring(0, 1);
                String sub = temp.substring(0, 1);
                int valorY = Integer.parseInt(subs2);
                int valorX = Integer.parseInt(sub);
                int operationY = valorY * widthLines;
                int operationX = valorX * widthLines;

                this.writableImage = new WritableImage(this.pixel, 0, operationY, widthLines, widthLines);
                gc.strokeRoundRect(operationX, operationY, widthLines, widthLines, 10, 10);
            } else if (ejeY < widthLines) {
                int splitX = ejeX / widthLines;
                int splitY = ejeY / widthLines;
                String temp = String.valueOf(splitX);
                String temp2 = String.valueOf(splitY);
                String subs = temp.substring(0, 1);
                String subs2 = temp2.substring(0, 1);

                int valorX = Integer.parseInt(subs);
                int valorY = Integer.parseInt(subs2); 
                int operationX = valorX * widthLines;
                int operationY = valorY * widthLines;
                this.writableImage = new WritableImage(pixel, operationX, 0, widthLines, widthLines);
                gc.strokeRoundRect(operationX, operationY, widthLines, widthLines, 10, 10);

                //Lee los pixeles (imagen, xInicio, yInicio, xFin, yFin)
            } else {

                int splitX = ejeX / widthLines;
                int splitY = ejeY / widthLines;
                String temp = String.valueOf(splitX);
                String temp2 = String.valueOf(splitY);
                String subs = temp.substring(0, 1);
                String subs2 = temp2.substring(0, 1);

                int valorX = Integer.parseInt(subs);
                int valorY = Integer.parseInt(subs2);

                int operationX = valorX * widthLines;
                int operationY = valorY * widthLines;
                //Lee los pixeles (imagen, xInicio, yInicio, xFin, yFin)
                this.writableImage = new WritableImage(this.pixel, operationX, operationY, widthLines, widthLines);
                gc.strokeRoundRect(operationX, operationY, widthLines, widthLines, 10, 10);

            }

        } catch (NumberFormatException e) {

        }
    }//end method

    /*Este popMenu2, es para el canvas 2, cada popMenu tiene opciones diferentes, como pegar, rotar, voltear, y eliminar */
    public void popMenu2(int width) {
        try {
            root2 = new Group();
            contextMenu2 = new ContextMenu();

            //paste
            Image pastee = new Image("/images/paste.png");
            paste = new ImageView(pastee);
            //rotate
            Image rotateLL = new Image("/images/rotateIz.png");
            this.rotateL = new ImageView(rotateLL);

            Image rotateRR = new Image("/images/rotateDe.png");
            this.rotateR = new ImageView(rotateRR);

            //flip
            Image flipLL = new Image("/images/volterIz.png");
            this.flipL = new ImageView(flipLL);

            Image flipRR = new Image("/images/voltear.png");
            this.flipR = new ImageView(flipRR);

            //delete
            Image deletee = new Image("/images/delete.png");
            this.delete = new ImageView(deletee);

            menu33 = new MenuItem("Paste ", paste);
            menu44 = new MenuItem("Flip    ", flipL);
            menu5 = new MenuItem("Rotate  ", rotateR);
            menu6 = new MenuItem("Flip    ", flipR);
          
            menu8 = new MenuItem("Delete", delete);

            contextMenu2.getItems().add(menu33);
            contextMenu2.getItems().add(menu44);
            contextMenu2.getItems().add(menu5);
            contextMenu2.getItems().add(menu6);
           
            contextMenu2.getItems().add(menu8);

            canvas2.setOnMouseClicked((MouseEvent mouseEvent) -> {
                ejeY2 = (int) mouseEvent.getY();
                ejeX2 = (int) mouseEvent.getX();

                canvas2.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ex) -> {
                    if (ex.getButton() == MouseButton.SECONDARY) {
                        /*Para que cada MenuItem se muestre al darle doble click*/
                        canvas2.setOnContextMenuRequested(e -> contextMenu2.show(canvas2, ex.getScreenX(), ex.getScreenY()));

                    } else if (ex.getButton() == MouseButton.PRIMARY) {
                        /*Para que cada MenuItem se muestre al darle doble click*/

                    }

                });
            });
            /*EVENTOS*/
            //cuando presionamos sobre alguna de las opciones del menuItem

            menu33.setOnAction((ActionEvent event) -> {
                try {
                    pasteImage(width);
                } catch (Exception e) {

                }
            });
            menu44.setOnAction((ActionEvent event) -> {
                try {
                    flipHo(width);
                } catch (Exception e) {

                }

            });
            menu5.setOnAction((ActionEvent event) -> {
                try {
                    rotate90(width);

                } catch (Exception e) {

                }

            });

            menu8.setOnAction((ActionEvent event) -> {
                try {
                    deleteImage(width);
                } catch (Exception e) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Alert");
                    alert.setHeaderText("Copy an image, to be able to delete it");
                    alert.getDialogPane().setPrefSize(200, 100);
                    alert.showAndWait();
                }

            });

        } catch (Exception e) {

        }

    }

    /*pega los pixeles al canvas 2*/
    public void pasteImage(int widthLines) {
        try {
            int slipX = ejeX2 / widthLines;

            String temp = String.valueOf(slipX);
            String subs = temp.substring(0, 1);
            int ejeX2Entero = Integer.parseInt(subs);
            int operationX2 = ejeX2Entero * widthLines;

            int slipY = ejeY2 / widthLines;
            String temp2 = String.valueOf(slipY);
            String subs2 = temp2.substring(0, 1);
            int ejeY2Enterp = Integer.parseInt(subs2);
            int operationY2 = ejeY2Enterp * widthLines;

            ggc.drawImage(writableImage, operationX2, operationY2, widthLines, widthLines);
        } catch (Exception e) {
        }
    }

    /*Este metodo  rota una bloque de una imagen */
    public void rotate90(int widthLines) {
        try {
            
            int slipX = ejeX2 / widthLines;

            String temp = String.valueOf(slipX);
            String subs = temp.substring(0, 1);
            int ejeX2Entero = Integer.parseInt(subs);
            int operationX2 = ejeX2Entero * widthLines;

            int slipY = ejeY2 / widthLines;
            String temp2 = String.valueOf(slipY);
            String subs2 = temp2.substring(0, 1);
            int ejeY2Entero = Integer.parseInt(subs2);
            int operationY2 = ejeY2Entero * widthLines;

            this.imageView = new ImageView(this.writableImage);
            this.imageView.setRotate(imageView.getRotate() + 90); //rota la imagen 90 gredos sentido del reloj 
            //obtienen la imagen modificada y la sobreescribe con la original
            this.image = imageView.snapshot(null, writableImage);

            imageView.setFitHeight(widthLines);
            imageView.setFitWidth(widthLines);

            ggc.drawImage(writableImage, operationX2, operationY2, widthLines, widthLines);
        } catch (Exception e) {
        }

    }

    /*Este metodo elimina un bloque de una imagen*/
    public void deleteImage(int widthLines) {
        try {
            int splitX = ejeX2 / widthLines;

            String temp = String.valueOf(splitX);
            String subs = temp.substring(0, 1);
            int ejeX2Entero = Integer.parseInt(subs);
            int operationX2 = ejeX2Entero * widthLines;

            int splitY = ejeY2 / widthLines;
            String temp2 = String.valueOf(splitY);
            String subs2 = temp2.substring(0, 1);
            int ejeY2Entero = Integer.parseInt(subs2);
            int operationY2 = ejeY2Entero * widthLines;

            ggc = canvas2.getGraphicsContext2D();
            chooseColor(ggc);
           // ggc.setLineWidth(3);

            this.imageView = new ImageView(this.writableImage);
            this.image = imageView.snapshot(null, writableImage);

            ggc.clearRect(operationX2, operationY2, widthLines, widthLines);
            ggc.setFill(Color.BURLYWOOD);

            ggc.fillRect(operationX2, operationY2, widthLines, widthLines);
        } catch (Exception e) {
        }
    }

    /*Este metodo voltea horizontalmente la imagen*/
    public void flipHo(int widthLines) {
        try {
            int splitX = ejeX2 / widthLines;

            String temp = String.valueOf(splitX);
            String subs = temp.substring(0, 1);
            int ejeX2Entero = Integer.parseInt(subs);
            int operationX2 = ejeX2Entero * widthLines;

            int splitY = ejeY2 / widthLines;
            String temp2 = String.valueOf(splitY);
            String subs2 = temp2.substring(0, 1);
            int ejeY2Entero = Integer.parseInt(subs2);
            int operationY2 = ejeY2Entero * widthLines;

            ggc = canvas2.getGraphicsContext2D();//Inicializo el graphics contexts
            this.imageView = new ImageView(this.writableImage);//Inicializo ImageView, le añado  writable, quien almacena nuestros pixeles

            imageView.setFitHeight(widthLines);//le doy un nuevo alto a la imagen , el introducido en pantalla
            imageView.setFitWidth(widthLines);//le doy un nuevo ancho a la imagen, el introducido en pantallada

            ggc.save();//guardamos

            ggc.translate(widthLines + (operationX2) * 2, (operationY2));
            ggc.scale(-1, 1);//para que la imagen se voltee, que se situe en el plano cartesiano izquierdo, de los negativos

            ggc.drawImage(this.writableImage, operationX2, operationY2);//pintamos la imagen
            ggc.restore();//repint
        } catch (Exception e) {
        }

    }

    /*Este metodo limpia el canvas, y le agregamos el color inicial  */
    public void cleanCanvaas1() {
        try {
            gc = canvas.getGraphicsContext2D(); //instanciamos el graphics contects

            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());//Nos permite limpiar el canvas

            canvas1Color();
        } catch (Exception e) {
        }
    }

    /*Este metodo no permite limpiar el canvas, y de paso, le volvemos añadir el color inicial*/
    public void cleanCanvaas2() {
        try {
            ggc = canvas2.getGraphicsContext2D(); //instanciamos el graphics contects
            ggc.clearRect(0, 0, canvas2.getWidth(), canvas2.getHeight());
            canvas2Color();
        } catch (Exception e) {
        }
    }

    /*EVENTOS*/
    @Override
    public void handle(ActionEvent e) {
        try {

            if ((Button) e.getSource() == search) {

                String wid = fieldWidth.getText();

                String comb = String.valueOf(combo.getSelectionModel().getSelectedItem().toString());
                validateField(wid, comb);

            } else if ((Button) e.getSource() == cleanCanvas1) {
                cleanCanvaas1();
            } else if ((Button) e.getSource() == save) {

                saveMosaik();

            } else if ((Button) e.getSource() == cleanCanvas2) {
                cleanCanvaas2();
            } else if ((Button) e.getSource() == saveProject) {

                saveAll();
             

            } else if ((Button) e.getSource() == openProject) {
                openProjectCanvas2();

            } else if ((Button) e.getSource() == openProject2) {
                openProjectCanvas1();

            }

        } catch (Exception es) {

        }
    }//handle

}//class
