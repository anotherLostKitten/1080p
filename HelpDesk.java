import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class HelpDesk {
    private PriorityQueue<Ticket> _tickets;
    private int _nextID;
    private Scanner scin;
    public HelpDesk() {
	_tickets = new ArrayPriorityQueue<Ticket>();
	_nextID = 0;
	scin = new Scanner(System.in);
    }
    
    public void genTicket() {
	System.out.println("Hello good sir or madam. By what would you like to be refered to?");
	String n = scin.nextLine();
	System.out.println("What seems to be the problem, " + n + "?");
	String p = scin.nextLine();
	Ticket t = new Ticket(_nextID, p.length());
	t.setName(n);
	t.setProb(p);
	_tickets.add(t);
	_nextID++;
    }
    
    public void saveSolution(String s) {
	try {
	    FileWriter f = new FileWriter("solutions.txt", true);
	    f.write(s);
	    f.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    public boolean alreadySolved() {
	try {
	    Scanner skin = new Scanner(new File("solutions.txt"));
	    String q;
	    while (skin.hasNextLine()) {
		q = skin.nextLine();
		if(q == null)
		    break;
		else if( q.substring(q.indexOf("|") + 1).equals(_tickets.peekMin().getProb())) {
		    System.out.println(_tickets.peekMin().getName() + ", your problem, " + _tickets.peekMin().getProb() + ", has already been solved. Try " + q.substring(0,q.indexOf("|")) + ".");
		    return true;
		}
	    }
	} catch(FileNotFoundException e) {
	    e.printStackTrace();
	}
	return false;
    }
    public String genSolution() {
	try {
	    Scanner sc = new Scanner(new File("random.txt"));
	    String pSol;
	    while (sc.hasNextLine()) {
		pSol = sc.nextLine();
		System.out.println("Hello " + _tickets.peekMin().getName() + "! To fix your problem, " + _tickets.peekMin().getProb()+ ", try " + pSol + ". Did this fix your problem?");
		if (scin.nextLine().substring(0,1).toLowerCase().equals("y")) {
		    sc.close();
		    return pSol;
		}
	    }
	    sc.close();
	    scin.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	return "nothing. There is no solution to this problem";
    }

    public void solution() {
	if(! alreadySolved())
	    saveSolution(genSolution() + "|" + _tickets.peekMin().getProb() + System.lineSeparator());
	_tickets.removeMin();
    }
    public static void main(String args[]) {
	HelpDesk hd = new HelpDesk();
	hd.genTicket();
        hd.solution();
	hd.genTicket();
        hd.solution();
	hd.genTicket();
        hd.solution();
	hd.genTicket();
        hd.solution();
	
    }
}
