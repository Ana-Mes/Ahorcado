package dad.ahorcado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import dad.ahorcado.partida.Jugador;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AhorcadoApp extends Application {

	public static Stage primaryStage;

	private RootController rootController;
	
	private Datos datos = new Datos();
	
	public static final String palabrasURL = "ficheros/palabras.txt";
	public static final String jugadoresURL = "ficheros/jugadores.csv";
	
	Random random = new Random();

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		rootController = new RootController(datos.cargarPalabras(), datos.cargarJugadores());

		AhorcadoApp.primaryStage = primaryStage;

		primaryStage.setTitle("Ahorcado");
		primaryStage.setScene(new Scene(rootController.getView()));
		primaryStage.show();

	}
	
	@Override
	public void stop() throws Exception {
		super.stop();
		
		// save data
		
		guardarPalabras();
		guardarJugadores();
		
	}
	
	public void guardarPalabras() {

		try {
			FileOutputStream file = new FileOutputStream(palabrasURL);
			OutputStreamWriter salida = new OutputStreamWriter(file, StandardCharsets.UTF_8);
			BufferedWriter writer = new BufferedWriter(salida);
			
			for (String palabra : rootController.getPalabras()) {
				writer.write(palabra.toUpperCase());
				writer.newLine();
			}
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void guardarJugadores() {
		try {
			FileOutputStream file = new FileOutputStream(jugadoresURL);
			OutputStreamWriter salida = new OutputStreamWriter(file, StandardCharsets.UTF_8);
			BufferedWriter writer = new BufferedWriter(salida);
			
			for (Jugador jugador : rootController.getJugadoresList()) {
				writer.write(jugador.getNombre()+","+jugador.getPuntuacion());
				writer.newLine();
			}
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
