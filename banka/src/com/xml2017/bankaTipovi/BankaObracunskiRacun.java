package com.xml2017.bankaTipovi;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BankaObracunskiRacun {
	
	@XmlElement(name = "broj-obracunskog", required = true, defaultValue = "000-0000000000000-00")
    protected String brojObracunskog;
	
	@XmlElement(name = "banka-port")
	protected String bankaPort;
	
	@XmlElement(name = "swift")
	protected String swiftKod;
	
	@XmlElement(name = "stanje")
	protected BigDecimal stanje;

	public String getBrojObracunskog() {
		return brojObracunskog;
	}

	public void setBrojObracunskog(String brojObracunskog) {
		this.brojObracunskog = brojObracunskog;
	}

	public String getBankaPort() {
		return bankaPort;
	}

	public void setBankaPort(String bankaPort) {
		this.bankaPort = bankaPort;
	}

	public BigDecimal getStanje() {
		return stanje;
	}

	public void setStanje(BigDecimal stanje) {
		this.stanje = stanje;
	}

	public String getSwiftKod() {
		return swiftKod;
	}

	public void setSwiftKod(String swiftKod) {
		this.swiftKod = swiftKod;
	}
	
}
