

import java.util.ArrayList;

public class FIFOCache implements Cache {
	public int nrMaxElemente;
	public ArrayList<Subscriptie> subscriptii = new ArrayList<Subscriptie>();
	
	@Override
	public void add(Subscriptie element) {
		if(subscriptii.size() >= nrMaxElemente) {
			subscriptii.remove(0);
		}
		subscriptii.add(element);
	}

	@Override
	public void remove() {}
	
	@Override
	public int existaInCache(String nume) {
		for(int j = 0; j < subscriptii.size(); j++) {
			if(subscriptii.get(j).nume.equals(nume) == true) {
				return j;
			}
		}
		return -1;
	}
	
	@Override
	public void verificaElement(Subscriptie subscriptie) {
		if(subscriptii.contains(subscriptie))
			subscriptii.remove(subscriptie);
	}
	
	@Override
	public int getLastTimestamp() {
		return 0;
	}
	
	@Override
	public void setNrMaxElemente( int nrMaxElemente) {
		this.nrMaxElemente = nrMaxElemente;
	}
}