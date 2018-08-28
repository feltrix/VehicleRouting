package br.com.ifood.vehiclerouting.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ForkJoinPool;

@Configuration
public class ThreadPoolGAConfiguration {
    
    @Bean
    public ForkJoinPool getThreadPool () {
        return new ForkJoinPool();
    }
    
}
