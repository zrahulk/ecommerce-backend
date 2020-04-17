package com.ecommerce.ecommerce.config;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import org.springframework.context.annotation.Configuration;

import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.ecommerce.ecommerce.dao")
public class ElasticsearchConfig extends AbstractFactoryBean {
	
	@Value("${spring.elasticsearch.rest.uris}")
	private String elasticsearchUrl;
	@Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes;
    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;
	private RestHighLevelClient restHighLevelClient;
	
	/*
	 * @Bean RestHighLevelClient client() { ClientConfiguration clientConfiguration
	 * = ClientConfiguration.builder() .connectedTo(getElasticsearchUrl()) .build();
	 * 
	 * return RestClients.create(clientConfiguration).rest(); }
	 */

	public String getElasticsearchUrl() {
		return elasticsearchUrl;
	}

	@Override
	public Class<RestHighLevelClient> getObjectType() {
		// TODO Auto-generated method stub
		return RestHighLevelClient.class;
	}
	
	private RestHighLevelClient buildClient() {
        try {
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("192.168.56.103", 9200, "http"),
                            new HttpHost("192.168.56.103", 9201, "http")));
        } catch (Exception e) {
           
        }
        return restHighLevelClient;
    }

	@Override
	protected RestHighLevelClient createInstance() throws Exception {
		// TODO Auto-generated method stub
		return buildClient();
	}

	

}
