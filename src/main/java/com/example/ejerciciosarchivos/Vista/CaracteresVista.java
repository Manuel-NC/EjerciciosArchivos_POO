package com.example.ejerciciosarchivos.Vista;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CaracteresVista {
    public BorderPane root = new BorderPane();
    public MenuBar menuBar = new MenuBar();
    public MenuItem item1 = new MenuItem("1. Editor de Notas");
    public MenuItem item2 = new MenuItem("2. Estadisticas de Texto");
    public MenuItem item3 = new MenuItem("3. Visualizador CSV");

    // Componentes del ejercicio 1
    public TextArea txtAreaEditor = new TextArea();
    public Button btnCargarNota = new Button("Abrir Archivo");
    public Button btnGuardarNota = new Button("Guardar Archivo");

    // Componentes del ejercicio 2
    public Button btnCalcularEstadisticas = new Button("Seleccionar archivo .txt");
    public TextArea txtResultadoEstadisticas = new TextArea();

    // Componentes del ejercicio 3
    public Button btnCargarCSV = new Button("Seleccionar archivo .csv");
    public TableView<String[]> tablaCSV = new TableView<>();

    public void inicializarComponentes(Stage stage) {
        Menu menuCaracteres = new Menu("Flujos de Caracteres");
        menuCaracteres.getItems().addAll(item1, item2, item3);
        menuBar.getMenus().add(menuCaracteres);

        root.setTop(menuBar);

        Label lblInicio = new Label("Selecciona una opcion en el menu.");
        lblInicio.setStyle("-fx-text-alignment: center; -fx-font-size: 14px;");
        root.setCenter(lblInicio);

        stage.setScene(new Scene(root, 650, 450));
        stage.setTitle("Flujos de Caracteres");
    }

    // Editor de notas
    public void mostrarVistaEditor() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));

        txtAreaEditor.setPromptText("Escribe tus notas aqui:");
        txtAreaEditor.setPrefHeight(300);

        HBox botones = new HBox(10);
        botones.getChildren().addAll(btnCargarNota, btnGuardarNota);

        layout.getChildren().addAll(new Label("1. Editor de Notas"), txtAreaEditor, botones);
        root.setCenter(layout);
    }

    // Estadisticas de Texto
    public void mostrarVistaEstadisticas() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));

        // Configuramos el TextArea para que no se pueda editar y tenga letra de consola
        txtResultadoEstadisticas.setEditable(false);
        txtResultadoEstadisticas.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 14px;");
        txtResultadoEstadisticas.setPrefHeight(200);
        txtResultadoEstadisticas.clear();

        layout.getChildren().addAll(
                new Label("2. Estadisticas de Texto"),
                btnCalcularEstadisticas,
                txtResultadoEstadisticas
        );
        root.setCenter(layout);
    }

    // Visualizador CSV
    public void mostrarVistaCSV() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));

        tablaCSV.getColumns().clear();
        tablaCSV.getItems().clear();
        tablaCSV.setPrefHeight(300);

        layout.getChildren().addAll(new Label("3. Visualizador CSV"), btnCargarCSV, tablaCSV);
        root.setCenter(layout);
    }
}
