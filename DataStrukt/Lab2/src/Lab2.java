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

    public static void trade(List<Bid> bids) throws MissingBid {
    	       
    	PriorityQueue buyers = new PriorityQueue(new buyComparator());
    	PriorityQueue sellers = new PriorityQueue(new sellComparator());
    	
    	// göra för alla bud i budlistan {
        for (int i=0; i<bids.size(); i++) {
    	
        	//	budet är ett förändringsbud (köp), måste först ta bort det gamla budet
        	if (bids.get(i).type.equals("NK")) {
        		// letar upp det gamla budet i orderlistan och tar bort det
    			int index = buyers.findBid(bids.get(i).name, bids.get(i).old_value);
    			if (index == -1) {
    				throw new MissingBid("Det tidigare budet " + bids.get(i).name + " K " + bids.get(i).old_value + " finns inte");
    			}
    			else {
    				buyers.deleteBid(index);
    			}
    			// lägger till det nya budet
    			buyers.insertBid(bids.get(i).name, bids.get(i).value);
    		}
        	
        	//	budet är ett förändringsbud (sälj), måste först ta bort det gamla budet
        	if (bids.get(i).type.equals("NS")) {	
        		// letar upp det gamla budet i orderlistan och tar bort det
    			int index = sellers.findBid(bids.get(i).name, bids.get(i).old_value);
    			if (index == -1) {
    				throw new MissingBid("Det tidigare budet " + bids.get(i).name + " S " + bids.get(i).old_value + " finns inte");
    			}
    			else {
    				sellers.deleteBid(index);
    			}
    			// lägger till det nya budet
    			sellers.insertBid(bids.get(i).name, bids.get(i).value);
    		}
        	
        	// vanligt köpbud
        	else if (bids.get(i).type.equals("K")) {
    			buyers.insertBid(bids.get(i).name, bids.get(i).value);
    		}
        	
        	// vanligt säljbud
        	else if (bids.get(i).type.equals("S")) {
    			sellers.insertBid(bids.get(i).name, bids.get(i).value);
    		}
        	
        	// HÄR KOMMER KODEN FÖR ATT UNDERSÖKA OM AVSLUT ÄR MÖJLIGT
        	// TYP JÄMFÖRA highestBid FÖR sellers och buyers
        	// om det är >= printa och ta bort buden
        	
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
     * Exception class for missing bids.
     */

    public static class MissingBid extends Exception {
        MissingBid(String bid) {
            super("Missing bid: " + bid + ".");
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
            } catch (MissingBid e) {
                    System.err.println(e.getMessage());
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

