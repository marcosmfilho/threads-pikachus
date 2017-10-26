import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Wagon extends Thread{
	
	private WagonStatus status;
	private static int seats;
	static LinkedList<Passenger> passengersWagon = new LinkedList<Passenger>();
	static boolean isFinish;
	static int velocidade;
	
	public Wagon(int seats, int velocidade) {
		this.status = WagonStatus.DESLIGADO;
		Wagon.seats = seats;
		Wagon.isFinish = false;
		Wagon.velocidade = velocidade;
	}
	
	//RUN
	public void run(){
        
		 while(true){
            try {
				actionWagon();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}
	
	//MÉTODOS
	
	public void actionWagon() throws InterruptedException{
		
		down(Game.semaphoreWagon);
		
		long delta = 0;
		long start = System.currentTimeMillis();
		long time = start;
		System.out.println(" ");
		System.out.println("Vagão está executando....");
		Wagon.isFinish = false;
		while(!Wagon.isFinish){
			long current = System.currentTimeMillis();
			delta = current - time;
			Game.viagem(delta);
			time += delta;
			System.out.println(time-start);
			Thread.sleep(1000/30);
		}
		System.out.println("Vagão terminou a viagem");
		System.out.println(" ");
	}
	
	public static boolean Max(){
		if(Wagon.passengersWagon.size() == getSeats()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static LinkedList<Passenger> getPassengersWagon() {
		return passengersWagon;
	}

	public static void setPassengersWagon(LinkedList<Passenger> passengersWagon) {
		Wagon.passengersWagon = passengersWagon;
	}

	public static boolean isFinish() {
		return isFinish;
	}

	public static void setFinish(boolean isFinish) {
		Wagon.isFinish = isFinish;
	}

	public void setSeats(int seats) {
		Wagon.seats = seats;
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

	public WagonStatus getStatus() {
		return status;
	}
	public void setStatus(WagonStatus status) {
		this.status = status;
	}
	public static int getSeats() {
		return seats;
	}
	
	public static int getVelocidade() {
		return velocidade;
	}
	
	public void estadoSemaforos(){
		System.out.println("");
		System.out.println("Semáforo passageiro: " + Game.semaphorePassenger.availablePermits());
		System.out.println("Semáforo vagão: " + Game.semaphoreWagon.availablePermits());
		System.out.println("Semáforo mutexIn: " + Game.mutexIn.availablePermits());
		System.out.println("Semáforo mutexOut: " + Game.mutexOut.availablePermits());
		System.out.println("");
	}


}


