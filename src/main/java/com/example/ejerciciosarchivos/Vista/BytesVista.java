package com.example.ejerciciosarchivos.Vista;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BytesVista {
    // Componentes principales para el controlador
    public BorderPane panelPrincipal = new BorderPane();
    public MenuBar menuBar = new MenuBar();
    public MenuItem item4 = new MenuItem("4. Clonador de Imagenes");
    public MenuItem item5 = new MenuItem("5. Encriptador XOR");
    public MenuItem item6 = new MenuItem("6. Identificacion de Tipos");

    // Componentes del ejercicio 4
    public Button btnClonar = new Button("Seleccionar y Clonar Imagen");
    public ProgressBar progressBar = new ProgressBar(0);

    // Componentes del ejercicio 5
    public TextField txtClave = new TextField();
    public Button btnProcesarXOR = new Button("Seleccionar archivo y aplicar mascara");

    // Componentes del ejercicio 6
    public Button btnIdentificar = new Button("Seleccionar archivo binario");
    public Label lblResultado = new Label("Tipo de archivo detectado: ");

    // Metodo para hacer la ventana
    public void inicializarComponentes(Stage stage) {
        // Hacemos el menu
        Menu menuBytes = new Menu("Flujos de Bytes");
        menuBytes.getItems().addAll(item4, item5, item6);
        menuBar.getMenus().add(menuBytes);

        panelPrincipal.setTop(menuBar);
        panelPrincipal.setCenter(new Label("Selecciona una opcion del menu."));

        stage.setScene(new Scene(panelPrincipal, 550, 400));
        stage.setTitle("Flujos de Bytes");
    }

    // Clonador imagenes
    public void mostrarVistaClonador() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        progressBar.setPrefWidth(300);
        progressBar.setProgress(0); // Reiniciar barra

        layout.getChildren().addAll(new Label("4. Clonador de Imagenes"), btnClonar, progressBar);
        panelPrincipal.setCenter(layout);
    }

    // Mascara XOR
    public void mostrarVistaXOR() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        txtClave.setPromptText("Escribe la clave numerica:");
        txtClave.setMaxWidth(250);
        txtClave.clear();

        layout.getChildren().addAll(new Label("5. Encriptador / Desencriptador XOR"), txtClave, btnProcesarXOR);
        panelPrincipal.setCenter(layout);
    }

    // Identificador formato
    public void mostrarVistaIdentificador() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        lblResultado.setStyle("-font-weight: bold; -fx-font-size: 14px;");
        lblResultado.setText("Tipo de archivo detectado: ");

        layout.getChildren().addAll(new Label("6. Identificacion de Tipos por Firma Hex"), btnIdentificar, lblResultado);
        panelPrincipal.setCenter(layout);
    }
}
