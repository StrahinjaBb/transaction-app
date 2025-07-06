package com.example.transaction;

import com.example.transaction.repository.CsvTransactionRepository;
import com.example.transaction.service.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransactionBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionBackendApplication.class, args);
	}

}
