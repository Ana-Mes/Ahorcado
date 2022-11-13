package dad.ahorcado.palabras;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

public class PalabrasController implements Initializable {

	// model

	private ListProperty<String> palabras = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<String> palabrasSorted = new SimpleListProperty<>(palabras.sorted());
	private StringProperty palabraSeleccionada = new SimpleStringProperty();

	@FXML
	private Button nuevoButton;

	@FXML
	private ListView<String> palabrasList;

	@FXML
	private Button quitarButton;

	@FXML
	private BorderPane view;

	public PalabrasController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PalabrasView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Ha ocurrido un error");
			alert.setContentText(e.getLocalizedMessage());
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.showAndWait();
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// bindings

		palabrasList.itemsProperty().bind(palabrasSorted);
		palabraSeleccionada.bind(palabrasList.getSelectionModel().selectedItemProperty());
		quitarButton.disableProperty().bind(palabraSeleccionada.isNull());

	}

	public BorderPane getView() {
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

	@FXML
	void onNuevoAction(ActionEvent event) {

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Nueva palabra");
		dialog.setHeaderText("Añadir una nueva palabra");
		dialog.setContentText("Palabra:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent() && !palabras.contains(result.get().trim().toUpperCase())) {
			palabras.add(result.get().trim().toUpperCase());
		}

	}

	@FXML
	void onQuitarAction(ActionEvent event) {
		palabras.remove(palabraSeleccionada.get());
	}

}
