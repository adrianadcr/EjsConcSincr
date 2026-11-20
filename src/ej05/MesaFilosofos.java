package ej05;

public class MesaFilosofos {

	private boolean[] tenedores;
	
	public MesaFilosofos(int numFilosofos) {
		this.tenedores = new boolean[numFilosofos];
		//creamos un array de booleans que son los tenedores(el número es el mismo que el de filosofos)
		//cuando sea true el tenedor estará ocupado y cuando sea false estará libre
	}
	
	public synchronized void cogerTenedor(int idFil) throws InterruptedException {
		int tenDcha = idFil;
		int tenIzda = (idFil + 1) % tenedores.length;
		
		int primero = Math.min(tenDcha, tenIzda);
		int segundo = Math.max(tenDcha, tenIzda);
		
		while(tenedores[primero]) {
			wait();			
		}
		tenedores[primero] = true;		
		
		while(tenedores[segundo]) {
			wait();
		}
		
		tenedores[segundo] = true;
		
		System.out.println("El filósofo " + idFil + " está empezando a comer.");
	}
	
	public synchronized void dejarTenedor(int idFil) throws InterruptedException {
		int tenDcha = idFil;
		int tenIzda = (idFil + 1) % tenedores.length;
		
		int primero = Math.min(tenDcha, tenIzda);
		int segundo = Math.max(tenDcha, tenIzda);
		
		
	}
}
