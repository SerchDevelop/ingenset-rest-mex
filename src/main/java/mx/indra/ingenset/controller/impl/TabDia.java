package mx.indra.ingenset.controller.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.indra.ingenset.bean.FechaBean;
import mx.indra.ingenset.bean.TabBean;

@Controller
@RequestMapping("/api/tab")
public class TabDia {
	
	static final Logger logger = Logger.getLogger(TabDia.class);
	
	@RequestMapping(value = "/dias/{diasDiff}/{fechaInicio}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<TabBean> mercado(
				@PathVariable(value = "diasDiff") Integer diasDiff,
				@PathVariable(value = "fechaInicio") String fechaInicio) throws ParseException {
		
		logger.info("Controller :: TabDia");
		logger.info("params :: diasDiff :: " + diasDiff + "  :: " + fechaInicio);
		
		int contador = 1;
		List<TabBean> tabs = new ArrayList<TabBean>();
		TabBean aux = new TabBean();
		aux.setTitle(formatDate(fechaInicio).getFechaTab());
		aux.setContent("views/oferta/tab1.html");
		aux.setFechaTabla(formatDate(fechaInicio).getFechaLong());
		aux.setFechaSimple(fechaInicio);
		aux.setFechaExport(formatDate(fechaInicio).getFechaExport());
		tabs.add(aux);
		String fechaAux = fechaInicio;
		while(contador <= diasDiff){
			aux = new TabBean();
			fechaAux = fechaDiaSiguiente(fechaAux);
			aux.setTitle(formatDate(fechaAux).getFechaTab());
			aux.setContent("views/oferta/tab" + (contador+1) + ".html");
			aux.setFechaTabla(formatDate(fechaAux).getFechaLong());
			aux.setFechaSimple(fechaAux);
			aux.setFechaExport(formatDate(fechaAux).getFechaExport());
			tabs.add(aux);
			contador++;
		}
		
/*		for (TabBean tabBean : tabs) {
			logger.info("Fecha Tab :: " + tabBean.getFechaSimple());
			logger.info("Fecha export :: " + tabBean.getFechaExport());
		}
*/		
		return tabs;
	}
	
	protected static FechaBean formatDate(String fechaString) throws ParseException{
		FechaBean fechaBean = new FechaBean();
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy", new Locale("es"));
		Date fecha = formateador.parse(fechaString);
		SimpleDateFormat dateFormatLong = new SimpleDateFormat("EEEE d 'de' MMMM yyyy", new Locale("es"));
		SimpleDateFormat dateFormatTab = new SimpleDateFormat("EEE d MMM yyyy", new Locale("es"));
		SimpleDateFormat dateExport = new SimpleDateFormat("YYYYMMdd",  new Locale("es"));
		fechaBean.setFecha(fechaString);
		fechaBean.setFechaLong(dateFormatLong.format(fecha));
		fechaBean.setFechaTab(dateFormatTab.format(fecha));
		fechaBean.setFechaExport(dateExport.format(fecha));
		return fechaBean;
	}
	
	protected static String fechaDiaSiguiente(String fechaActual){
		
		String formatDate[] = fechaActual.split("-");
		Integer dia = Integer.parseInt(formatDate[0]);
		Integer mes = Integer.parseInt(formatDate[1]);
		Integer anio =  Integer.parseInt(formatDate[2]);
		
		if(dia == 28 && mes == 2){
			if((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0))){
				dia = dia + 1;
				return dia.toString() + "-" + mes.toString() + "-" + anio.toString();	
			}else{
				dia = 1;
				mes = mes + 1;
				return dia.toString() + "-" + mes.toString() + "-" + anio.toString();				
			}
		}
		
		if(dia == 30){
			dia = dia + 1;
			if(mes == 4 || mes == 6 || mes == 9 || mes == 11){
				dia = 1;
				mes = mes + 1;
			}else if (mes == 12){
				mes = 1;
				anio = anio + 1;
			}else{
				dia = dia + 1;
			}
		}else if (dia == 31){
			if(mes < 12){
				dia = 1;
				mes = mes + 1;
			}else{
				mes = 1;
				anio = anio + 1;
			}
		}else{
			dia = dia + 1;
		}
		
		return dia.toString() + "-" + mes.toString() + "-" + anio.toString();
	}	
	
	// Suma los días recibidos a la fecha  
	 public Calendar sumarRestarDiasFecha(Date fecha, int dias){
		 
	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(fecha); // Configuramos la fecha que se recibe
	      calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
	      return calendar; // Devuelve el objeto Date con los nuevos días añadidos
	      
	 }


}
