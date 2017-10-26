import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Game extends JFrame {

private static final long serialVersionUID = 1L;

//Semaphores
public static Semaphore semaphorePassenger;
public static Semaphore semaphoreWagon;
public static Semaphore semaphoreFila;
public static Semaphore mutexIn;
public static Semaphore mutexOut;

static JMenuBar menuBar = new JMenuBar();
static JMenu menu = new JMenu("Menu");


//Row
static ArrayList<Passenger> fila = new ArrayList<Passenger>();

//Var
int FPS = 30;
int janelaW = 799;
int janelaH = 599;
public static int distanciaPercorrida = 0;
BufferedImage backBuffer; 

//Sprites
Sprite cano = new Sprite(1, 700, 460,0);
Sprite canoOut = new Sprite(1, 70, -60,1);
static Sprite wagon = new Sprite(1, 30, 159,0);
ImageIcon fundo = new ImageIcon("src/bg.jpg");
ImageIcon vagao = new ImageIcon("src/wagon1.png");

 public static void viagem(double time){
	 
	 int velo = 1848/(Wagon.getVelocidade());
	 double vx = 200*(Wagon.getVelocidade());
	 double s = vx * time/1000;
	 
	 distanciaPercorrida += s;
	 wagon.x += s;
	 
	 if(distanciaPercorrida >= 1848){
		 wagon.x = 30;
		 distanciaPercorrida = 0;
		 Wagon.isFinish = true;
		 Game.mutexOut.release();
	 }
	 else if(distanciaPercorrida >= 1718 && wagon.y == 310){
		 wagon.x = -200;
		 wagon.y = 159;
	 }
	 else if(distanciaPercorrida >= 828 && distanciaPercorrida < 1718 && wagon.y == 159) {
		 wagon.x = -100;
		 wagon.y = 310;
	 }

 }
 
 
 public void desenharGraficos(){
    	  
	  Graphics g = getGraphics(); 
	  Graphics bbg = backBuffer.getGraphics();
	  bbg.drawImage(fundo.getImage(),0,0,this);
	  draw();
	  bbg.drawImage(wagon.cenas[wagon.cena].getImage(), wagon.x, wagon.y, this);
	  bbg.drawImage(cano.cenas[cano.cena].getImage(), cano.x, cano.y, this);
	  bbg.drawImage(canoOut.cenas[canoOut.cena].getImage(), canoOut.x, canoOut.y, this);
	  g.drawImage(backBuffer, 0, 0, this);
	 
 
 }
 
 public void drawSprite(Sprite sprite){
	  Graphics bbg = backBuffer.getGraphics();  
	  bbg.drawImage(sprite.cenas[sprite.cena].getImage(), sprite.x, sprite.y, this);
	  bbg.setColor(Color.WHITE);
	  bbg.fillOval(sprite.x+75, sprite.y+23, 20, 20);
	  bbg.setColor(Color.RED);
	  bbg.setFont(new Font(Font.DIALOG, Font.BOLD, 20)); 
	  bbg.drawString(Integer.toString(sprite.id), sprite.x+80, sprite.y+40);
	  sprite.animar();   
}
 
 public void draw(){
	 for(int i = 0; i< Game.fila.size(); i++){
		 drawSprite(Game.fila.get(i).sprite);
	 }
 }
 
 public static void goWagon(int id){
	 
	 long initialTime = System.currentTimeMillis();
	 int initialPos = Game.fila.get(id).sprite.x;
	 
	 
	 int caminho = 800 - Game.fila.get(id).sprite.x;
	 int velo = caminho/(Passenger.getVelPassenger());
	
	 while( Game.fila.get(id).sprite.x <= 800){
		 Game.fila.get(id);
		 Game.fila.get(id).sprite.x = (int) (initialPos + velo*(System.currentTimeMillis()- initialTime)/1000.0); //Game.fila.get(id).sprite.x + velo;//Passenger.getVelPassenger();
		 Game.fila.get(id).sprite.cenas[0] = new ImageIcon("src/pikachuFrame/pk0.gif");
		 Game.fila.get(id).sprite.cenas[1] = new ImageIcon("src/pikachuFrame/pk1.gif");
		 Game.fila.get(id).sprite.cenas[2] = new ImageIcon("src/pikachuFrame/pk2.gif");
		 Game.fila.get(id).sprite.cenas[3] = new ImageIcon("src/pikachuFrame/pk3.gif");
		 Game.fila.get(id).sprite.cenas[4] = new ImageIcon("src/pikachuFrame/pk4.gif");
		 Game.fila.get(id).sprite.cenas[5] = new ImageIcon("src/pikachuFrame/pk5.gif");
		 Game.fila.get(id).sprite.cenas[6] = new ImageIcon("src/pikachuFrame/pk6.gif");
		 Game.fila.get(id).sprite.cenas[7] = new ImageIcon("src/pikachuFrame/pk7.gif");
	  	    try {
				Thread.sleep(1000/30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       
	   }
 
	   Game.fila.get(id).sprite.x = 83;
	   Game.fila.get(id).sprite.y = -100;
	   
	   while( Game.fila.get(id).sprite.y <= 90){
		 Game.fila.get(id).sprite.cenas[0] = new ImageIcon("src/pikachuCaindo.png");
		 Game.fila.get(id).sprite.cenas[1] = new ImageIcon("src/pikachuCaindo.png");
		 Game.fila.get(id).sprite.cenas[2] = new ImageIcon("src/pikachuCaindo.png");
		 Game.fila.get(id).sprite.cenas[3] = new ImageIcon("src/pikachuCaindo.png");
		 Game.fila.get(id).sprite.cenas[4] = new ImageIcon("src/pikachuCaindo.png");
		 Game.fila.get(id).sprite.cenas[5] = new ImageIcon("src/pikachuCaindo.png");
		 Game.fila.get(id).sprite.cenas[6] = new ImageIcon("src/pikachuCaindo.png");
		 Game.fila.get(id).sprite.cenas[7] = new ImageIcon("src/pikachuCaindo.png");
		 Game.fila.get(id).sprite.y = Game.fila.get(id).sprite.y + 4;
		 //Game.fila.get(id).sprite.y = (int) (initialPos + velo*(System.currentTimeMillis()- initialTime)/1000.0);
	  	    try {
				Thread.sleep(1000/30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
 }
 
public static void outWagon(int id){
	
	 Wagon.passengersWagon.getFirst().sprite.cenas[0] = new ImageIcon("src/pikachuFrame/pk0.gif");
	 Wagon.passengersWagon.getFirst().sprite.cenas[1] = new ImageIcon("src/pikachuFrame/pk1.gif");
	 Wagon.passengersWagon.getFirst().sprite.cenas[2] = new ImageIcon("src/pikachuFrame/pk2.gif");
	 Wagon.passengersWagon.getFirst().sprite.cenas[3] = new ImageIcon("src/pikachuFrame/pk3.gif");
	 Wagon.passengersWagon.getFirst().sprite.cenas[4] = new ImageIcon("src/pikachuFrame/pk4.gif");
	 Wagon.passengersWagon.getFirst().sprite.cenas[5] = new ImageIcon("src/pikachuFrame/pk5.gif");
	 Wagon.passengersWagon.getFirst().sprite.cenas[6] = new ImageIcon("src/pikachuFrame/pk6.gif");
	 Wagon.passengersWagon.getFirst().sprite.cenas[7] = new ImageIcon("src/pikachuFrame/pk7.gif");
	 Wagon.passengersWagon.getFirst().sprite.x = -100;
	 Wagon.passengersWagon.getFirst().sprite.y = 445;
	     
     goFilaOut(Wagon.passengersWagon.getFirst().getIdPassenger());
           
     Wagon.passengersWagon.removeFirst();
 }
 
 public void framesSprite(){
	 
	 for(int i = 0; i < Game.fila.size(); i++){
		 
		 Game.fila.get(i).sprite.cenas[0] = new ImageIcon("src/pikachuFrame/pk0.gif");
		 Game.fila.get(i).sprite.cenas[1] = new ImageIcon("src/pikachuFrame/pk1.gif");
		 Game.fila.get(i).sprite.cenas[2] = new ImageIcon("src/pikachuFrame/pk2.gif");
		 Game.fila.get(i).sprite.cenas[3] = new ImageIcon("src/pikachuFrame/pk3.gif");
		 Game.fila.get(i).sprite.cenas[4] = new ImageIcon("src/pikachuFrame/pk4.gif");
		 Game.fila.get(i).sprite.cenas[5] = new ImageIcon("src/pikachuFrame/pk5.gif");
		 Game.fila.get(i).sprite.cenas[6] = new ImageIcon("src/pikachuFrame/pk6.gif");
		 Game.fila.get(i).sprite.cenas[7] = new ImageIcon("src/pikachuFrame/pk7.gif");
	 
	 }
 }
 
 public static void goFila(int id){
	      
     while(Game.fila.get(id).sprite.x <= 500 - (id*120)){
    	 Game.fila.get(id).sprite.x = Game.fila.get(id).sprite.x + Passenger.getVelPassenger();
    	try {
			Thread.sleep(1000/80);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
     
     Game.fila.get(id).sprite.cenas[0] = new ImageIcon("src/pikachuFrame/pk6.gif");
     Game.fila.get(id).sprite.cenas[1] = new ImageIcon("src/pikachuFrame/pk6.gif");
     Game.fila.get(id).sprite.cenas[2] = new ImageIcon("src/pikachuFrame/pk6.gif");
     Game.fila.get(id).sprite.cenas[3] = new ImageIcon("src/pikachuFrame/pk6.gif");
     Game.fila.get(id).sprite.cenas[4] = new ImageIcon("src/pikachuFrame/pk6.gif");
     Game.fila.get(id).sprite.cenas[5] = new ImageIcon("src/pikachuFrame/pk6.gif");
     Game.fila.get(id).sprite.cenas[6] = new ImageIcon("src/pikachuFrame/pk6.gif");
     Game.fila.get(id).sprite.cenas[7] = new ImageIcon("src/pikachuFrame/pk6.gif");
 }
 
 public static void goFilaOut(int id){
     
	 Game.fila.set(Game.fila.get(id).getIdPassenger(), Game.fila.get(id));
     while(Game.fila.get(id).sprite.x <= 500 - (id*120)){
    	 Game.fila.get(id).sprite.x = Game.fila.get(id).sprite.x + Passenger.getVelPassenger();
    	try {
			Thread.sleep(1000/80);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
     
     Game.fila.get(id).sprite.cenas[0] = new ImageIcon("src/pikachuFrame/pk6.gif");
     Game.fila.get(id).sprite.cenas[1] = new ImageIcon("src/pikachuFrame/pk6.gif");
     Game.fila.get(id).sprite.cenas[2] = new ImageIcon("src/pikachuFrame/pk6.gif");
     Game.fila.get(id).sprite.cenas[3] = new ImageIcon("src/pikachuFrame/pk6.gif");
     Game.fila.get(id).sprite.cenas[4] = new ImageIcon("src/pikachuFrame/pk6.gif");
     Game.fila.get(id).sprite.cenas[5] = new ImageIcon("src/pikachuFrame/pk6.gif");
     Game.fila.get(id).sprite.cenas[6] = new ImageIcon("src/pikachuFrame/pk6.gif");
     Game.fila.get(id).sprite.cenas[7] = new ImageIcon("src/pikachuFrame/pk6.gif");
 }
 
 public static void addNewPassenger(){
	 
	 Game.fila.add(new Passenger(Game.fila.size()+1, Passenger.getVelPassenger()));
	 Game.fila.get(Game.fila.size()).start();
	 goFila(Game.fila.size());
 }
 
 public static void removePassenger(Passenger passenger){
	 if(Game.fila.contains(passenger)){
		 Game.fila.remove(passenger);
	 }
	 else{
		 if(Wagon.isFinish){
			 Wagon.passengersWagon.remove((passenger.getIdPassenger()));
		 }
	 }
 }
 
 public static void actionSprite(int id){
	  int aux = 0;
	  
	  if(Wagon.getSeats() <= 2){
		  aux = 90;
	  }
	  else if(Wagon.getSeats() <= 4 && Wagon.getSeats() > 2){
		  aux = 253;
	  }
	  else if(Wagon.getSeats() <= 6 && Wagon.getSeats() > 4){
		  aux = 423;
	  }
	     
	 for(int i = 0; i<Wagon.passengersWagon.size(); i++){
		      Wagon.passengersWagon.get(i).sprite.x = (Game.wagon.x+aux)-(i*85);
		      Wagon.passengersWagon.get(i).sprite.y = Game.wagon.y-60;
		      Wagon.passengersWagon.get(i).sprite.cenas[0] = new ImageIcon("src/pikachuCam.png");
		      Wagon.passengersWagon.get(i).sprite.cenas[1] = new ImageIcon("src/pikachuCam.png");
		      Wagon.passengersWagon.get(i).sprite.cenas[2] = new ImageIcon("src/pikachuCam2.png");
		      Wagon.passengersWagon.get(i).sprite.cenas[3] = new ImageIcon("src/pikachuCam2.png");
		      Wagon.passengersWagon.get(i).sprite.cenas[4] = new ImageIcon("src/pikachuCam.png");
		      Wagon.passengersWagon.get(i).sprite.cenas[5] = new ImageIcon("src/pikachuCam.png");
		      Wagon.passengersWagon.get(i).sprite.cenas[6] = new ImageIcon("src/pikachuCam2.png");
		      Wagon.passengersWagon.get(i).sprite.cenas[7] = new ImageIcon("src/pikachuCam2.png");
	  }
 }
 
 @SuppressWarnings("deprecation")
public void inicializar(){
	 
	  setTitle("RollerCoaster Pikachu");
	  setSize(janelaW, janelaH);
	  setJMenuBar(menuBar);
	  System.out.println(menuBar.isVisible());
	  System.out.println(menuBar.getAlignmentX());
	  System.out.println(menuBar.bounds());
	  setResizable(false);
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  //setLayout(null);
	  setVisible(true);
	  backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);
	  cano.cenas[0] = new ImageIcon("src/canoIn.png");
	  canoOut.cenas[0] = new ImageIcon("src/canoOut.png");
	  
	  if(Wagon.getSeats() <= 2){
		  wagon.cenas[0] = new ImageIcon("src/wagon.png");
	  }
	  else if(Wagon.getSeats() <= 4 && Wagon.getSeats() > 2){
		  wagon.cenas[0] = new ImageIcon("src/wagon2.png");
	  }
	  else if(Wagon.getSeats() <= 6 && Wagon.getSeats() > 4){
		  wagon.cenas[0] = new ImageIcon("src/wagon3.png");
	  }
	  
	  framesSprite();
 }
 
 public void run(){
	  inicializar(); 
	  while (true) {
		   desenharGraficos();
		    try{
		       Thread.sleep(1000/30);
		    } catch (Exception e) {
		       System.out.println("Thread interrompida!");
		    }
	  }
}
 
 public static void addPassengers(int quant, int velocidade){
	 for(int i=0; i<quant; i++){
		 Game.fila.add(new Passenger(i,velocidade));
	 }
 }
 
 public static void startPassengers(){
	 for(int i = 0; i<Game.fila.size(); i++){
		 Game.fila.get(i).start();
	 }
 }
 
 public static void menu(){
	 
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				menuSO frame = new menuSO();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});	 
 }
 
 public static void main(String[] args){
    
	 menu();
     /*Wagon w = new Wagon(1);
     semaphoreWagon = new Semaphore(0);
     w.start();
    
     addPassengers(5);
     startPassengers();
     
     semaphorePassenger = new Semaphore(Wagon.getSeats());
     semaphoreFila = new Semaphore(Game.fila.size());
     mutexIn = new Semaphore(1);
     mutexOut = new Semaphore(0);
     
     
     Game game = new Game();
	 game.run();*/
 }

}





