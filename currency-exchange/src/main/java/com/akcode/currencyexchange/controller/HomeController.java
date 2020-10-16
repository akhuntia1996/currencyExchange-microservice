package com.akcode.currencyexchange.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.akcode.currencyexchange.model.CurrExchange;
import com.akcode.currencyexchange.repository.CurrExRepo;
import com.fasterxml.jackson.databind.deser.ValueInstantiator.Gettable;

@RestController
public class HomeController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private CurrExRepo repo;
	
	@GetMapping("curr-ex/from/{from}/to/{to}")
	public CurrExchange getData(@PathVariable String from, 
								@PathVariable String to) {
		CurrExchange currExchange = new CurrExchange(1000,from,to,65);
		currExchange.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		return currExchange;
	}
	
	@GetMapping("curr-ex2/from/{from}/to/{to}")
	public CurrExchange getData2(@PathVariable String from, 
								@PathVariable String to) {
		System.out.println("Hitting the URL");
		removeAll();
		addData();
		return displayData(from,to);
	}

	private CurrExchange displayData(String from, String to) {
		// TODO Auto-generated method stub
		System.out.println("Displaying ....");
		List<CurrExchange> listCurrEx = repo.findAll();
		for(int i=0;i<listCurrEx.size();i++) {
			System.out.println("......"+listCurrEx.get(i).getTo());
			if(to.equals(listCurrEx.get(i).getTo()))
				return listCurrEx.get(i);
		}
		return null;
	}

	private void addData() {
		// TODO Auto-generated method stub
		System.out.println("Adding........");
		CurrExchange currExchange = new CurrExchange(1000,"INF","AAA",65);
		currExchange.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		repo.save(currExchange);
		currExchange = new CurrExchange(2000,"INF","BBB",66);
		currExchange.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		repo.save(currExchange);
		currExchange = new CurrExchange(3000,"INF","CCC",67);
		currExchange.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		repo.save(currExchange);
		System.out.println(repo.findAll());
	}

	private void removeAll() {
		// TODO Auto-generated method stub
		System.out.println("Removing .......");
		repo.deleteAll();
	}
}