import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LineChart_AWT extends ApplicationFrame
{
	private XYSeriesCollection data =null;
   public LineChart_AWT( String applicationTitle , String chartTitle )
   {
	   super(applicationTitle);
	   data = new XYSeriesCollection();
	   fillXYSeries();
	
	   final JFreeChart chart = ChartFactory.createXYLineChart(
	            "Most Updated Tables",
	            "Version ID", 
	            "Number of Changes", 
	            data,
	            PlotOrientation.VERTICAL,
	            true,
	            true,
	            false
	    );
         
      ChartPanel chartPanel = new ChartPanel( chart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      setContentPane( chartPanel );
   }

   private DefaultCategoryDataset createDataset( )
   {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      dataset.addValue( 15 , "schools" , "1970" );
      dataset.addValue( 30 , "schools" , "1980" );
      dataset.addValue( 60 , "schools" ,  "1990" );
      dataset.addValue( 120 , "schools" , "2000" );
      dataset.addValue( 240 , "schools" , "2010" );
      dataset.addValue( 300 , "schools" , "2014" );
      return dataset;
   }
   
   private void fillXYSeries(){

	    data.removeAllSeries();
/*
	    for(int i=0;i<tables.size();i++){

	        final XYSeries series = new XYSeries(tables.get(i).getName());

	        for(int j=0;j<mostIntensiveTables.get(i).getChangesForChart().size();j++){
	            series.add(j,mostIntensiveTables.get(i).getChangesForChart().get(j));
	        }

	        int found=0;
	        for(int k=0;k<data.getSeriesCount();k++){
	            if(data.getSeries(k)==series){
	                found=1;
	                break;
	            }
	        }

	        if(found==0){
	            data.addSeries(series);
	        }
	    }
*/
	    
	    final XYSeries series = new XYSeries("Form 1");
	    series.add(100,50);
	    series.add(50,1000);
	    data.addSeries(series);
	    final XYSeries serie2 = new XYSeries("Form 2");
	    serie2.add(0,50);
	    serie2.add(300,600);
	    data.addSeries(serie2);
	}
   public static void main( String[ ] args ) 
   {
      LineChart_AWT chart = new LineChart_AWT(
      "School Vs Years" ,
      "Numer of Schools vs years");

      chart.pack( );
      RefineryUtilities.centerFrameOnScreen( chart );
      chart.setVisible( true );
   }
}