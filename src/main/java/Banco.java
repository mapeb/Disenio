import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Assert;

/*Date date = new Date();   // given date
Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
calendar.setTime(date);   // assigns calendar to given date 
calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
calendar.get(Calendar.HOUR);        // gets hour in 12h format
calendar.get(Calendar.MONTH);       // gets month number, NOTE this is zero based!*/



public class Banco extends POI {

	
	public boolean estaDisponible(Date horario){
		//Date date = new Date();   // given date
		Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
		calendar.setTime(horario);   // assigns calendar to given date 
		int hora = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
		return(10 < hora && hora < 15 );
	}
		/*Date diaReferencia = new Date();
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd.MM.yyyy");
		String fechadeReferencia=formateador.format(diaReferencia);*/
		
		
		
		
	}
		
}