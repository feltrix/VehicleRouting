package br.com.ifood.vehiclerouting.ga;

import br.com.ifood.vehiclerouting.configuration.GAProperties;
import br.com.ifood.vehiclerouting.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class RoutePopulation {

    private static Logger logger = LoggerFactory.getLogger(RoutePopulation.class);

    private final List<RouteChromosome> populationRoutes;
    private final double sumFitness;
    private final GAProperties properties;


    public RoutePopulation(final List<Order> orders, final GAProperties properties) {

        this.properties = properties;
        populationRoutes = Collections.unmodifiableList(
            IntStream.range(0,properties.getMaxPopulationSize())
                .mapToObj(x->new RouteChromosome(orders,properties))
                .collect(Collectors.toList())
        );

        sumFitness = populationRoutes.stream().mapToDouble(RouteChromosome::getFitness).sum();

    }

    private RoutePopulation(final GAProperties properties, final List<RouteChromosome> populationRoutes) {
        this.properties = properties;
        Collections.sort(populationRoutes.stream().collect(Collectors.toList()));
        this.populationRoutes = Collections.unmodifiableList(populationRoutes);
        sumFitness = populationRoutes.stream().mapToDouble(RouteChromosome::getFitness).sum();
    }

    public RouteChromosome getFitnesest(){
        return populationRoutes.get(0);
    }

    public RoutePopulation generateNextPopulation() {


        if(properties.isParallel()) {


            final ForkJoinPool pool = properties.getPool();
            final ForkJoinTask<List<RouteChromosome>> parallelTask = pool.submit(() ->
                    IntStream.range(0, populationRoutes.size() / 2)
                    .parallel()
                    .mapToObj(i -> selectByRoullete().crossover(selectByRoullete()))
                    .flatMap(Stream::of)
                    .collect(Collectors.toList()));
            try {
                return new RoutePopulation(properties,parallelTask.get());
            } catch (InterruptedException| ExecutionException e) {
                logger.warn("Unexpected error to process parallel",e);
            }


        }

        final List<RouteChromosome> nextGeneration = new ArrayList<>();

        for (int i = 0; i < populationRoutes.size(); i += 2) {
            RouteChromosome parent1 = selectByRoullete();
            RouteChromosome parent2 = selectByRoullete();

            RouteChromosome[] children = parent1.crossover(parent2);
            nextGeneration.add(children[0]);
            nextGeneration.add(children[1]);
        }

        return new RoutePopulation(properties, nextGeneration);


    }

    private RouteChromosome selectByRoullete() {

        //selecao da roleta
        double limit = Math.random() * sumFitness;

        double sum = sumFitness;

        for (RouteChromosome c : populationRoutes) {

            sum -= c.getFitness();

            if (sum <= limit) {
                return c;
            }

        }

        return populationRoutes.get(0);

    }




}
