

import java.util.ArrayList;

public class LRUCache implements Cache {
	public int nrMaxElemente;
	public int timestamp = 0;
	public ArrayList<Subscriptie> subscriptii =new ArrayList<Subscriptie>();

//	int timestamp;
	
	@Override
	public void add(Subscriptie element) {
		if(subscriptii.size() >= nrMaxElemente) {
			subscriptii.remove(calcularePozitie());
		} 
		subscriptii.add(element);
	}

	@Override
	public void remove() {}

	@Override
	public int existaInCache( String nume) {
		int j;
		for( j = 0; j < subscriptii.size(); j++) {
			if(subscriptii.get(j).nume.equals(nume) == true) {
				subscriptii.get(j).timestamp = timestamp;
				timestamp++;
				return j;
			}
		}
		return -1;
	}

	private int calcularePozitie() {
		int min = subscriptii.get(0).timestamp;
		int pozitie = 0;
		for(int i = 1; i < subscriptii.size(); i++) {
			if (subscriptii.get(i).timestamp < min) {
				min = subscriptii.get(i).timestamp;
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