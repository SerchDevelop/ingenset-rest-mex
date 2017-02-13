package mx.indra.ingenset.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import mx.indra.ingenset.bean.CenaceBean;
import mx.indra.ingenset.bean.FenixBean;
import mx.indra.ingenset.bean.ResultadosBean;
import mx.indra.ingenset.service.IDatosService;
import mx.indra.ingenset.utils.ConectionDataBase;

@Service("serviceDatos")
public class DatosService implements IDatosService{
	
	static final Logger logger = Logger.getLogger(DatosService.class);
	
	public List<ResultadosBean> buscarDatosTablas(String identificador) throws SQLException {
		ConectionDataBase dataBase = new ConectionDataBase();
		Connection conexion = dataBase.getConection();
		ArrayList<ResultadosBean> lstResultados = new ArrayList<ResultadosBean>();
		ResultadosBean res = new ResultadosBean();
		
		String queryFenix = "select * from INGEN_SET_MARKET.TEST_FENIX "
						  +	"WHERE ID_IDENTIFICADOR_FENIX = '" + identificador + "' ";
		
		System.out.println("queryFenix: " + queryFenix);
		Statement stDatos = conexion.createStatement();
		ResultSet rsDatosFenix = stDatos.executeQuery(queryFenix);
			 
		if(rsDatosFenix != null)
		{
	       while(rsDatosFenix.next()) 
	       {
	          FenixBean fx = new FenixBean();
		      CenaceBean cenace = new CenaceBean();
		     		   
		      Date dia = rsDatosFenix.getDate("FECHA");
		      String pattern = "dd/MM/yyyy";
		      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		      String date = simpleDateFormat.format(dia);
		   
		      fx.setDia(date);
		      fx.setIdIdentificadorFenix(rsDatosFenix.getString("ID_IDENTIFICADOR_FENIX"));
		  	   
		      // buscar en cenace  *************
		      cenace = getObtenerDatosCenace(fx.getIdIdentificadorFenix(),fx.getDia());
		      
		     if(cenace != null)
		     {
	   		    fx.setMontoNeto(rsDatosFenix.getString("MONTO_FENIX"));
		        fx.setIva(rsDatosFenix.getString("IVA_FENIX"));
		        fx.setTotal(rsDatosFenix.getString("TOTAL_FENIX"));
		        fx.setEstatus((rsDatosFenix.getString("ESTATUS_FENIX")));
     	        fx.setDescIdentificadorFenix(rsDatosFenix.getString("DESC_IDENTIFICADOR_FENIX"));
       	        
     	       System.out.println("id fenix:" + fx.getMontoNeto());
     	      
     	        res.setDiaFenix(fx.getDia());
     	        res.setDiaCenace(cenace.getDia());
     	        res.setIdIdentificadorFenix(fx.getIdIdentificadorFenix());
     	        res.setIdIdentificadorCenace(cenace.getIdIdentificadorCenace());
     	        res.setDescIdentificadorFenix(fx.getDescIdentificadorFenix());
     	        res.setDescIdentificadorCenace(cenace.getDescIdentificadorCenace());
     	        res.setMontoNetoFenix(fx.getMontoNeto());
     	        res.setMontoNetoCenace(cenace.getMontoNeto());
     	        res.setIvaFenix(fx.getIva());
     	        res.setIvaCenace(cenace.getIva());
     	        res.setTotalFenix(fx.getTotal());
     	        res.setTotalCenace(cenace.getTotal());
     	        res.setEstatusFenix(fx.getEstatus());
     	        res.setEstatusCenace(cenace.getEstatus());
     	        
     	      // System.out.println("monto fenix:" + res.getMontoNetoFenix());
     	       
		      }
		     
		     lstResultados.add(res);
		     
		   }  // fin while
	       
	      
		}     // fin if rsDatos
		
		
		
		for(int i=0; i < lstResultados.size();i++)
		{
			System.out.println(lstResultados.get(i).getIvaCenace());
			System.out.println(lstResultados.get(i).getIvaFenix());
			System.out.println(lstResultados.get(i).getMontoNetoFenix());
			System.out.println(lstResultados.get(i).getMontoNetoCenace());
		}
		System.out.println();
		
		
		
		      
		rsDatosFenix.close();
		conexion.close();  

		return lstResultados;
	}
	
