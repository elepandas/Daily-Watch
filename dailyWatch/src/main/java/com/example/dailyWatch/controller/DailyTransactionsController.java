package com.example.dailyWatch.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dailyWatch.service.DailyTransactionsService;

@RestController
public class DailyTransactionsController {
	@Autowired
	private DailyTransactionsService dts;
	
	@RequestMapping(path = "feedDailyTransactions")
	public void setDataInDb() throws IOException {
		dts.saveDailyTransactions();
	}

}
