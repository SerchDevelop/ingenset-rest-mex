package mx.indra.ingenset.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.indra.ingenset.bean.MercadoBean;
import mx.indra.ingenset.bean.UnidadGeneradoraBean;

public interface IExcelController {
	
	public void exportExcel(List<MercadoBean> mercadoBean, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException;
	
	public void exportUnidadGeneradoraBeanExcel(UnidadGeneradoraBean unidadGeneradoraBean,
			HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException;
	
	public void totalUnidadGeneradoraBean(List<UnidadGeneradoraBean> unidadGeneradoraBean,
			HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException;

}
