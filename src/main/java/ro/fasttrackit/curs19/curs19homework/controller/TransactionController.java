package ro.fasttrackit.curs19.curs19homework.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs19.curs19homework.exception.ResourceNotFoundException;
import ro.fasttrackit.curs19.curs19homework.model.Transaction;
import ro.fasttrackit.curs19.curs19homework.service.TransactionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service){
        this.service= service;
    }

    @GetMapping
    List<Transaction> getTransactions(){
        return service.getAll();
    }
    @GetMapping({"id"})
    Optional<Transaction> getTransactionById(@PathVariable int id){
        return Optional.ofNullable(service.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can t find " + id)));
    }

    @PostMapping
    Transaction addTransaction(@RequestBody Transaction transaction){
        return service.add(transaction);
    }

    @PutMapping({"id"})
    Transaction replaceTransaction(@PathVariable int id, @RequestBody Transaction transaction){
        return service.replace(id, transaction)
                .orElseThrow(() -> new ResourceNotFoundException("Can t find country with id"+id));
    }

    @DeleteMapping({"id"})
    Transaction deleteTransaction(@PathVariable int id){
        return service.delete(id)
                .orElse(null);
    }
}
