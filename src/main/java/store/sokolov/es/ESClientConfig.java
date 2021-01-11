package store.sokolov.es;

import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Конфигуратор
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "store.sokolov.es.dao")
@ComponentScan(basePackages = "store.sokolov.es")
public class ESClientConfig extends AbstractElasticsearchConfiguration {
    public static Logger logger = LoggerFactory.getLogger(ESClientConfig.class);
    @Value("${elasticsearch.host}")
    private String esHost; // адрес ElasticSearch
    @Value("${elasticsearch.port}")
    private int esPort; // порт ElasticSearch

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration =
                ClientConfiguration.builder()
                .connectedTo(esHost + ":" + esPort)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
