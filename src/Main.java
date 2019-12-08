

import java.io.*;



public class Main {
	public static void main(String[] args) throws FileNotFoundException {

		BufferedReader br = null;
		FileReader fr = null;
		// creez fisierul in care scriu
		PrintStream fileStream = null ;
		fileStream = new PrintStream (args[1]);
		System.setOut(fileStream);

		try {

			fr = new FileReader (args[0]);
			br = new BufferedReader(fr);

			String tipCache = br.readLine();
			int nrMaxElemente = Integer.parseInt(br.readLine());
			int nrOperatii = Integer.parseInt(br.readLine());

			//initializez memoria principala
			Memorie memoriePrincipala = new Memorie();

			Cache cache = null;

			if(tipCache.equals("LRU")==true) {
				cache = new LRUCache(); 
			} else if(tipCache.equals("FIFO")==true) {
				cache = new FIFOCache(); 									
			} else if(tipCache.equals("LFU")==true) {
				cache = new LFUCache(); 		
			}
			cache.setNrMaxElemente(nrMaxElemente);

			for(int i = 0; i < nrOperatii; i++) {
				String instructiune = br.readLine();
				String[] vectorInstructiuni = instructiune.split(" ");

				if(vectorInstructiuni[0].equals("ADD")==true) {
					//verific daca am obiect basic sau premium

					int ptBasic =  Integer.parseInt(vectorInstructiuni[2]);
					int ptPremium;
					Subscriptie noulElement;
					if(vectorInstructiuni.length == 4) {
						ptPremium =  Integer.parseInt(vectorInstructiuni[3]);
						noulElement = new Premium (vectorInstructiuni[1], ptBasic, ptPremium);
						//						noulElement.accesari = 0;
					} else {
						noulElement = new Basic (vectorInstructiuni[1], ptBasic);
						//						noulElement.accesari = 0;
					}

					//am initializat noulElement
					//acum il adaug in memoria principala
					//daca gasesc NoulElement in Memoria Principala il suprascriu
					//daca gasesc NoulELement in Cache il elimin
					
					Subscriptie subscriptie = memoriePrincipala.existaInMemoriaPrincipala(noulElement.nume);
					if(subscriptie != null) {
						cache.verificaElement(subscriptie);
						memoriePrincipala.memoriePrincipala.remove(subscriptie);								
					} 
					memoriePrincipala.memoriePrincipala.add(noulElement);		

				} else if(vectorInstructiuni[0].equals("GET") == true) {
					int afisare = 0;
					int indexCache = 0;
					Subscriptie subscriptieMemorie = null;
					
					//verificam daca se afla in cache
					indexCache = cache.existaInCache(vectorInstructiuni[1]);

					//actualizez afisare
					if(indexCache != -1) 
						afisare = 0;

					//verificam daca se afla in memoria principala
					subscriptieMemorie = memoriePrincipala.existaInMemoriaPrincipala(vectorInstructiuni[1]);

					//daca exista in memoria principala, dar nu si in cache 
					//il adaugam in cache si adaugam timestamp ul				
					if(indexCache == -1 && subscriptieMemorie != null) {

						subscriptieMemorie.timestamp = cache.getLastTimestamp();					
						afisare = 1;
						cache.add(subscriptieMemorie);
					}

					//daca nu exista nici in memoria principala
					if(subscriptieMemorie == null) {
						afisare = 2;
					}
					if (afisare!=2) { 
						System.out.println(afisare + " " + subscriptieMemorie.tip());
					}
					else
						System.out.println(afisare);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}