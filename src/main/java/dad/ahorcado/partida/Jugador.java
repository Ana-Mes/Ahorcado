package dad.ahorcado.partida;

import java.util.Comparator;

public class Jugador implements Comparator<Jugador>{
	
	private String nombre;
	private int puntuacion;
	
	public Jugador(String nombre, int puntuacion) {
		this.nombre = nombre;
		this.puntuacion = puntuacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	public String toString() {
		return nombre+": "+puntuacion;
	}

	@Override
	public int compare(Jugador j1, Jugador j2) {
		return j2.getPuntuacion().compareTo(j1.getPuntuacion()) ;
	}

}
