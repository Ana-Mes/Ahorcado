package dad.ahorcado.partida;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import dad.ahorcado.AhorcadoApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

public class PartidaController implements Initializable {
	
	// model

	private PartidaModel partidaModel = new PartidaModel();
	private Random random = new Random();
	private static String letras = "";

	// view

	@FXML
	private ImageView ahorcadoImagen;

	@FXML
	private Label letraLabel;

	@FXML
	private Label palabraLabel;

	@FXML
	private Label puntosTextLabel;

	@FXML
	private Label puntuacionLabel;

	@FXML
	private TextField textField;

	@FXML
	private Button resolverBoton;

	@FXML
	private Button letraBoton;

	@FXML
	private BorderPane partidaView;

	public PartidaController() {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PartidaView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Ha ocurrido un error");
			alert.setContentText(e.getLocalizedMessage());
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
			AhorcadoApp.primaryStage.close();
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// bindings

		palabraLabel.textProperty().bind(partidaModel.palabraRandomProperty());
		letraLabel.textProperty().bind(partidaModel.letrasDescubiertasProperty());
		letraBoton.disableProperty().bind(textField.textProperty().isEmpty());
		resolverBoton.disableProperty().bind(textField.textProperty().isEmpty());
		puntuacionLabel.textProperty().bind(partidaModel.puntosProperty().asString());

	}

	public BorderPane getView() {
		return partidaView;
	}

	public void comprobarFallos() {
		if (partidaModel.getFallos() == 0) {
			ahorcadoImagen.setImage(new Image("/imagenes/1.png"));
		} else if (partidaModel.getFallos() == 1) {
			ahorcadoImagen.setImage(new Image("/imagenes/2.png"));
		} else if (partidaModel.getFallos() == 2) {
			ahorcadoImagen.setImage(new Image("/imagenes/3.png"));
		} else if (partidaModel.getFallos() == 3) {
			ahorcadoImagen.setImage(new Image("/imagenes/4.png"));
		} else if (partidaModel.getFallos() == 4) {
			ahorcadoImagen.setImage(new Image("/imagenes/5.png"));
		} else if (partidaModel.getFallos() == 5) {
			ahorcadoImagen.setImage(new Image("/imagenes/6.png"));
		} else if (partidaModel.getFallos() == 6) {
			ahorcadoImagen.setImage(new Image("/imagenes/7.png"));
		} else if (partidaModel.getFallos() == 7) {
			ahorcadoImagen.setImage(new Image("/imagenes/8.png"));
		} else if (partidaModel.getFallos() == 8) {
			ahorcadoImagen.setImage(new Image("/imagenes/9.png"));

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Fin");
			alert.setHeaderText("Partida perdida");
			alert.setContentText("Has perdido, pero puedes continuar jugando si lo deseas");
			alert.showAndWait();

			meterPuntuacion();
			seleccionarPalabra();
			partidaModel.setLetrasDescubiertas(null);
			letras = "";
			partidaModel.setFallos(0);
			comprobarFallos();
			
			
		}
	}

