package ru.madela.ignite3computedemo.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.madela.ignite3computedemo.dto.TransactionDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class TransactionRowMapper implements RowMapper<TransactionDto> {
    @Override
    public TransactionDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        TransactionDto tx = new TransactionDto();
        tx.setId(rs.getObject("id", UUID.class));
        tx.setAccountId(rs.getObject("account_id", UUID.class));
        tx.setAmount(rs.getBigDecimal("amount"));
        tx.setDateTime(rs.getTimestamp("dt").toLocalDateTime());
        return tx;
    }
}
