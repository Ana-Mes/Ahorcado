package dad.ahorcado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dad.ahorcado.partida.Jugador;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class AhorcadoApp extends Application {

	public static Stage primaryStage;

	private RootController rootController;
	
	public static final String palabrasURL = "ficheros/palabras.txt";
	public static final String jugadoresURL = "ficheros/jugadores.csv";
	
	Random random = new Random();

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		rootController = new RootController(cargarPalabras(), cargarJugadores());

		AhorcadoApp.primaryStage = primaryStage;

		primaryStage.setTitle("Ahorcado");
		primaryStage.setScene(new Scene(rootController.getView()));
		primaryStage.show();

	}
	
	@Override
	public void stop(){
		try {
			super.stop();
			guardarPalabras();
			guardarJugadores();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Ha ocurrido un error");
			alert.setContentText(e.getLocalizedMessage());
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
			primaryStage.close();
			e.printStackTrace();	
		}
	}
	
	public List<String> cargarPalabras() throws Exception {
		List<String> listaPalabrasCargada = new ArrayList<>();
		try {

			FileReader file = new FileReader(palabrasURL);
			BufferedReader reader = new BufferedReader(file);
			String line;
			while ((line = reader.readLine()) != null) {

				listaPalabrasCargada.add(line.toUpperCase());

			}
			reader.close();

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Ha ocurrido un error");
			alert.setContentText(e.getLocalizedMessage());
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
			primaryStage.close();
			e.printStackTrace();
			throw e;
		}
		return listaPalabrasCargada;
	}
	
	public List<Jugador> cargarJugadores() throws Exception {
		List<Jugador> listaJugadoresCargada = new ArrayList<>();
		try {
			FileReader file = new FileReader(jugadoresURL);
			
			BufferedReader reader = new BufferedReader(file);
			String line;
			while((line = reader.readLine()) != null) {
				String[] jugador = line.split(",");
				listaJugadoresCargada.add(new Jugador(jugador[0], Integer.parseInt(jugador[1])) );
			}
			reader.close();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Ha ocurrido un error");
			alert.setContentText(e.getLocalizedMessage());
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
			primaryStage.close();
			e.printStackTrace();
			throw e;
		}
		return listaJugadoresCargada;
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
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Ha ocurrido un error");
			alert.setContentText(e.getLocalizedMessage());
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
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
			
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Ha ocurrido un error");
			alert.setContentText(e.getLocalizedMessage());
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
