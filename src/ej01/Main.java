package ej01;

public class Main {
	
	private static final int Hebras_Lectoras = 300;
	private static final int RatioLectoras_Escritoras = 30;
	
	public static void main (String[] args) {
		
		RecursoCompartido recurso = new RecursoCompartido();
		System.out.println("Iniciando... " + Hebras_Lectoras + " lectoras " + Hebras_Lectoras/RatioLectoras_Escritoras + " escritoras.");
		
		//Ahora tenemos que crear las hebras y arrancarlas.
		try {
			for(int i=0; i< Hebras_Lectoras; i++) {
				Thread t = new HebraLectora(recurso, "L" + i);
				t.start();
				if(i % RatioLectoras_Escritoras == 0) {
					Thread t1  = new HebraEscritora("E" + i, recurso);
					t1.start();
				}
				
				Thread.sleep((long) (Math.random() * 500));
			}
			
		} catch (InterruptedException e) {}
	}
}
