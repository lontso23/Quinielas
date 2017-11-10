package scraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import source.ListaPartidos;
import source.Partido;

public class Scraping {

	private static Scraping miScrap = new Scraping();
	final String url = "http://www.mundodeportivo.com/servicios/quiniela/";
	
	private Scraping(){
		
		
		
	}
	
	
	public static Scraping getScraping(){
		return miScrap;
	}
	
	
	public void cargarDatos(){

    	
    	Document doc = new Document(url);
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//Obtenemos los primeros 14 partidos
    	Elements matches = doc.select("article[class=content-ticket structure]");
    	for (Element match: matches) {
			String partido = match.getElementsByClass("bg-name").text();
			//System.out.println(partido);
			Elements probs = match.getElementsByClass("row-cell3 bg-lightpink item1");
			 String[] split = new String[3];
				for (Element prob: probs) {
					String p = prob.getElementsByClass("value").text();
					split = p.split(" ");
					//System.out.println(split[0]+" "+split[1]+" "+split[2]);
				}
				ArrayList<String> res = new ArrayList<String>();
				ArrayList<Integer> prob = new ArrayList<Integer>();
				prob.add(Integer.valueOf(split[0]));
				prob.add(Integer.valueOf(split[1]));
				prob.add(Integer.valueOf(split[2]));
				Partido p = new Partido(partido, res, prob);
				ListaPartidos.getPartidos().add(p);
			}			
			//Pleno al 15
				Elements pleno = doc.select("article[class=content-ticket row-last structure]");
				for(Element el : pleno){
				String equipo = el.getElementsByClass("bg-name").text();
				Elements probsQun = el.getElementsByClass("row-last3 value-last bg-lightpink");	
				//System.out.println( equipo); 
				String[] split = new String[4];
					for (Element pquin: probsQun) {
						String p = pquin.getElementsByClass("value").text();
						split = p.split(" ");
					}
					ArrayList<String> res = new ArrayList<String>();
					ArrayList<Integer> prob = new ArrayList<Integer>();
					prob.add(Integer.valueOf(split[0]));
					prob.add(Integer.valueOf(split[1]));
					prob.add(Integer.valueOf(split[2]));
					prob.add(Integer.valueOf(split[3]));
					Partido p = new Partido(equipo, res, prob);
					ListaPartidos.getPartidos().add(p);
				}
	}
	
	public static void main(String args[]){
		Scraping.getScraping().cargarDatos();
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca cuantas apuestas quiere hacer: ");
		String apuesta = sc.nextLine();
		//System.out.println(Double.parseDouble(apuesta));
		ListaPartidos.getPartidos().setApuesta(Integer.valueOf(apuesta));
		ListaPartidos.getPartidos().hacerQuiniela();
		for( Partido p : ListaPartidos.getPartidos().getResultados()){
			System.out.println(p.getNombre());
			for(String r : p.getResultados()){
				System.out.println(" -----> "+ r);
			}
		}
	}
	
}
