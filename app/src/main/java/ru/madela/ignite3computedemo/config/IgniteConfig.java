package ru.madela.ignite3computedemo.config;

import lombok.extern.log4j.Log4j2;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.table.RecordView;
import org.apache.ignite.table.Table;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.madela.ignite3computedemo.dto.TransactionDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Log4j2
@Configuration
public class IgniteConfig {

    @Value("${ignite.address}")
    private String igniteAddress;

    @Bean
    public IgniteClient igniteClient() {
        return IgniteClient.builder()
                .addresses(igniteAddress)
                .build();
    }

    @Bean
    public RecordView<TransactionDto> transactionRecordView(IgniteClient client) {
        client.catalog().createTable(TransactionDto.class);
        Table t = client.tables().table("tx");
        RecordView<TransactionDto> transactionDtoRecordView = t.recordView(TransactionDto.class);
        fillTable(transactionDtoRecordView);
        return transactionDtoRecordView;
    }

    private void fillTable(RecordView<TransactionDto> transactionDtoRecordView) {
        UUID uuidTrue = UUID.randomUUID();
        UUID uuidFalse = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        log.info("uuid for true: {}", uuidTrue);
        log.info("uuid for false: {}", uuidFalse);
        List<TransactionDto> batch = List.of(
                new TransactionDto()
                        .setId(UUID.randomUUID())
                        .setAccountId(uuidTrue)
                        .setAmount(new BigDecimal("12"))
                        .setDateTime(now),
                new TransactionDto()
                        .setId(UUID.randomUUID())
                        .setAccountId(uuidTrue)
                        .setAmount(new BigDecimal("13"))
                        .setDateTime(now.plusSeconds(2)),
                new TransactionDto()
                        .setId(UUID.randomUUID())
                        .setAccountId(uuidTrue)
                        .setAmount(new BigDecimal("14"))
                        .setDateTime(now.plusSeconds(4)),
                new TransactionDto()
                        .setId(UUID.randomUUID())
                        .setAccountId(uuidFalse)
                        .setAmount(new BigDecimal("12"))
                        .setDateTime(now),
                new TransactionDto()
                        .setId(UUID.randomUUID())
                        .setAccountId(uuidFalse)
                        .setAmount(new BigDecimal("13"))
                        .setDateTime(now.plusSeconds(31)),
                new TransactionDto()
                        .setId(UUID.randomUUID())
                        .setAccountId(uuidFalse)
                        .setAmount(new BigDecimal("14"))
                        .setDateTime(now.plusSeconds(62))
        );
        transactionDtoRecordView.upsertAll(null, batch);
    }

}
