package mx.indra.ingenset.bean;

import java.util.Date;

public class Fenix 
{
	private String hora;
	private String total;
	private String precio;
	private String montoNeto;
	private String dia;
	private String iva;
	private String estatus;
	
	public String getHora() 
	{
		return hora;
	}
	
	public void setHora(String hora) 
	{
		this.hora = hora;
	}
	
	public String getPrecio() 
	{
		return precio;
	}
	
	public void setPrecio(String precio) 
	{
		this.precio = precio;
	}
	
	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getMontoNeto() {
		return montoNeto;
	}

	public void setMontoNeto(String montoNeto) {
		this.montoNeto = montoNeto;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	
}
