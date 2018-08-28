package br.com.ifood.vehiclerouting.configuration;

import br.com.ifood.vehiclerouting.ga.VehicleRoutingGA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ForkJoinPool;

@Configuration
public class VehicleRoutingGAConfiguration {

    @Bean
    public VehicleRoutingGA getVehicleRoutingGA(@Autowired final GAProperties gaProperties) {

        return new VehicleRoutingGA(gaProperties);

    }
}
