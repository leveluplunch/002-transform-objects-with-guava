package com.levelup.tutorials;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Enums;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;

public class TranformObjectsTest {

	@Test
	public void transform_string_to_enum () {
		
		List<String> days = Lists.newArrayList(
				"WEDNESDAY", 
				"SUNDAY", 
				"MONDAY", 
				"WEDNESDAY");
		
//		Enums.valueOfFunction(Day.class);
		Function<String, Day> stringToDayEnum = Enums.stringConverter(Day.class);
		
		Iterable<Day> daysAsEnum = Iterables.transform(days, stringToDayEnum);
		
		for (Day day : daysAsEnum) {
			System.out.println(day.getClass());
		}
	}
	
	
	// in release canidate 16 you will want to update to stringConverter
	@Test
	public void convert_tdinvestment_etradeinvestment () {
		
		List<TdAmeritradeInvestment> tdInvestments = Lists.newArrayList();
		tdInvestments.add(new TdAmeritradeInvestment(555, "Facebook Inc", 57.51));
		tdInvestments.add(new TdAmeritradeInvestment(123, "Micron Technology, Inc.", 21.29));
		tdInvestments.add(new TdAmeritradeInvestment(456, "Ford Motor Company", 15.31));
		tdInvestments.add(new TdAmeritradeInvestment(236, "Sirius XM Holdings Inc", 3.60));
		
		// convert a list of objects
		Function<TdAmeritradeInvestment, ETradeInvestment> tdToEtradeFunction = new Function<TdAmeritradeInvestment, ETradeInvestment>() {
			
			public ETradeInvestment apply(TdAmeritradeInvestment input) {
				ETradeInvestment investment = new ETradeInvestment();
				investment.setKey(Ints.stringConverter().reverse().convert(input.getInvestmentKey()));
				investment.setName(input.getInvestmentName());
				investment.setPrice(new BigDecimal(input.getInvestmentPrice()));
				return investment;
			}
		};

		List<ETradeInvestment> etradeInvestments = Lists.transform(tdInvestments, tdToEtradeFunction);
		
		System.out.println(etradeInvestments);
		
		// convert object
		ETradeInvestment faceBookInvestment = tdToEtradeFunction.apply(new TdAmeritradeInvestment(555, "Facebook Inc", 57.51));
		
		System.out.println(faceBookInvestment);
		
	}
	
	@Test
	public void transform_list_to_map () {
		
		List<TdAmeritradeInvestment> tdInvestments = Lists.newArrayList();
		tdInvestments.add(new TdAmeritradeInvestment(555, "Facebook Inc", 57.51));
		tdInvestments.add(new TdAmeritradeInvestment(123, "Micron Technology, Inc.", 21.29));
		tdInvestments.add(new TdAmeritradeInvestment(456, "Ford Motor Company", 15.31));
		tdInvestments.add(new TdAmeritradeInvestment(236, "Sirius XM Holdings Inc", 3.60));
		
		ImmutableMap<Integer, TdAmeritradeInvestment> investmentMap = Maps.uniqueIndex(tdInvestments, new Function<TdAmeritradeInvestment, Integer>() {

			public Integer apply(TdAmeritradeInvestment input) {
				return new Integer(input.getInvestmentKey());
			}
		});
		
		System.out.println(investmentMap);
		
	}
	
//	{555=TdAmeritradeInvestment{key=555, name=Facebook Inc, price=57.51}, 
//			123=TdAmeritradeInvestment{key=123, name=Micron Technology, Inc., price=21.29}, 4
//			56=TdAmeritradeInvestment{key=456, name=Ford Motor Company, price=15.31}, 
//			236=TdAmeritradeInvestment{key=236, name=Sirius XM Holdings Inc, price=3.6}}

	
	
}
