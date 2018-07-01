import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class CurrencyConvertor {
	private String Source;
	private String Target;
	private double ExchangeRate;
	private double Amount;
	
	public CurrencyConvertor( String Source, String Target, double Amount ) {
		this.Source = Source;
		this.Target = Target;
		this.Amount = Amount;
	}
	
	public void SetExchangeRate() throws IOException {
	    String number;
	    Double n = 1.169038;
	    int i;
	    // Make a URL to the web page
	    String url_s = "http://free.currencyconverterapi.com/api/v5/convert?q=EUR_USD&compact=y";
	    String SourceTarget = Source + "_" + Target;	    
	    String url_r = url_s.replace("EUR_USD", SourceTarget);  
	    
	    //System.out.println(SourceTarget);
	    //System.out.println(url_r); 	   
	    
        URL url = new URL(url_r);

        // Get the input stream through URL Connection
        URLConnection con = url.openConnection();
        InputStream is =con.getInputStream();

        // Once you have the Input Stream, it's just plain old Java IO stuff.

        // For this case, since you are interested in getting plain-text web page
        // I'll use a reader and output the text content to System.out.

        // For binary content, it's better to directly read the bytes from stream and write
        // to the target file.


        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;

        // read each line and write to System.out
        while ((line = br.readLine()) != null) {
            i = line.indexOf("val");
            number = line.substring(i+5, line.length()-2);
            n = Double.parseDouble(number);
            //System.out.println(line.length()-2);  
            //System.out.println(number); 
            //System.out.println(line);
            System.out.println("The exchange rate for " + SourceTarget+ " is " + n);  
            
            
            	
            }	
	    
		  this.ExchangeRate = n;
		
		
	}
	
	public double ConvertAmount() {
		double ConvertedValue = 1;		
		
		ConvertedValue = this.Amount * this.ExchangeRate;
		return ConvertedValue;
		
		
	}	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner console = new Scanner(System.in); 
		System.out.print("Enter the Amount: "); 
		double Amount =   console.nextDouble();  
		System.out.print("Enter the Source Currency: "); 
		String Source =   console.next();
		System.out.print("Enter the Target Currency: "); 
		String Target =   console.next();		
				
		CurrencyConvertor CurrCon = new CurrencyConvertor(Source, Target, Amount);
		try {
		
		CurrCon.SetExchangeRate();
		}catch(IOException IO) {
		  System.out.println("IO Exception has occured");
		}
		double ConvertedValue = CurrCon.ConvertAmount();
		System.out.println("After Currency Conversion from " + Source+ " to " + Target + ", the amount is " + ConvertedValue + ".");
		
		
		

	}
	
	

}