	public void seleccionarPalabra() {
		
		if(partidaModel.getPalabras().size() > 0) {
			int num = random.nextInt(partidaModel.getPalabras().size());
			partidaModel.setPalabra(partidaModel.palabrasProperty().valueAt(num).get().trim());
			partidaModel.setPalabra(quitarTildes(partidaModel.getPalabra()));
			partidaModel.setLetrasDescubiertas(null);
			letras = "";

			String palabraCodificada = "";
			for (int i = 0; i < partidaModel.palabrasProperty().valueAt(num).get().length(); i++) {
				if (partidaModel.palabrasProperty().valueAt(num).get().charAt(i) != ' ') {
					palabraCodificada += "_";
				} else {
					palabraCodificada += " ";
				}
			}

			partidaModel.setPalabraRandom(palabraCodificada);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Sin palabras");
			alert.setHeaderText("No hay ninguna palabra en la lista de palabras");
			alert.setContentText("Por favor, introduzca una palabra");
			alert.showAndWait();
			
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Nueva palabra");
			dialog.setHeaderText("Añadir una nueva palabra");
			dialog.setContentText("Palabra:");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent() && !partidaModel.getPalabras().contains(result.get().trim().toUpperCase())) {
				partidaModel.getPalabras().add(result.get().trim().toUpperCase());
			}
			seleccionarPalabra();
			
		}
		
	}

	public boolean palabraResuelta() {
		return !partidaModel.getPalabraRandom().contains("_");
	}

	public String quitarTildes(String cadena) {
		return cadena.replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U");
	}

	public PartidaModel getPartidaModel() {
		return partidaModel;
	}

	public void setPartidaModel(PartidaModel partidaModel) {
		this.partidaModel = partidaModel;
	}
	
	public void meterPuntuacion() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.initOwner(AhorcadoApp.primaryStage);
		dialog.setTitle("Guardar puntos");
		dialog.setHeaderText("Escribe tu nombre para guardar la puntuación");
		dialog.setContentText("Nombre:");
		Optional<String> name = dialog.showAndWait();

		if (name.isPresent() && !name.get().isBlank()) {
			int i = 0;
			if(partidaModel.getJugadoresList().size() > 0) {
				while (i < partidaModel.getJugadoresList().size()-1 && !partidaModel.getJugadoresList().get(i).getNombre().equals(name.get())) {
					i++;
				}
				if(!partidaModel.getJugadoresList().get(i).getNombre().equals(name.get())) {
					partidaModel.setJugador(new Jugador(name.get(), partidaModel.getPuntos()));
					partidaModel.getJugadoresList().add(partidaModel.getJugador());


				} else {
					partidaModel.setJugador(partidaModel.getJugadoresList().get(i));
					partidaModel.getJugador().setPuntuacion(partidaModel.getPuntos());
					partidaModel.getJugadoresList().remove(i);
					partidaModel.getJugadoresList().add(new Jugador(name.get(), partidaModel.getPuntos()));


				}
			} else {
				partidaModel.setJugador(new Jugador(name.get(), partidaModel.getPuntos()));
				partidaModel.getJugadoresList().add(partidaModel.getJugador());


			}
			
		}
		partidaModel.setPuntos(0);
	}

	@FXML
	void onLetraAction(ActionEvent event) {
		String nuevaPalabra = partidaModel.getPalabraRandom();
		char[] nuevaPalabraArray = nuevaPalabra.toCharArray();
		String letra = quitarTildes(textField.getText().trim().toUpperCase());

		if (letras.contains(letra)) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Letra existente");
			alert.setHeaderText("Ya has introducido la letra " + textField.getText().toUpperCase());
			alert.setContentText("Inténtalo con otra letra");
			alert.showAndWait();

		} else if (textField.getText().length() > 1) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Número incorrecto de letras");
			alert.setHeaderText("Has introducido más de una letra");
			alert.setContentText("Por favor, introduzca solamente una letra");
			alert.showAndWait();

		} else if(letra.equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("No hay ninguna letra");
			alert.setHeaderText("No ha introducido letras");
			alert.setContentText("Por favor, introduzca una letra");
			alert.showAndWait();
		}
		
		else {

			letras += letra;
			partidaModel.setLetrasDescubiertas(letras);

			if (partidaModel.getPalabra().contains(quitarTildes(letra))) {
				for (int i = 0; i < partidaModel.getPalabra().length(); i++) {
					if (letra.equals(partidaModel.getPalabra().charAt(i) + "")) {
						nuevaPalabraArray[i] = partidaModel.getPalabra().charAt(i);
						partidaModel.setPuntos(partidaModel.getPuntos() + 1);
					}
				}
				partidaModel.setPalabraRandom(String.valueOf(nuevaPalabraArray));
				if (palabraResuelta()) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Palabra acertada");
					alert.setHeaderText("Enhorabuena, has acertado la palabra");
					alert.setContentText("Ahora puedes intentarlo con otra palabra");
					alert.showAndWait();
					seleccionarPalabra();
				}
			} else {
				partidaModel.setFallos(partidaModel.getFallos() + 1);
			}
			comprobarFallos();
		}
		textField.setText(null);
	}

	@FXML
	void onResolverAction(ActionEvent event) {
		if (quitarTildes(textField.getText().trim().toUpperCase()).equals(partidaModel.getPalabra())) {
			for (int i = 0; i < partidaModel.getPalabraRandom().length(); i++) {
				if (partidaModel.getPalabraRandom().charAt(i) == '_') {
					partidaModel.setPuntos(partidaModel.getPuntos() + 1);
				}
			}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Palabra acertada");
			alert.setHeaderText("Enhorabuena, has acertado la palabra");
			alert.setContentText("Ahora puedes intentarlo con otra palabra");
			alert.showAndWait();
			seleccionarPalabra();
		} else {
			partidaModel.setFallos(partidaModel.getFallos() + 1);
		}
		comprobarFallos();
		textField.setText(null);
	}

}
