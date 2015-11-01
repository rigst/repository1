import java.util.ArrayList;

public class Condominio {
	private String nome;
	private ArrayList<Bloco> blocos;
	
	public Condominio(String nome){
		this.nome = nome; blocos = new ArrayList<Bloco>();
	}
	
	public void addBloco(int numero){
		int pos = 0;
		for(Bloco aux : blocos){
			if(aux.getNumero() > numero) break;
			pos++;
		}
		blocos.add(pos, new Bloco(numero));
	}
	
	public void addBloco(Bloco bloco){
		int pos = 0;
		for(Bloco aux : blocos){
			if(aux.getNumero() > bloco.getNumero()) break;
			pos++;
		}
		blocos.add(pos, bloco);
	}
	
	public Bloco getBloco(int numero){
		for(Bloco aux : blocos){
			if(aux.getNumero() == numero) return aux;
		}
		return null;
	}
	
	public String toString(){
		String msg = "CONDOMINIO " + nome + "\n";
		for(Bloco aux : blocos){
			msg += aux + "\n";
		}
		return msg;
	}
}
