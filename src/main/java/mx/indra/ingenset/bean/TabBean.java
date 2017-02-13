package mx.indra.ingenset.bean;

import java.io.Serializable;

public class TabBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String content;
	private String fechaTabla;
	private String fechaSimple;
	private String fechaExport;
	
	public TabBean(){
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFechaTabla() {
		return fechaTabla;
	}

	public void setFechaTabla(String fechaTabla) {
		this.fechaTabla = fechaTabla;
	}

	public String getFechaSimple() {
		return fechaSimple;
	}

	public void setFechaSimple(String fechaSimple) {
		this.fechaSimple = fechaSimple;
	}

	public String getFechaExport() {
		return fechaExport;
	}

	public void setFechaExport(String fechaExport) {
		this.fechaExport = fechaExport;
	}

}
