package ru.madela.ignite3computedemo.service;

import ru.madela.RapidRepeatJobResponse;
import ru.madela.ignite3computedemo.dto.TransactionDto;

import java.util.List;
import java.util.UUID;

public interface IgniteComputeService {

    Boolean primeCheck(Integer number);
    List<TransactionDto> findByAccountId(UUID accountId);

    RapidRepeatJobResponse findRapidRepeats(UUID accountId);

    List<RapidRepeatJobResponse> findAllAccountsRapidRepeats();

}