	public CenaceBean getObtenerDatosCenace(String identificador, String fecha) 
	{
		CenaceBean cenace = new CenaceBean();
		
		try{
		      Connection conexion;
		
		      Class.forName("oracle.jdbc.OracleDriver"); 
		      String BaseDeDatos = "jdbc:oracle:thin:@129.152.42.208:1521:FENIXDEV"; 
		      conexion = DriverManager.getConnection(BaseDeDatos, "SYSTEM","zBvv6TWrFWv3hRpnGTrySeve2M_LFO");
		      
		      
		      String query = "select * from INGEN_SET_MARKET.TEST_CENACE "
			  				  + "WHERE ID_IDENTIFICADOR_CENACE = '" + identificador + "' "
			  				  + "AND FECHA = '" + fecha + "'";
		      
		      System.out.println("Query Cenace: " + query);
		      Statement st = conexion.createStatement();
			  ResultSet rs = st.executeQuery(query);
				 
			  if(rs != null)
			  {
				  while(rs.next())
				  {
					 Date dia = rs.getDate("FECHA");
				     String pattern = "dd/MM/yyyy";
				     SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
     			     String date = simpleDateFormat.format(dia);
						 			 
				     cenace.setDia(date);
				     cenace.setMontoNeto(rs.getString("MONTO_CENACE"));
				     cenace.setIva(rs.getString("IVA_CENACE"));
				     cenace.setTotal(rs.getString("TOTAL_CENACE"));
				     cenace.setEstatus((rs.getString("ESTATUS_CENACE")));
				     cenace.setIdIdentificadorCenace(rs.getString("ID_IDENTIFICADOR_CENACE"));
				     cenace.setDescIdentificadorCenace(rs.getString("DESC_IDENTIFICADOR_CENACE"));
				  }
			  }
		  
			  rs.close();
			  conexion.close();  
		
		} catch(SQLException sql) {
			sql.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return cenace;
		
	}
	
	
	// CON CONEXION
	public List<ResultadosBean> getObtenerDatos() throws SQLException {
		 
		ArrayList<ResultadosBean> res = new ArrayList<ResultadosBean>();
  		ArrayList<FenixBean> fenix = new ArrayList<FenixBean>();
	    ArrayList<CenaceBean> cenace = new ArrayList<CenaceBean>();
	    
	    fenix = this.getObtenerDatosFenix();
	    cenace = this.getObtenerDatosCenace();
		    
	    for(int i=0; i < fenix.size(); i++){
	    
	    	ResultadosBean r = new ResultadosBean();
	    	
	    	String diaFenix = fenix.get(i).getDia();
	    	String diaCenace = cenace.get(i).getDia();
	    	String montoNetoFenix = fenix.get(i).getMontoNeto();
	    	String montoNetoCenace = cenace.get(i).getMontoNeto();
	    	String ivaFenix = fenix.get(i).getIva();
	    	String ivaCenace = cenace.get(i).getIva();
	    	String totalFenix = fenix.get(i).getTotal();
	    	String totalCenace = cenace.get(i).getTotal();
	    	String estatusFenix = fenix.get(i).getEstatus();
	    	String estatusCenace = cenace.get(i).getEstatus();
	    	String identificadorFenix = fenix.get(i).getIdIdentificadorFenix();
	    	String descFenix = fenix.get(i).getDescIdentificadorFenix();
	    	String identificadorCenace = cenace.get(i).getIdIdentificadorCenace();
	    	String descCenace = cenace.get(i).getDescIdentificadorCenace();
	    	
	    	r.setDiaFenix(diaFenix);
	    	r.setDiaCenace(diaCenace);
	    	r.setMontoNetoFenix(montoNetoFenix);
	    	r.setMontoNetoCenace(montoNetoCenace);
	    	r.setIvaFenix(ivaFenix);
	    	r.setIvaCenace(ivaCenace);
	    	r.setTotalFenix(totalFenix);
	    	r.setTotalCenace(totalCenace);
	    	r.setEstatusFenix(estatusFenix);
	    	r.setEstatusCenace(estatusCenace);
	    	r.setIdIdentificadorFenix(identificadorFenix);    
	    	r.setDescIdentificadorFenix(descFenix);
	    	r.setIdIdentificadorCenace(identificadorCenace);    
	    	r.setDescIdentificadorCenace(descCenace);
	    	
	    	res.add(r);
  	
	    }
		
		//resultados1 = compararDatos(f1,c1);
		//resultados2 = compararDatos(f2,c2);
    			
    	return res;
    	
    }
	
	public ArrayList<FenixBean> getObtenerDatosFenix() throws SQLException {
		
		ConectionDataBase dataBase = new ConectionDataBase();
		ArrayList<FenixBean> listadoFenix = new ArrayList<FenixBean>();
		Connection conexion = dataBase.getConection();
		
	      Statement st = conexion.createStatement();
		  ResultSet rs = st.executeQuery("select * from INGEN_SET_MARKET.TEST_FENIX");
			 
		  while(rs.next()) {
			 FenixBean fx = new FenixBean();
			 
			 Date dia = rs.getDate("FECHA");
			 String pattern = "dd/MM/yyyy";
			 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				 
			 String date = simpleDateFormat.format(dia);
					 			 
			 fx.setDia(date);
			 fx.setMontoNeto(rs.getString("MONTO_FENIX"));
			 fx.setIva(rs.getString("IVA_FENIX"));
			 fx.setTotal(rs.getString("TOTAL_FENIX"));
			 fx.setEstatus((rs.getString("ESTATUS_FENIX")));
				 			 
			 listadoFenix.add(fx);
		}
		      
			rs.close();
			conexion.close();  
		
		return listadoFenix;
		
	}
	
	public ArrayList<CenaceBean> getObtenerDatosCenace() {
		
		ArrayList<CenaceBean> listadoCenace = new ArrayList<CenaceBean>();
		
		try{
		      Connection conexion;
		
		      Class.forName("oracle.jdbc.OracleDriver"); 
		      String BaseDeDatos = "jdbc:oracle:thin:@129.152.42.208:1521:FENIXDEV"; 
		      conexion = DriverManager.getConnection(BaseDeDatos, "SYSTEM","zBvv6TWrFWv3hRpnGTrySeve2M_LFO");
		      
		      Statement st = conexion.createStatement();
			  ResultSet rs = st.executeQuery("select * from INGEN_SET_MARKET.TEST_CENACE");
				 
			  while(rs.next())
			  {
				 CenaceBean cx = new CenaceBean();
				 
				 Date dia = rs.getDate("FECHA");
				 String pattern = "dd/MM/yyyy";
				 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					 
				 String date = simpleDateFormat.format(dia);
						 			 
				 cx.setDia(date);
				 cx.setMontoNeto(rs.getString("MONTO_CENACE"));
				 cx.setIva(rs.getString("IVA_CENACE"));
				 cx.setTotal(rs.getString("TOTAL_CENACE"));
				 cx.setEstatus((rs.getString("ESTATUS_CENACE")));
					 			 
				 listadoCenace.add(cx);
			}
		      
			rs.close();
			conexion.close();  
		
		} catch(SQLException sql) {
			sql.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return listadoCenace;
		
	}
	
	// ********************************++
	// DUMMYS
	public ArrayList<ResultadosBean> getObtenerDatos2() {
		
		ArrayList<ResultadosBean> res = new ArrayList<ResultadosBean>();
	    ResultadosBean resultados1 = new ResultadosBean();
	    ResultadosBean resultados2 = new ResultadosBean();
	       
    	FenixBean f1 = new FenixBean();
		CenaceBean c1 = new CenaceBean();
		
		FenixBean f2 = new FenixBean();
		CenaceBean c2 = new CenaceBean();
	        			
		f1.setDia("01/01/2016");
		f1.setMontoNeto("1000.00");
		f1.setTotal("5000.17");
    	f1.setIva("3000.00");
	        				
		c1.setDia("07/07/2016");
    	c1.setMontoNeto("2100.33");
		c1.setTotal("5700.89");
		c1.setIva("3000.00");
	        				
		f2.setDia("15/10/2016");
		f2.setMontoNeto("1000.00");
		f2.setTotal("5000.17");
    	f2.setIva("3000.00");
	        				
    	c2.setDia("09/09/2016");
    	c2.setMontoNeto("1000.00");
		c2.setTotal("5000.17");
		c2.setIva("3000.00");
		
		resultados1 = compararDatos(f1,c1);
		resultados2 = compararDatos(f2,c2);
    	
		res.add(resultados1);
		res.add(resultados2);
		
    	return res;
    }
		
	public ResultadosBean compararDatos(FenixBean f,CenaceBean c) {
		
		ResultadosBean res = new ResultadosBean();
		boolean bandera = true;
		
		if(f.getMontoNeto().equals(c.getMontoNeto())) {
						
		} else {
			bandera = false;
		}
		
		if(f.getIva().equals(c.getIva())) {
			
		} else {
			bandera = false;
		}
			
		if(f.getTotal().equals(c.getTotal())) {
			
		} else {
			bandera = false;
		}
		
				
		if(bandera) {
			res.setEstatusCenace("Facturada");
		    res.setEstatusFenix("Aprobada");
		} else {
			res.setEstatusFenix("Reclamacion");
			res.setEstatusCenace("Liquidada");
		}
			
		res.setMontoNetoFenix(f.getMontoNeto());
		res.setMontoNetoCenace(c.getMontoNeto());
		
		res.setIvaFenix(f.getIva());
		res.setIvaCenace(c.getIva());
		
		res.setTotalFenix(f.getTotal());
		res.setTotalCenace(c.getTotal());
		
		res.setDiaFenix(f.getDia());
		res.setDiaCenace(c.getDia());
			
		return res;	
	}


}
