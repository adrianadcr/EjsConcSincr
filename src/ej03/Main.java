package ej03;


public class Main {
	private static final int Hebras_Lectoras = 100;
	private static final int RatioLectoras_Escritoras = 10;
	
	
	public static void main (String[] args) {
		
		RecursoCompartido recurso = new RecursoCompartido();
		Prioridad[] prioridad = Prioridad.values();
		System.out.println("Iniciando... " + Hebras_Lectoras + " lectoras " + Hebras_Lectoras/RatioLectoras_Escritoras + " escritoras.");
		
		//Ahora tenemos que crear las hebras y arrancarlas.
		try {
			for(int i=0; i< Hebras_Lectoras; i++) {
				Prioridad pL = prioridad[(int)(Math.random()*3)];
				Thread t = new HebraLectora(recurso, "L" + i, pL);
				t.start();
				if(i % RatioLectoras_Escritoras == 0) {
					Prioridad pE = prioridad[(int)(Math.random()*3)];
					Thread t1  = new HebraEscritora("E" + i, recurso, pE);
					t1.start();
				}
				
				Thread.sleep((long) (Math.random() * 500));
			}
			
		} catch (InterruptedException e) {}
	}

}
