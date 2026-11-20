package ej02;

public class Main {
	private static final int Hebras_Lectoras = 300;
	private static final int RatioLectoras_Escritoras = 30;
	
	public static void main (String[] args) {
		
		RecursoCompartido recurso1 = new RecursoCompartido();
		RecursoCompartido recurso2 = new RecursoCompartido();
		System.out.println("Iniciando... " + Hebras_Lectoras + " lectoras " + Hebras_Lectoras/RatioLectoras_Escritoras + " escritoras.");
		System.out.println("Iniciando para dos recursos");
		
		//Ahora tenemos que crear las hebras y arrancarlas.
		try {
			for(int i=0; i< Hebras_Lectoras; i++) {
				Thread t = new HebraLectora(recurso1, recurso2, "L" + i);
				t.start();
				if(i % RatioLectoras_Escritoras == 0) {
					Thread t1  = new HebraEscritora("E" + i, recurso1, recurso2);
					t1.start();
				}
				
				Thread.sleep((long) (Math.random() * 500));
			}
			
		} catch (InterruptedException e) {}
	}
}
