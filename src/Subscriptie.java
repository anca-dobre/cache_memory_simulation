
public abstract class Subscriptie {

	protected String nume;
	int timestamp;
	int accesari = 0;

	public abstract String tip();

	public Subscriptie(String nume) {	
		this.nume = nume;
	}

}