package io.tntra.javabootcamp.controller;

import io.tntra.javabootcamp.Exceptionpkg.MinBalanceException;
import io.tntra.javabootcamp.model.Account;
import io.tntra.javabootcamp.services.HDFCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class HDFC_Controller {
    @Autowired
    HDFCService hdfcService;

    @PostMapping(value = "/hdfc")
    public ResponseEntity<Object> createAccount(@RequestBody Account account) {
        try {
            hdfcService.createAccount(account);
            return new ResponseEntity<>("HDFC Account created successfully", HttpStatus.OK);
        } catch (MinBalanceException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Minimum balance for HDFC is 1000", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/hdfc")
    public ResponseEntity<Object> getAllAccount(){
        return new ResponseEntity<>(hdfcService.getAllAccount(),HttpStatus.OK);
    }

    @GetMapping(value = "/hdfc/{ownerName}")
    public ResponseEntity<Object> getAccount(@PathVariable("ownerName") String ownerName){
        try{
            return new ResponseEntity<>(hdfcService.getAccount(ownerName),HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Given account owner does not exist",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/hdfc/balance/{ownerName}")
    public ResponseEntity<Object> getAccountBalance(@PathVariable("ownerName") String ownerName){
        try{
            BigDecimal balance = hdfcService.getAccountBalance(ownerName);
            return new ResponseEntity<>("The given user has balance of "+ balance ,HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Given account owner does not exist",HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value="/hdfc/deposit/{ownerName}/{amount}")
    public ResponseEntity<Object> depositAmount(@PathVariable("ownerName") String ownerName, @PathVariable("amount")BigDecimal amt){
        try {
            hdfcService.depositAmount(ownerName,amt);
            return new ResponseEntity<>("Amount deposited Successfully from HDFC",HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Invalid owner name.",HttpStatus.CONFLICT);
        }
    }

    @PatchMapping(value = "/hdfc/withdraw/{ownerName}/{amount}")
    public ResponseEntity<Object> withdrawAmount(@PathVariable("ownerName") String ownerName, @PathVariable("amount")BigDecimal amt){
        try {
            hdfcService.withdrawAmount(ownerName,amt);
            return new ResponseEntity<>("Amount withdrawn Successfully from HDFC",HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
