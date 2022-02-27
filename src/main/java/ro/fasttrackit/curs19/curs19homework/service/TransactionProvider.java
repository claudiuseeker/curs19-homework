package ro.fasttrackit.curs19.curs19homework.service;

import org.springframework.stereotype.Component;
import ro.fasttrackit.curs19.curs19homework.model.Transaction;
import ro.fasttrackit.curs19.curs19homework.model.TransactionType;

import java.util.List;

@Component
public class TransactionProvider {

    public List<Transaction> getTransactions(){
        return List.of(
                new Transaction(1, "Masina", TransactionType.SELL, 20000),
                new Transaction(2,"Avion", TransactionType.BUY, 550000),
                new Transaction(3,"Motocicleta", TransactionType.BUY, 30000),
                new Transaction(4,"Barca", TransactionType.BUY, 80540),
                new Transaction(5,"Yacht", TransactionType.SELL, 999999)
        );
    }
}
