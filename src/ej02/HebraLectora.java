package ej02;

public class HebraLectora extends Thread{
	
	private final RecursoCompartido recurso1;
	private final RecursoCompartido recurso2;
	private final String nombre;

	public HebraLectora(RecursoCompartido recurso1, RecursoCompartido recurso2, String nombre) {
		this.nombre = nombre;
		this.recurso1 = recurso1;
		this.recurso2 = recurso2;
	}
	
	@Override
	public void run() {
		try {
			//como nos dicen que el orden de lectura tiene que ser al azar:
			if(Math.random()<0.5) {
				System.out.println(nombre + " decide leer Recurso 1 y luego Recurso 2");
				recurso1.leer(nombre);
				recurso2.leer(nombre);
			} else {
				System.out.println(nombre + " decide leer Recurso 2 y luego Recurso 1");
				recurso2.leer(nombre);
				recurso1.leer(nombre);
			}
		} catch (InterruptedException e) {}
	}

}
