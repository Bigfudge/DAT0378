import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * ...
 */

public class Lab2 {

    /**
     * 
     */

    public static void trade(List<Bid> bids) {
    	
    	// K�RS EFTER VI HAR BUDLISTAN
    	// BUDLISTAN �R EN ARRAYLIST AV BIDS
    	// ArrayList<Bid> med namnet bids
    	
    	
    	// GENERERA FELMEDDELANDE VID F�RS�K AV ATT �NDRA BUD SOM EJ FINNS
    	
    	
    	
    	
    	// PRINTAR GENOMF�RDA AFF�RER
    	
    	
    	//Er prioritetsk�implementation m�ste vara s� generell att den kan anv�ndas b�de f�r k�park�n och f�r s�ljark�n.

    	//Om ni anv�nder Java ska prioritetsk�ns konstruktorer ta komparatorer som argument:

    	
    	  // public PriorityQueue(..., Comparator<? super E> comp, ...) {
    	  //    ...
    	  // }
    	
    	
    	// PRINTAR �TERST�ENDE BUD
    	
    	
    }    

    /**
     * Parses a bid.
     *
     * @param s The string that should be parsed.
     *
     * @throws MalformedBid If the bid cannot be parsed.
     */
    
    public static Bid parseBid(String s) throws MalformedBid {
        Matcher m = Pattern.compile(
                      "\\s*(\\S+)\\s+" +
                      "(?:(K|S)\\s+(\\d+)|(NS|NK)\\s+(\\d+)\\s+(\\d+))" +
                      "\\s*").matcher(s);

        if (m.matches()) {
            if (m.group(2) == null) {
            	// �NDRING AV BUD
                // m.group(1): The name of the buyer/seller.
                // m.group(4): NK or NS.
                // m.group(5): Old value.
                // m.group(6): New value.    	
                return new Bid(m.group(1), m.group(4), Integer.parseInt(m.group(6)), Integer.parseInt(m.group(5)));
            } else {
            	// NYTT BUD
                // m.group(1): The name of the buyer/seller.
                // m.group(2): K or S.
                // m.group(3): The value.
                return new Bid(m.group(1), m.group(2),Integer.parseInt(m.group(3)));
            }
        } else {
            throw new MalformedBid(s);
        }
    }

    /**
     * Parses line-separated bids from the given Readable thing.
     *
     * @param input The character stream that should be parsed.
     *
     * @throws MalformedBid If some bid couldn't be parsed.
     */

    public static List<Bid> parseBids(Readable input) throws MalformedBid {
        ArrayList<Bid> bids = new ArrayList<Bid>();
        Scanner s = new Scanner(input);

        while (s.hasNextLine()) {
            bids.add(parseBid(s.nextLine()));
        }

        return bids;
    }

    /**
     * Exception class for malformed bids.
     */

    public static class MalformedBid extends Exception {
        MalformedBid(String bid) {
            super("Malformed bid: " + bid + ".");
        }
    }

    /**
     * Prints usage info.
     */

    public static void usageInfo() {
        System.err.println("Usage: java Aktiehandel [<file>]");
        System.err.println("If no file is given, then input is " +
                           "read from standard input.");
    }

    /**
     * STARTAR PROGRAMMET MED ELLER UTAN FIL
     * 
     * MED: L�SER IN BUDLISTA FR�N FIL, SEDAN K�RS TRADE
     * UTAN: ANV�NDAREN F�R SKRIVA IN BUDLISTAN, SEDAN K�RS TRADE
     */

    public static void main(String[] args) {
        if (args.length >= 2) {
            usageInfo();
        } else {
            try {
                BufferedReader r;
                if (args.length == 0) {
                    // Read from stdin.
                    r = new BufferedReader(new InputStreamReader(System.in));
                } else {
                    // Read from a named file.
                    r = new BufferedReader(new FileReader(args[0]));
                }

                try {
                    List<Bid> bids = parseBids(r);
                    trade(bids);
                } finally {
                    r.close();
                }
            } catch (MalformedBid e) {
                System.err.println(e.getMessage());
                usageInfo();
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + args[0] + ".");
                usageInfo();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                usageInfo();
            }
        }
    }
}

