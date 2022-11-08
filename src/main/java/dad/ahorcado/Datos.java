package dad.ahorcado;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dad.ahorcado.partida.Jugador;

public class Datos {
	private String palabrasURL = "ficheros/palabras.txt";
	private final String jugadoresURL = "ficheros/jugadores.csv";
	
	public List<String> cargarPalabras() {
		List<String> listaPalabrasCargada = new ArrayList<>();
		try {

			FileReader file = new FileReader(palabrasURL);
			BufferedReader reader = new BufferedReader(file);
			String line;
			while ((line = reader.readLine()) != null) {

				listaPalabrasCargada.add(line.toUpperCase());

			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaPalabrasCargada;
	}
	
	public List<Jugador> cargarJugadores() {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaJugadoresCargada;
	}
}
