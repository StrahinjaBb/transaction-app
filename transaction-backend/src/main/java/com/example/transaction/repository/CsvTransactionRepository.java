package com.example.transaction.repository;

import com.example.transaction.enums.TransactionStatus;
import com.example.transaction.model.Transaction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvTransactionRepository implements TransactionRepository {
    private final String csvFilePath = "src/main/resources/transactions.csv";
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @Override
    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());

            for (CSVRecord record : csvParser) {
                Transaction transaction = new Transaction();
                transaction.setDate(LocalDate.parse(record.get("Transaction Date"), dateFormatter));
                transaction.setAccountNumber(record.get("Account Number"));
                transaction.setAccountHolder(record.get("Account Holder Name"));
                transaction.setAmount(new BigDecimal(record.get("Amount")).doubleValue());
                transaction.setStatus(TransactionStatus.valueOf(record.get("Status").toUpperCase()));

                transactions.add(transaction);
            }
        } catch (IOException ioe) {
            throw new RuntimeException("Failed to read CSV file: " + csvFilePath, ioe);
        }

        return transactions;
    }

    @Override
    public Transaction save(Transaction transaction) {
        try {
            // Proveri da li fajl postoji, ako ne - kreiraj ga
            if (!Files.exists(Paths.get(csvFilePath))) {
                Files.createFile(Paths.get(csvFilePath));
                try (Writer writer = Files.newBufferedWriter(Paths.get(csvFilePath));
                     CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                             .withHeader("Transaction Date", "Account Number",
                                     "Account Holder Name", "Amount", "Status"))) {
                }
            }

            try (Writer writer = Files.newBufferedWriter(Paths.get(csvFilePath), StandardOpenOption.APPEND);
                 CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                         .withSkipHeaderRecord())) {

                csvPrinter.printRecord(
                        transaction.getDate().format(dateFormatter),
                        transaction.getAccountNumber(),
                        transaction.getAccountHolder(),
                        transaction.getAmount().toString(),
                        transaction.getStatus().name()
                );

                csvPrinter.flush();
            }

            return transaction;
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to CSV file: " + csvFilePath, e);
        }
    }

    public String getCsvFilePath() {
        return csvFilePath;
    }
}
