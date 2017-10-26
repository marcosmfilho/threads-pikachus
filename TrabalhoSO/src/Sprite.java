import javax.swing.ImageIcon;
 
public class Sprite {
  
 ImageIcon cenas[]; 
 int x; 
 int y;   
 int largura; 
 int altura; 
 int cena = 0;  
 int controlaVelocidade = 0;
 int velocidade = 5;
 int id;
   
 public Sprite(int numeroDeCenas, int x, int y, int id){
	  
	  cenas = new ImageIcon[numeroDeCenas];
	  this.id = id;
	  this.x = x;
	  this.y = y;
 }
  
 public void animar(){
	  cena += 1;
	  if(cena == cenas.length){ 
		  cena = 0; 
	   }
 }

 public void animarMaisLento(){
   controlaVelocidade+=1;
   if(controlaVelocidade>velocidade){
	   cena += 1;
	   controlaVelocidade = 0;
	   if(cena == cenas.length){ 
		   cena = 0; 
	   }
   }
 }
  
}