import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Grafico extends ApplicationFrame implements Observer{
	private List<Observer> observadores = null;

	private final static XYSeries serieHumanos = new XYSeries("Humanos");
	private final static XYSeries serieZumbis = new XYSeries("Zumbis");
	private XYSeriesCollection data =null;

	private static ChartPanel chartPanel = null;
	public ChartPanel getGrafico() { return chartPanel; }
	
	public Grafico( String applicationTitle , String chartTitle )
	{
		super(applicationTitle);
		Tabuleiro.getInstance().addObserver(this);
		
		data = new XYSeriesCollection();
		data.addSeries(serieZumbis);
		data.addSeries(serieHumanos);

		final JFreeChart chart = ChartFactory.createXYLineChart("","Passos", 
				"Número de Personagens", 
				data,
				PlotOrientation.VERTICAL,
				true,
				true,
				false
				);

		chartPanel =  new ChartPanel( chart );
		chartPanel.setPreferredSize( new java.awt.Dimension( 560 ,500 ) );
		setContentPane( chartPanel );
	}

	public void addObserver(Observer b){
		observadores.add(b);
	}
	
	public void notifyObservers(){
		for(Observer b : observadores){
			b.update(null,null);
		}
	}

	public static void limparGrafico(){ 
		serieHumanos.clear();
		serieZumbis.clear();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		int jogada = Tabuleiro.getInstance().getNumeroJogada();
		int numHum = Tabuleiro.getInstance().getNumeroHumanos();
		int numZum = Tabuleiro.getInstance().getNumeroZumbis();
		
		serieHumanos.add(jogada,numHum);
		serieZumbis.add(jogada,numZum);
	}
	
}
