import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.ArrayList;

public class Lab2 {

    public static void trade(List<Bid> bids) throws MissingBid {
    	PriorityQueue<Bid> buyers = new PriorityQueue<Bid>(new buyComparator());
    	PriorityQueue<Bid> sellers = new PriorityQueue<Bid>(new sellComparator());
    	
        for (int i=0; i<bids.size(); i++) {
    	
        	// Deletes the bid from the order list
        	if (bids.get(i).getType().equals("NK")) { 
        		try {
        			buyers.deleteElement(new Bid(bids.get(i).getName(), bids.get(i).getOldValue()));
        		}
        		catch(Exception e) {
        			throw new MissingBid("Det tidigare budet " + bids.get(i).getName() + " K " + bids.get(i).getOldValue() + " finns inte");
        		}
        		// Adds the new bid to the order list
    			buyers.addElement(new Bid(bids.get(i).getName(), bids.get(i).getNewValue()));
    		}
        	
        	// Deletes the bid from the order list       	
        	if (bids.get(i).getType().equals("NS")) {	
    			
        		try {
        			sellers.deleteElement(new Bid(bids.get(i).getName(), bids.get(i).getOldValue()));
        		}
        		catch(Exception e){
        			throw new MissingBid("Det tidigare budet " + bids.get(i).getName() + " S " + bids.get(i).getOldValue() + " finns inte");
        		}
        		// Adds the new bid to the order list
        		sellers.addElement(new Bid(bids.get(i).getName(), bids.get(i).getNewValue()));;
    		}
        	
        	else if (bids.get(i).getType().equals("K")) {
        		buyers.addElement(new Bid(bids.get(i).getName(), bids.get(i).getNewValue()));
    		}
        	
        	else if (bids.get(i).getType().equals("S")) {
        		sellers.addElement(new Bid(bids.get(i).getName(), bids.get(i).getNewValue()));    		}
        	
        	// Trading and print
            if (sellers.sizeQueue() > 0 & buyers.sizeQueue() > 0) {
            	
            	Bid topBuyer = (Bid)buyers.topElement();
            	Bid topSeller = (Bid)sellers.topElement();

            	if (topBuyer.getNewValue() >= topSeller.getNewValue()) {
            		System.out.println(topBuyer.getName() + " köper från " + topSeller.getName() + " för " + topBuyer.getNewValue() + " kr");
            		buyers.deleteElement(topBuyer);
            		sellers.deleteElement(topSeller);
            	}
            }
        }
        
    	// Prints the order list
        System.out.println(); 
        System.out.println("Orderbok:");
        
        System.out.print("Säljare: ");
        int sellersSize = sellers.sizeQueue();
        for (int i = 0; i < sellersSize; i++) {
        	Bid topSeller = (Bid)sellers.topElement();
        	System.out.print(topSeller.getName() + " " + topSeller.getNewValue());
        	if (i+1 < sellersSize) {
        		System.out.print(", ");
        	}
        	sellers.deleteElement(topSeller);
        }
        int buyersSize = buyers.sizeQueue();
        System.out.println();
        
        System.out.print("Köpare: "); 
        for (int i = 0; i < buyersSize; i++) {
        	Bid topBuyer = (Bid)buyers.topElement();
        	System.out.print(topBuyer.getName() + " " + topBuyer.getNewValue());
        	if (i+1 < buyersSize) {
        		System.out.print(", ");
        	}
        	buyers.deleteElement(topBuyer);
        }
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
                // m.group(1): The name of the buyer/seller.
                // m.group(4): NK or NS.
                // m.group(5): Old value.
                // m.group(6): New value.    	
                return new Bid(m.group(1), m.group(4), Integer.parseInt(m.group(5)), Integer.parseInt(m.group(6)));
            } else {
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
        s.close();
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

