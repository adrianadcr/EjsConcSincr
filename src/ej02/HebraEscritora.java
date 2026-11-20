package ej02;

public class HebraEscritora extends Thread{
	private final String nombre;
	private final RecursoCompartido recurso1;
	private final RecursoCompartido recurso2;

	public HebraEscritora(String nombre, RecursoCompartido recurso1, RecursoCompartido recurso2) {
		this.nombre = nombre;
		this.recurso1 = recurso1;
		this.recurso2 = recurso2;
	}
	
	@Override
	public void run() {
		try {
			if(Math.random()<0.5) {
				System.out.println(nombre + "decide escribir el Recurso 1");
				recurso1.escritura(nombre);
			} else {
				System.out.println(nombre + "decide escribir el Recurso 2");
				recurso2.escritura(nombre);
			}
		} catch (InterruptedException e) {}
	}
}
