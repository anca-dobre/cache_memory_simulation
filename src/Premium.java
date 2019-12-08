
public class Premium extends Basic {

	int numarPremium;
	
	public Premium(String nume, int numarBasic, int numarPremium) {
		super(nume, numarBasic);
		this.numarPremium = numarPremium;
		this.numarBasic = numarBasic;
	}

	@Override
	public String tip() {
		if(numarPremium > 0) {
			numarPremium--;
			return "Premium";
		} else {
			return super.tip();
		}
	}

}