

public interface Cache {
	public int getLastTimestamp();
	public void add(Subscriptie subscriptie);
	public void remove();
	public int existaInCache(String nume);
	public void verificaElement(Subscriptie subscriptie);
	public void setNrMaxElemente(int nr);
	
}