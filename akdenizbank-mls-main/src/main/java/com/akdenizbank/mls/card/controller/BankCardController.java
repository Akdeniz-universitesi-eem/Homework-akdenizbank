package com.akdenizbank.mls.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akdenizbank.mls.card.BankCard;
import com.akdenizbank.mls.card.repository.BankCardRepository;
import com.akdenizbank.mls.card.service.BankCardService;
import com.akdenizbank.mls.generic.rest.GenericApiResponse;

@RestController
@RequestMapping("/api/v1/bank-cards")
public class BankCardController {

    @Autowired
    private BankCardService bankCardService;

    //TODO : CREATE BANK CARD METHOD
    
    @PostMapping
    public GenericApiResponse createBankCard(@RequestBody BankCard bankCard) {
        BankCard createdBankCard = bankCardService.create(bankCard);
        return new GenericApiResponse(200, "created with success", "123456789", createdBankCard);
    }

    //TODO : UPDATE BANK CARD METHOD

    public BankCard update(String id, BankCard updatedBankCard) {

        GenericApiResponse existingBankCard = getById(id);

        if (existingBankCard == null) {
            throw new RuntimeException("No Such Bank Card");
        }
        
        existingBankCard.setNameoncard(updatedBankCard.getNameoncard());

        existingBankCard.setCardnumber(updatedBankCard.getCardnumber());

        existingBankCard.setExpiredate(updatedBankCard.getExpiredate());
        
        existingBankCard.setCvc(updatedBankCard.getCvc());

        return BankCardRepository.save(existingBankCard);
    }

    @PutMapping("/{id}")
    public GenericApiResponse updateBankCard(@PathVariable String id, @RequestBody BankCard bankCard) {
        BankCard updatedBankCard = BankCardService.update(id, bankCard);
        return new GenericApiResponse(200, "Bank Card Updated Successfully", "987654321", updatedBankCard);
    }

    
    @GetMapping
    public GenericApiResponse getAlBankCards(Pageable pageable) {
        Page<BankCard> bankCardsPage = this.bankCardService.getAll(pageable);
        return new GenericApiResponse(200, "Success", "345732945213", bankCardsPage);
    }

    @GetMapping("/{id}")
    public GenericApiResponse getById(@PathVariable String id) {
        BankCard bankCardInDB = this.bankCardService.getById(id);
        if (bankCardInDB == null) {
            throw new RuntimeException("No Such Bank Card");
        }
        return new GenericApiResponse(200, "Success", "23097452893", bankCardInDB);
    }

    @DeleteMapping("/{id}")
    public GenericApiResponse deleteCard(@PathVariable String id) {
        this.bankCardService.delete(id);
        return new GenericApiResponse(200, "Success", "34265782");
    }

}
