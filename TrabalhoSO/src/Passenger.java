import java.util.concurrent.Semaphore;

public class Passenger extends Thread{
	
	private PassengerStatus status;
	private int idPassenger;
	Sprite sprite;
	static int velPassenger;
	
	public Passenger(int idPassenger, int velPassenger){
		
		this.idPassenger = idPassenger;
		this.sprite = new Sprite(8, -200-(idPassenger*120), 445, idPassenger);
		Passenger.velPassenger = velPassenger;
	}
	
	//RUN
	public void run(){
		go();	
		while(true){
					//BOARDING
					down(Game.semaphorePassenger);
					down(Game.mutexIn);
					goWagon();
					wagonFull();
					up(Game.mutexIn);
					actionPassenger();
					
					while(!Wagon.isFinish);
					
					//LANDING
					down(Game.mutexOut); 
					outWagon();
		            restart();
					up(Game.mutexOut);
		}
			
	}
	
	//M�TODOS
	
	public void go(){
		Game.goFila(this.idPassenger);
	}
	
	public void goWagon(){
		System.out.println("O passageiro " + this.idPassenger + " foi pro vag�o");
		Game.goWagon(this.idPassenger);
		Wagon.passengersWagon.addFirst(this);
	}
	
	public void wagonFull(){
		if(Wagon.Max()){
			System.out.println("Capacidade m�xima");
			up(Game.semaphoreWagon);
		}
	}
	
	public void actionPassenger(){
		System.out.println("Passageiro " + this.idPassenger +  " est� executando dentro do vag�o");
		while(!Wagon.isFinish){
		    Game.actionSprite(this.idPassenger);
		}
    }
	
	public void outWagon(){
		
		System.out.println("Passageiro " + this.idPassenger +  " est� saindo do vag�o");
		Game.outWagon(this.idPassenger);		
	}
		
	public void restart(){
		
		if(Wagon.passengersWagon.size() == 0){
			Wagon.isFinish = false;
			System.out.println("Vagas do vag�o: " + Wagon.getSeats());
			Game.semaphorePassenger.release(Wagon.getSeats());
			down(Game.mutexOut);
			//up(Game.mutexIn);
		}
		System.out.println("");
		System.out.println("Sem�foro passageiro: " + Game.semaphorePassenger.availablePermits());
		System.out.println("Sem�foro vag�o: " + Game.semaphoreWagon.availablePermits());
		System.out.println("Sem�foro mutexIn: " + Game.mutexIn.availablePermits());
		System.out.println("Sem�foro mutexOut: " + Game.mutexOut.availablePermits());
		System.out.println("");
	}
		
	
		
	public void down(Semaphore semaphore){
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void up(Semaphore semaphore){
		semaphore.release();
	}
	
	public PassengerStatus getStatus() {
		return status;
	}
	public void setStatus(PassengerStatus status) {
		this.status = status;
	}
	public int getIdPassenger() {
		return idPassenger;
	}
	
	public static int getVelPassenger() {
		return velPassenger;
	}

	
}
