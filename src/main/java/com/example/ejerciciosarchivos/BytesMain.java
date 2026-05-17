package com.example.ejerciciosarchivos;

import com.example.ejerciciosarchivos.Controlador.BytesControlador;
import com.example.ejerciciosarchivos.Vista.BytesVista;
import javafx.application.Application;
import javafx.stage.Stage;

public class BytesMain extends Application {
    @Override
    public void start(Stage stage) {
        // Instanciamos la vista
        BytesVista vista = new BytesVista();

        // Se construye la interfaz principal
        vista.inicializarComponentes(stage);

        // 3. Instanciamos el controlador, uniendo la vista con el stage
        new BytesControlador(vista, stage);

        // 4. Mostramos la ventana
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
