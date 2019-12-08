
import java.util.ArrayList; 

public class Memorie {

	public ArrayList<Subscriptie> memoriePrincipala = new ArrayList<Subscriptie>();

	public Subscriptie existaInMemoriaPrincipala( String nume) {
		for(int j = 0; j < memoriePrincipala.size(); j++) {
			if(memoriePrincipala.get(j).nume.equals(nume) == true) 
				return memoriePrincipala.get(j);
		}
		return null;
	}

}