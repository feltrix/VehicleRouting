package br.com.ifood.vehiclerouting.ga;

import br.com.ifood.vehiclerouting.configuration.GAProperties;
import br.com.ifood.vehiclerouting.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VehicleRoutingGA {

	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleRoutingGA.class);

	private final GAProperties properties;

	public VehicleRoutingGA(final GAProperties properties) {
		this.properties = properties;
	}

	public RouteChromosome optimize(final List<Order> orders) {

		RoutePopulation routePopulation = new RoutePopulation(orders, properties);

		RouteChromosome fitnesestOverall = routePopulation.getFitnesest();
		
		for (int gen = 0; gen < properties.getMaxGenerations(); gen++) {

			routePopulation = routePopulation.generateNextPopulation();

			if(fitnesestOverall.getFitness() < routePopulation.getFitnesest().getFitness()){
				fitnesestOverall = routePopulation.getFitnesest();
			}
			if (gen % 100 == 0) {
				LOGGER.info("Generation: {}",gen);
				LOGGER.info("The best current fitness is: {}", fitnesestOverall.getFitness());
			}
		}


		LOGGER.info("The best fitness overall is: {}", fitnesestOverall.getFitness());
		LOGGER.debug("Explanation: {}",fitnesestOverall);

		return fitnesestOverall;
		
	}
	

}
