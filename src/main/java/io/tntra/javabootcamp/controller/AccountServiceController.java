package io.tntra.javabootcamp.controller;

import io.tntra.javabootcamp.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class AccountServiceController {
    private static HashMap<String, Account> accountMap = new HashMap<>();

    @PostMapping(value = "/account")
    public ResponseEntity<Object> createAccount(@RequestBody Account account){
        accountMap.put(account.getOwnerName(),account);
        return new ResponseEntity<>("Account created successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/account")
    public ResponseEntity<Object> getAllAccount(){
        return new ResponseEntity<>(accountMap,HttpStatus.OK);
    }

    @GetMapping(value = "/account/{ownerName}")
    public ResponseEntity<Object> getAccount(@PathVariable("ownerName") String ownerName){
        if(accountMap.containsKey(ownerName)){
            return new ResponseEntity<>(accountMap.get(ownerName),HttpStatus.OK);
        }
        return new ResponseEntity<>("No such account owner",HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/account/balance/{ownerName}")
    public ResponseEntity<Object> getAccountBalance(@PathVariable("ownerName") String ownerName){
        if(accountMap.containsKey(ownerName)){
            return new ResponseEntity<>("The user has balance of "+accountMap.get(ownerName).getBalance(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>("No such account owner",HttpStatus.BAD_REQUEST);
    }

}
