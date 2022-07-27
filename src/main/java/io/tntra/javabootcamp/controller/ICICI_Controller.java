package io.tntra.javabootcamp.controller;

import io.tntra.javabootcamp.Exceptionpkg.MinBalanceException;
import io.tntra.javabootcamp.model.Account;
import io.tntra.javabootcamp.services.HDFCService;
import io.tntra.javabootcamp.services.ICICIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class ICICI_Controller {

    @Autowired
    ICICIService iciciService;

    @PostMapping(value = "/icici")
    public ResponseEntity<Object> createAccount(@RequestBody Account account) throws MinBalanceException {
        try{
            iciciService.createAccount(account);
            return new ResponseEntity<>("ICICI Account created successfully", HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Minimum balance for ICICI is 1000", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/icici")
    public ResponseEntity<Object> getAllAccount(){
        return new ResponseEntity<>(iciciService.getAllAccount(),HttpStatus.OK);
    }

    @GetMapping(value = "/icici/{ownerName}")
    public ResponseEntity<Object> getAccount(@PathVariable("ownerName") String ownerName){
        try{
            return new ResponseEntity<>(iciciService.getAccount(ownerName),HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Given account owner does not exist",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/icici/balance/{ownerName}")
    public ResponseEntity<Object> getAccountBalance(@PathVariable("ownerName") String ownerName){
        try{
            BigDecimal balance = iciciService.getAccountBalance(ownerName);
            return new ResponseEntity<>("The given user has balance of "+ balance ,HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Given account owner does not exist",HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value="/icici/deposit/{ownerName}/{amount}")
    public ResponseEntity<Object> depositAmount(@PathVariable("ownerName") String ownerName, @PathVariable("amount")BigDecimal amt){
        try {
            iciciService.depositAmount(ownerName,amt);
            return new ResponseEntity<>("Amount deposited Successfully into ICICI",HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Invalid owner name.",HttpStatus.CONFLICT);
        }
    }

    @PatchMapping(value = "/icici/withdraw/{ownerName}/{amount}")
    public ResponseEntity<Object> withdrawAmount(@PathVariable("ownerName") String ownerName, @PathVariable("amount")BigDecimal amt){
        try {
            iciciService.withdrawAmount(ownerName,amt);
            return new ResponseEntity<>("Amount withdrawn Successfully from ICICI",HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
