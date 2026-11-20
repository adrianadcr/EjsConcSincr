package ej03;

public class HebraEscritora extends Thread{
	private final String nombre;
	private final RecursoCompartido recurso;
	private final Prioridad p;

	public HebraEscritora(String nombre, RecursoCompartido recurso, Prioridad p) {
		this.nombre = nombre;
		this.recurso = recurso;
		this.p = p;
	}
	
	@Override
	public void run() {
		try {
				recurso.escritura(nombre,p);
			
		} catch (InterruptedException e) {}
	}
}
