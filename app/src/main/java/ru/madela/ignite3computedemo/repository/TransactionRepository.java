package ru.madela.ignite3computedemo.repository;

import ru.madela.ignite3computedemo.dto.TransactionDto;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository {

    List<TransactionDto> findByAccountId(UUID id);

    List<TransactionDto> findAll();


}
