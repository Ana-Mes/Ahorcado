package dad.ahorcado.partida;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PartidaModel {

	private ListProperty<String> palabras = new SimpleListProperty<>(FXCollections.observableArrayList());
	private StringProperty palabraRandom = new SimpleStringProperty();
	private StringProperty letrasDescubiertas = new SimpleStringProperty();
	private IntegerProperty puntos = new SimpleIntegerProperty();
	private ListProperty<Jugador> jugadoresList = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ObjectProperty<Jugador> jugador = new SimpleObjectProperty<>();

	private int posicionJugador;
	private int fallos = 0;
	private String palabra = "";

	
	public int getPosicionJugador() {
		return posicionJugador;
	}
	public void setPosicionJugador(int posicionJugador) {
		this.posicionJugador = posicionJugador;
	}
	
	public int getFallos() {
		return fallos;
	}
	public void setFallos(int fallos) {
		this.fallos = fallos;
	}
	public String getPalabra() {
		return palabra;
	}
	public void setPalabra(String palabra) {
		this.palabra = palabra;
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
	
	public final StringProperty palabraRandomProperty() {
		return this.palabraRandom;
	}
	
	public final String getPalabraRandom() {
		return this.palabraRandomProperty().get();
	}
	
	public final void setPalabraRandom(final String palabraRandom) {
		this.palabraRandomProperty().set(palabraRandom);
	}
	
	public final StringProperty letrasDescubiertasProperty() {
		return this.letrasDescubiertas;
	}
	
	public final String getLetrasDescubiertas() {
		return this.letrasDescubiertasProperty().get();
	}
	
	public final void setLetrasDescubiertas(final String letrasDescubiertas) {
		this.letrasDescubiertasProperty().set(letrasDescubiertas);
	}
	public final IntegerProperty puntosProperty() {
		return this.puntos;
	}
	
	public final int getPuntos() {
		return this.puntosProperty().get();
	}
	
	public final void setPuntos(final int puntos) {
		this.puntosProperty().set(puntos);
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
	public final ObjectProperty<Jugador> jugadorProperty() {
		return this.jugador;
	}
	
	public final Jugador getJugador() {
		return this.jugadorProperty().get();
	}
	
	public final void setJugador(final Jugador jugador) {
		this.jugadorProperty().set(jugador);
	}
		
}
