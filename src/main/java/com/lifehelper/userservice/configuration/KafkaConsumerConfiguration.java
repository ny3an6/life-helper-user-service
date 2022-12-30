package com.lifehelper.userservice.configuration;

import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.backoff.FixedBackOff;

@EnableKafka
@Configuration
@EnableTransactionManagement
public class KafkaConsumerConfiguration {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<?, ?> concurrentKafkaListenerContainerFactory(
            KafkaProperties kafkaProperties,
            KafkaTransactionManager<?, ?> kafkaTransactionManager,
            DefaultErrorHandler errorHandler) {
        kafkaProperties.getConsumer().setKeyDeserializer(LongDeserializer.class);
        kafkaProperties.getConsumer().setValueDeserializer(StringDeserializer.class);
        kafkaProperties.getConsumer().setEnableAutoCommit(false);
        kafkaProperties.getConsumer().setIsolationLevel(KafkaProperties.IsolationLevel.READ_COMMITTED);

        final ConcurrentKafkaListenerContainerFactory<?, ?> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties()));
        factory.setBatchListener(false);
        factory.setMessageConverter(new StringJsonMessageConverter());
        factory.getContainerProperties().setTransactionManager(kafkaTransactionManager);
        factory.setCommonErrorHandler(errorHandler);
        return factory;
    }

    @Bean
    public DefaultErrorHandler getErrorHandler(KafkaTemplate<?, ?> kafkaTemplate) {
        return new DefaultErrorHandler(
                new DeadLetterPublishingRecoverer(kafkaTemplate),
                new FixedBackOff(0L, 0L)
        );
    }
}
