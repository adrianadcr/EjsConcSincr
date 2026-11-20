package ej01;

public class HebraLectora extends Thread{
	
	
	private final RecursoCompartido recurso;
	private final String nombre;

	public HebraLectora(RecursoCompartido recurso, String nombre) {
		this.nombre = nombre;
		this.recurso = recurso;
	}
	
	@Override
	public void run() {
		try {
			recurso.leer(nombre);
		} catch (InterruptedException e) {}
	}
}
