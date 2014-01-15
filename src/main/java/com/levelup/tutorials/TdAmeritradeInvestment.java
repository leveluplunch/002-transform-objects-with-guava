package com.levelup.tutorials;

import com.google.common.base.Objects;

/**
 * Class that represents TD Ameritrade investments
 * 
 * @author Justin Musgrove
 */
public class TdAmeritradeInvestment {
	
	private int investmentKey;
	private String investmentName;
	private double investmentPrice;
	
	public TdAmeritradeInvestment(int investmentKey, String investmentName,
			double investmentPrice) {
		super();
		this.investmentKey = investmentKey;
		this.investmentName = investmentName;
		this.investmentPrice = investmentPrice;
	}
	
	public int getInvestmentKey() {
		return investmentKey;
	}
	public void setInvestmentKey(int investmentKey) {
		this.investmentKey = investmentKey;
	}
	public String getInvestmentName() {
		return investmentName;
	}
	public void setInvestmentName(String investmentName) {
		this.investmentName = investmentName;
	}
	public double getInvestmentPrice() {
		return investmentPrice;
	}
	public void setInvestmentPrice(double investmentPrice) {
		this.investmentPrice = investmentPrice;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("key", investmentKey)
				.add("name", investmentName)
				.add("price", investmentPrice)
				.toString();
		
	}
	

}
