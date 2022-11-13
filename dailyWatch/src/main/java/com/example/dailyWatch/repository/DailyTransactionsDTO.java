package com.example.dailyWatch.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.dailyWatch.model.DailyTransaction;


@Repository
public interface DailyTransactionsDTO extends CrudRepository<DailyTransaction, String> {
	
	@Query(value = "SELECT * FROM daily_transaction dlw WHERE dlw.company_name= :companyName and date_of_transaction =:date", nativeQuery=true)
	List<DailyTransaction> showTransactionHistory(String companyName, Date date);

}
