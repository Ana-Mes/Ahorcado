package dad.ahorcado.puntos;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import dad.ahorcado.partida.Jugador;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
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
