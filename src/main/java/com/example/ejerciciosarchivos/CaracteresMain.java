package com.example.ejerciciosarchivos;

import com.example.ejerciciosarchivos.Controlador.CaracteresControlador;
import com.example.ejerciciosarchivos.Vista.CaracteresVista;
import javafx.application.Application;
import javafx.stage.Stage;

public class CaracteresMain extends Application {

    @Override
    public void start(Stage stage) {
        // Instanciamos la Vista de Caracteres
        CaracteresVista vista = new CaracteresVista();

        // Construimos la interfaz visual
        vista.inicializarComponentes(stage);

        // Instanciamos el controlador, uniendo la vista y el stage
        new CaracteresControlador(vista, stage);

        // 4. Mostramos la ventana
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
