package mx.indra.ingenset.bean;

import java.util.Date;

public class PeriodBean {
	
	private Date dateFrom;
	private Date dateTo;

	public String GMTString_DateFrom() {
		return DateGMTBean.DateGMTString(this.dateFrom);
	}

	public String GMTString_DateTo() {
		return DateGMTBean.DateGMTString(this.dateTo);
	}

	public Date getDateFrom() {
		return this.dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return this.dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

}
