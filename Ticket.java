public class Ticket implements Comparable {
    private int _prio;
    private String _prob;
    private int _id;
    private String _name;
    
    public Ticket(int id, int prio) {
	_id = id;
	_prio = prio;
	
    }
    public int getId() { return _id; }
    public String getProb() { return _prob; }
    public String getName() { return _name; }
    
    public void setName(String n) { _name = n; }
    public void setProb(String n) { _prob = n; }

    public int compareTo(Object o) {
	if (o instanceof Ticket) {
	    return (new Integer(_prio)).compareTo(new Integer(((Ticket)o)._prio));
	} else
	    throw new IllegalArgumentException("Object is not Ticket");
    }


    public static void main(String args[]) {
	Ticket tocket = new Ticket(0, 2);
	Ticket tucket = new Ticket(0, 1);
	System.out.println(tocket.compareTo(tucket));
    }
}
