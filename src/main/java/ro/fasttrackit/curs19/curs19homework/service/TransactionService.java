package ro.fasttrackit.curs19.curs19homework.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.curs19.curs19homework.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final List<Transaction> transactions;

    public TransactionService(TransactionProvider transactionProvider){
        this.transactions= new ArrayList<>(transactionProvider.getTransactions());
    }

    public List<Transaction> getAll(){
        return transactions;
    }
    public Optional<Transaction> getById(int id) {
        return findById(id);

    }

    public Transaction add(Transaction transaction) {
        Transaction newTransaction = cloneWithId(this.transactions.size(), transaction);
        this.transactions.add(newTransaction);
        return newTransaction;
    }

    private Transaction cloneWithId(int id, Transaction transaction) {
        return new Transaction(
                id,
                transaction.product(),
                transaction.type(),
                transaction.amount()
        );
    }

    public Optional<Transaction> replace(int id, Transaction transaction) {
        return findById(id)
                .map(existing -> replaceExistingCountry(id, existing, transaction));
    }
    private Transaction replaceExistingCountry(int id, Transaction existing, Transaction country) {
        this.transactions.remove(existing);
        Transaction newCountry = cloneWithId(id, country);
        this.transactions.add(newCountry);
        return newCountry;
    }

    public Optional<Transaction> findById(int id) {
        return this.transactions.stream()
                .filter(country -> country.id() == id)
                .findFirst();
    }

    public Optional<Transaction> delete(int id) {
        Optional<Transaction> countryToDelete = findById(id);
        countryToDelete.ifPresent(this.transactions::remove);
        return countryToDelete;
    }
}
