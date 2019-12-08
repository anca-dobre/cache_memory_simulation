

public class Basic extends Free {

	int numarBasic;

	public Basic(String nume, int numarBasic) {
		super(nume);
		this.numarBasic = numarBasic;
	}

	@Override
	public String tip() {
		if(numarBasic > 0) {
			numarBasic--;
			return "Basic";
		} else { 
			return "Free";
		}
	}
	
}