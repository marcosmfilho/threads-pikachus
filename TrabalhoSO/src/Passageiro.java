
public class Passageiro extends Thread{

	//Id = identificador do passageiro.
	//Te = tempo de embarque (tempo que o passageiro gasta para entrar no vagão sentar na cadeira). Durante este tempo o thread deverá estar executando.
	//Td = tempo de desembarque (tempo que o passageiro gasta para
	
	String status;
	int idPassageiro;
	int tempoDeEmbarque;
	int tempoDeDesembarque;
	
	public Passageiro(String status, int idPassageiro, int tempoDeEmbarque, int tempoDeDesembarque){
		
		this.status= status;
		this.idPassageiro = idPassageiro;
		this.tempoDeEmbarque = tempoDeEmbarque;
		this.tempoDeDesembarque = tempoDeDesembarque;
		
	}
	
	public Passageiro(){
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getIdPassageiro() {
		return idPassageiro;
	}

	public void setIdPassageiro(int idPassageiro) {
		this.idPassageiro = idPassageiro;
	}

	public int getTempoDeEmbarque() {
		return tempoDeEmbarque;
	}

	public void setTempoDeEmbarque(int tempoDeEmbarque) {
		this.tempoDeEmbarque = tempoDeEmbarque;
	}

	public int getTempoDeDesembarque() {
		return tempoDeDesembarque;
	}

	public void setTempoDeDesembarque(int tempoDeDesembarque) {
		this.tempoDeDesembarque = tempoDeDesembarque;
	}
}
