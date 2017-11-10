package source;

import java.util.ArrayList;

public class ListaPartidos {
	
	private ArrayList<Partido> partidos = new ArrayList<Partido>();
	private static ListaPartidos milista = new ListaPartidos();
	private int numDobles=0;
	private int numTriples=0;
	private int apuesta;
	
	private ListaPartidos() {
		
	}
	
	public static ListaPartidos getPartidos(){
		return milista;
	}
	
	public void add(Partido p){
		partidos.add(p);
	}
	
	public ArrayList<Partido> getResultados(){
		return partidos;
	}
	
	
	
	public int getNumDobles() {
		return numDobles;
	}

	public void setNumDobles(int numDobles) {
		this.numDobles = numDobles;
	}

	public int getNumTriples() {
		return numTriples;
	}

	public void setNumTriples(int numTriples) {
		this.numTriples = numTriples;
	}

	public int getApuesta() {
		return apuesta;
	}

	public void setApuesta(int apuesta) {
		this.apuesta = apuesta;
	}

	public void hacerQuiniela(){
		for(int i=0; i<=13;i++){
			Partido act = partidos.get(i);
			ArrayList<Integer> probsAct = act.getProb();
			if( probsAct.get(0)>30 &&probsAct.get(0)<35 &&
					 probsAct.get(1)>30 &&probsAct.get(1)<35 &&
					 probsAct.get(2)>30 &&probsAct.get(2)<35){
				if(esPosible()){
					act.getResultados().add("1");
					act.getResultados().add("X");
					act.getResultados().add("2");
					numTriples++;
				}else comprobarDobles(act);
				
			}else{
				comprobarDobles(act);
				}
		}
		Partido pleno1 = partidos.get(14);
		Partido pleno2 = partidos.get(15);
		
		plenoAl15(pleno1, pleno2);
	}
	
	
	private void comprobarDobles(Partido act){
		String max = obtMax3(act.getProb().get(0), act.getProb().get(1), act.getProb().get(2));
		switch (max){
		case "1":
			if(act.getProb().get(0) < act.getProb().get(1)+act.getProb().get(2)){
				if(esPosible()){
					if (act.getProb().get(1)>act.getProb().get(2)){
						act.getResultados().add("1");
						act.getResultados().add("X");
						numDobles++;
					}else{
						act.getResultados().add("1");
						act.getResultados().add("2");
						numDobles++;
					}
				}else act.getResultados().add("1");
				
			}else act.getResultados().add("1");
			break;
		case "X":
			if(act.getProb().get(1) < act.getProb().get(0)+act.getProb().get(2)){
				if(esPosible()){
					if (act.getProb().get(0)>act.getProb().get(2)){
						act.getResultados().add("1");
						act.getResultados().add("X");
						numDobles++;
					}else{
						act.getResultados().add("X");
						act.getResultados().add("2");
						numDobles++;
					}
				}else act.getResultados().add("1");
				
			}else act.getResultados().add("X");
			break;
			
		case "2":
			if(act.getProb().get(2) < act.getProb().get(1)+act.getProb().get(0)){
				if(esPosible()){
					if (act.getProb().get(1)>act.getProb().get(0)){
						act.getResultados().add("1");
						act.getResultados().add("2");
						numDobles++;
					}else{
						act.getResultados().add("X");
						act.getResultados().add("2");
						numDobles++;
					}
				}else act.getResultados().add("2");
				
			}else act.getResultados().add("2");
			break;
		}
	}
	
	private boolean esPosible(){
		double res=0.75;
		res = Math.pow(2, numDobles)*Math.pow(3, numTriples);
		//res = res * 0.75;
		if (apuesta < res){
			return false;
		}else return true;
	}
	
	private String obtMax3(int uno, int x, int dos ){
		 if(uno > x)
	           if(uno>dos)return "1";
	           else return "2";
	        else if(x>dos)return "X";
	         else return "2";
	}
	
	
	private int obtMax4(ArrayList<Integer> prob){
		 int max = 0;
		 int ind = 0;
		 for(int i=0; i<prob.size();i++){
			 if(max<prob.get(i)){
				 max = prob.get(i);
				 ind = i;
			 }
		 }
		
		return ind;
	}
	
	private void plenoAl15(Partido pleno1, Partido pleno2){
		int res1 = obtMax4(pleno1.getProb());
		if(res1 == 3){
			pleno1.getResultados().add("M");
		}else{
			pleno1.getResultados().add(Integer.toString(res1));
		}
		
		int res2 = obtMax4(pleno2.getProb());
		if(res2 == 3){
			pleno2.getResultados().add("M");
		}else{
			pleno2.getResultados().add(Integer.toString(res2));
		}
	}
	
	

}
