package ej03;

public class HebraEscritora extends Thread{
	private final String nombre;
	private final RecursoCompartido recurso;

	public HebraEscritora(String nombre, RecursoCompartido recurso) {
		this.nombre = nombre;
		this.recurso = recurso;
	}
	
	@Override
	public void run() {
		try {
				recurso.escritura(nombre);
			
		} catch (InterruptedException e) {}
	}
}
