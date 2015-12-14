import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Lab3Help.*;

public class Lab3<E extends Comparable<? super E>> implements Path<E> {
	
	// Beräknar en kortaste väg (om det finns någon) mellan två noder i en graf utan negativa kantvikter.
	public void computePath(E from, E to) {
		;
	}
	
	// Ger en iterator med noderna längs vägen (om det finns någon), inklusive start- och målnoderna.
	// Om det inte finns någon väg ska iteratorn vara tom.
	public Iterator<E> getPath() {
		Iterator<E> i = null;
		return i;
	}
	
	// Ger vägens längd (om det finns någon).
	public int getPathLength() {
		return 0;
	}
	
	// Programmet ska ta fyra argument:
	// 1.En fil med hållplatser.
	// 2.En fil med linjer.
	// 3.Starthållplatsen.
	// 4.Ändhållplatsen.
	public static void main(String[] args) {
		
		ArrayList<Node> g = new ArrayList<Node>();        
		  Lab3File f = new Lab3File();
		  try {
			  List<BStop> stops = f.readStops("stops-gbg.txt");
			  for (int i=0; i < stops.size(); i++) {
				  g.add(new Node(stops.get(i)));
			  }
		  }
		  catch (Exception e) {
		  }
		
		  

	}
}

/** A class implementing Path could perhaps be used as follows:
  Lab3File f = new Lab3File();
  List<BStop>      stops = f.readStops("stops-gbg.txt");
  List<BLineTable> lines = f.readLines("lines-gbg.txt");
  Path<String> p = new MyPath(stops, lines);
  p.computePath("Chalmers","Angered");
  System.out.println("Distance: " + p.getPathLength());
  p.computePath("Chalmers","GuldHeden");
  System.out.println("Distance: " + p.getPathLength());
*/