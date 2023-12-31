package com.akdenizbank.mls.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akdenizbank.mls.card.BankCard;
import com.akdenizbank.mls.generic.rest.GenericApiResponse;

public interface BankCardRepository extends JpaRepository<BankCard, String> {

    static BankCard save(GenericApiResponse existingBankCard) {
        return null;
    }

}
