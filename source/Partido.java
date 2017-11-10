package source;

import java.util.ArrayList;

public class Partido {
	
	private String nombre;
	private ArrayList<String> resultados;
	private ArrayList<Integer> prob;
	
	public Partido(String nombre, ArrayList<String> resultados, ArrayList<Integer> prob) {
		this.nombre = nombre;
		this.resultados = resultados;
		this.prob = prob;
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<String> getResultados() {
		return resultados;
	}

	public ArrayList<Integer> getProb() {
		return prob;
	}
	
	
	
	
}
