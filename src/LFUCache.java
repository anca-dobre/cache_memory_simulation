

import java.util.ArrayList;

public class LFUCache implements Cache {
	public int nrMaxElemente;
	ArrayList<Subscriptie> subscriptii = new ArrayList<Subscriptie>();
	int timestamp = 0;
	
	@Override
	public void add(Subscriptie element) {	
		if(subscriptii.size() >= nrMaxElemente) {
			subscriptii.remove(calcularePozitie());
		}
			subscriptii.add(element);
	}

	@Override
	public void remove() { }

	@Override
	public int existaInCache(String nume) {
		for(int j = 0; j < subscriptii.size(); j++) {
			if(subscriptii.get(j).nume.equals(nume) == true) {
				subscriptii.get(j).timestamp = timestamp;
			 	timestamp++;
				subscriptii.get(j).accesari++;
				return j;
			}
		}
		return -1;
	}

	private int calcularePozitie() {
		int min = subscriptii.get(0).accesari;
		int pozitie = 0;
		for(int i = 1; i < subscriptii.size(); i++) {
			if 		(subscriptii.get(i).accesari < min ||
					(subscriptii.get(i).accesari == min && 
					subscriptii.get(i).timestamp < subscriptii.get(pozitie).timestamp) ) {
				min = subscriptii.get(i).accesari;
				pozitie = i;
			}
		}
		return pozitie;
	}
	
	@Override
	public void verificaElement(Subscriptie subscriptie) {
		if(subscriptii.contains(subscriptie))
			subscriptii.remove(subscriptie);
	}
	
	@Override
	public int getLastTimestamp() {
		return timestamp++;
	}
	
	@Override
	public void setNrMaxElemente( int nrMaxElemente) {
		this.nrMaxElemente = nrMaxElemente;
	}
}