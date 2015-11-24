import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.ArrayList;


/**
 * ...
 */

public class Lab2 {

    /**
     * 
     */

    public static void trade(List<Bid> bids) {
    	       
    	PriorityQueue buyers = new PriorityQueue();
    	PriorityQueue sellers = new PriorityQueue();
    	    	
    	// göra för alla bud i budlistan {
    	
        for (int i=0; i<bids.size(); i++) {
    	
        	//	om budet är ett förändringsbud måste vi först ta bort det gamla budet
        	if (bids.get(i).type.equals("NK")) {
    			buyers.deleteBid(bids.get(i).name, bids.get(i).old_value);
    			buyers.insertBid(bids.get(i).name, bids.get(i).value);
    		}
        	
        	//	om budet är ett förändringsbud måste vi först ta bort det gamla budet       	
        	else if (bids.get(i).type.equals("NS")) {
    			sellers.deleteBid(bids.get(i).name, bids.get(i).old_value);
    			sellers.insertBid(bids.get(i).name, bids.get(i).value);
    		}
        	
        	else if (bids.get(i).type.equals("K")) {
    			buyers.insertBid(bids.get(i).name, bids.get(i).value);
    		}
        	
        	else if (bids.get(i).type.equals("S")) {
    			sellers.insertBid(bids.get(i).name, bids.get(i).value);
    		}
        	
        }
        
    	// printar orderlistorna
        System.out.print("Säljare: ");
        sellers.print();
        System.out.println();
        System.out.print("Köpare: ");
        buyers.print();
        System.out.println();
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
            	// Förändringsbud
                // m.group(1): The name of the buyer/seller.
                // m.group(4): NK or NS.
                // m.group(5): Old value.
                // m.group(6): New value.    	
                return new Bid(m.group(1), m.group(4), Integer.parseInt(m.group(6)), Integer.parseInt(m.group(5)));
            } else {
            	// Nytt bud
                // m.group(1): The name of the buyer/seller.
                // m.group(2): K or S.
                // m.group(3): The value.
                return new Bid(m.group(1), m.group(2), Integer.parseInt(m.group(3)));
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
     *
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

