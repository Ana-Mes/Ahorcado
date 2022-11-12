package dad.ahorcado;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dad.ahorcado.palabras.PalabrasController;
import dad.ahorcado.partida.Jugador;
import dad.ahorcado.partida.PartidaController;
import dad.ahorcado.puntos.PuntosController;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;

public class RootController implements Initializable {

	// model

	private ListProperty<String> palabras = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Jugador> jugadoresList = new SimpleListProperty<>(FXCollections.observableArrayList());

	// controllers

	private PalabrasController palabrasController = new PalabrasController();
	private PuntosController puntosController = new PuntosController();
	private PartidaController partidaController = new PartidaController();

	// view

	@FXML
	private Tab palabrasTab;

	@FXML
	private Tab partidaTab;

	@FXML
	private Tab puntuacionesTab;

	@FXML
	private TabPane view;

	public RootController(List<String> palabrasCargadas, List<Jugador> jugadoresCargados) {
		try {

			palabras.addAll(palabrasCargadas);
			jugadoresList.addAll(jugadoresCargados);
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
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

		palabrasTab.setContent(palabrasController.getView());
		puntuacionesTab.setContent(puntosController.getView());
		partidaTab.setContent(partidaController.getView());
		
		// bindings

		palabrasController.palabrasProperty().bind(palabras);
		partidaController.getPartidaModel().palabrasProperty().bind(palabras);
		partidaController.getPartidaModel().jugadoresListProperty().bindBidirectional(jugadoresList);
		puntosController.jugadoresListProperty().bind(jugadoresList);

		// primeras acciones
		
		partidaController.seleccionarPalabra();
		
	}

	public TabPane getView() {
		return view;
	}

	public final ListProperty<String> palabrasProperty() {
		return this.palabras;
	}

	public final ObservableList<String> getPalabras() {
		return this.palabrasProperty().get();
	}

	public final void setPalabras(final ObservableList<String> palabras) {
		this.palabrasProperty().set(palabras);
	}

	public final ListProperty<Jugador> jugadoresListProperty() {
		return this.jugadoresList;
	}

	public final ObservableList<Jugador> getJugadoresList() {
		return this.jugadoresListProperty().get();
	}

	public final void setJugadoresList(final ObservableList<Jugador> jugadoresList) {
		this.jugadoresListProperty().set(jugadoresList);
	}

}
