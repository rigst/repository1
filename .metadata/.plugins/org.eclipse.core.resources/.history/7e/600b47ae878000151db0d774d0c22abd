import java.util.ArrayList;
import java.util.Calendar;
public class Acordos {
	private ArrayList<Acordo> listaAcordos;
	
	class Acordo{
		private String devedor, formaAtualizacao;
		private Calendar dataAssinatura, inicio, fim;
		private double valorTotal, valorParcela;
		private int numParcelas;
		private ArrayList<Calendar> debitosInclusos;
		private ArrayList<Item> tabela;
		
		class Item{
			private String parcela;
			private double valorOriginal, valorPago;
			private Calendar vencimentoOriginal, dataPagamento;
			
			public Item(String parc, double valO, double valP, Calendar vencO, Calendar dataP){
				parcela = parc; valorOriginal = valO; valorPago = valP; vencimentoOriginal = vencO; dataPagamento = dataP;
			}
			public String toString(){
				return "Parcela : " + parcela + "\tValorOriginal : " + valorOriginal + "\tValorPago : " + valorPago +
							"\tVencimento Original : " + vencimentoOriginal + "\tData Pagamento : " + dataPagamento;
			}
		}
		
		public Acordo(String devedor, String formaAtualizacao, Calendar dataAssinatura, Calendar inicio,
						Calendar fim, double valorTotal, double valorParcela, int numParcelas){
			this.devedor = devedor; this.formaAtualizacao = formaAtualizacao; this.dataAssinatura = dataAssinatura;
			this.inicio = inicio; this.fim = fim; this.valorTotal = valorTotal; this.valorParcela = valorParcela;
			this.numParcelas = numParcelas;
			debitosInclusos = null; //chamar função para criar debitos
			tabela = null; //chamar função para criar tabela
		}
		
		public void addItemTabela(String parcela, double valO, double valP, Calendar vencO, Calendar dataP){
			tabela.add(new Item(parcela,valO,valP,vencO,dataP));
		}
						
		public void addItemDebitos(Calendar d){
			debitosInclusos.add(d);
		}
		
		public String toString(){
			String msg = "ACORDO:\nDevedor : " + devedor + "\nForma Atualização : " + formaAtualizacao +
					"\nData Assinatura : " + dataAssinatura + "\nInicio : " + inicio + "\nFim : " + fim +
					"\nValor Total : " + valorTotal + "\nValor Parcela : " + valorParcela +
					"Número Parcelas : " + numParcelas + "\nDebitos Inclusos :\n";
			for(int i=0; i<debitosInclusos.size(); i++){
				msg += "Debito : " + debitosInclusos.get(i);
			}
			msg += "Tabela : \n";
			for(int i=0; i<tabela.size(); i++){
				msg += "Item : " + tabela.get(i);
			}
			return msg;
		}
	}

	
	public Acordos(){
		listaAcordos = null;
	}
	
	public void addAcordo(String devedor, String formaAtualizacao, Calendar dataAssinatura, Calendar inicio,
						Calendar fim, double valorTotal, double valorParcela, int numParcelas){
		Acordo acordo = new Acordo(devedor,formaAtualizacao,dataAssinatura,inicio,fim,valorTotal,valorParcela,numParcelas);
		listaAcordos.add(acordo);
	}
	
	public void addItemTabela(int numAcordo,String parcela, double valO, double valP, Calendar vencO, Calendar dataP){
		listaAcordos.get(numAcordo).addItemTabela(parcela, valO, valP, vencO, dataP);
	}
	
	public void addItemDebitos(int numAcordo,Calendar c){
		listaAcordos.get(numAcordo).addItemDebitos(c);
	}
	
	public String toString(){
		String msg = "ACORDOS\n";
		for(int i=0; i<listaAcordos.size(); i++){
			msg += "ACORDO :\n" + listaAcordos.get(i);
		}
		return msg;
	}
}