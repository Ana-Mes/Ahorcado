package dad.ahorcado.puntos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.ahorcado.AhorcadoApp;
import dad.ahorcado.partida.Jugador;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

public class PuntosController implements Initializable {
	
	// model
	
	private ListProperty<Jugador> jugadoresList = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Jugador> jugadoresListSorted = new SimpleListProperty<>(jugadoresList.sorted((o1, o2) -> o2.getPuntuacion().compareTo(o1.getPuntuacion())));
	
	// view

	@FXML
    private BorderPane puntosPane;

    @FXML
    private ListView<Jugador> puntosView;
	
    public PuntosController() {
    	
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PuntosView.fxml"));
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
		
		puntosView.itemsProperty().bind(jugadoresListSorted);
	
	}
	
	public BorderPane getView() {
		return puntosPane;
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
