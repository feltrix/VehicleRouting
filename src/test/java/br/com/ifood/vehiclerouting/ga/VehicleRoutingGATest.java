package br.com.ifood.vehiclerouting.ga;

import br.com.ifood.vehiclerouting.configuration.GAProperties;
import br.com.ifood.vehiclerouting.entity.Custumer;
import br.com.ifood.vehiclerouting.entity.Order;
import br.com.ifood.vehiclerouting.entity.Position;
import br.com.ifood.vehiclerouting.entity.Restaurant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class VehicleRoutingGATest {


    private GAProperties properties;

    @Before
    public void setUp() {
        properties = new GAProperties();
        properties.setCrossoverRate(0.85f);
        properties.setMutationRate(0.01f);
        properties.setMaxGenerations(1000);
        properties.setMaxPopulationSize(100);
        properties.setParallel(true);
        properties.setPool(new ForkJoinPool());
        properties.setConstraints(new GAProperties.Constraints());
        properties.getConstraints().setHard(new GAProperties.Constraints.Hard());
        properties.getConstraints().setSoft(new GAProperties.Constraints.Soft());
        properties.getConstraints().getHard().setChargeLimit(3);
        properties.getConstraints().getHard().setExtraCharge(0.8);
        properties.getConstraints().getHard().setExtraRestaurants(0.8);
        properties.getConstraints().getHard().setPickupTimeAhead(0.8);
        properties.getConstraints().getSoft().setAmountRiders(0.15);
        properties.getConstraints().getSoft().setDeliveryTimeDelayed(0.25);

    }

    @Test
    public void iTShouldReturnResolutionWithTwoRiders () {

        VehicleRoutingGA vehicleRoutingGA = new VehicleRoutingGA(properties);

        Restaurant r1 = new Restaurant(1,new Position(100.0, 100.0));
        Restaurant r2 = new Restaurant(2,new Position(200.0, 200.0));

        Custumer c1 = new Custumer(1,new Position(100.2, 100.2));
        Custumer c2 = new Custumer(2,new Position(100.3, 100.3));

        Custumer c3 = new Custumer(3,new Position(200.2, 200.2));
        Custumer c4 = new Custumer(4,new Position(200.3, 200.3));

        Order o1 = new Order(1,r1,c1, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,15));
        Order o2 = new Order(2,r1,c2, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,30));

        Order o3 = new Order(3,r2,c3, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,15));
        Order o4 = new Order(4,r2,c4, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,30));

        final List<Order> orders = Stream.of(o1, o2, o3, o4).collect(Collectors.toList());

        final RouteChromosome route = vehicleRoutingGA.optimize(orders);

        Assert.assertTrue(route.isResolved());
        Assert.assertEquals(2, route.getRiders().size());

    }

    @Test
    public void iTShouldReturnResolutionWithTwoRidersAndSinglePickup () {

        VehicleRoutingGA vehicleRoutingGA = new VehicleRoutingGA(properties);

        Restaurant r1 = new Restaurant(1,new Position(100.0, 100.0));
        Restaurant r2 = new Restaurant(2,new Position(200.0, 200.0));

        Custumer c1 = new Custumer(1,new Position(100.2, 100.2));
        Custumer c2 = new Custumer(2,new Position(100.3, 100.3));

        Custumer c3 = new Custumer(3,new Position(200.2, 200.2));
        Custumer c4 = new Custumer(4,new Position(200.3, 200.3));

        Order o1 = new Order(1,r1,c1, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,40));
        Order o2 = new Order(2,r1,c2, LocalDateTime.of(2018,1,1,0,5),LocalDateTime.of(2018,1,1,0,40));

        Order o3 = new Order(3,r2,c3, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,40));
        Order o4 = new Order(4,r2,c4, LocalDateTime.of(2018,1,1,0,5),LocalDateTime.of(2018,1,1,0,40));

        final List<Order> orders = Stream.of(o1, o2, o3, o4).collect(Collectors.toList());

        final RouteChromosome route = vehicleRoutingGA.optimize(orders);

        Assert.assertTrue(route.isResolved());
        Assert.assertEquals(2, route.getRiders().size());

    }

    @Test
    public void iTShouldReturnResolutionWithFourRiders () {

        VehicleRoutingGA vehicleRoutingGA = new VehicleRoutingGA(properties);

        Restaurant r1 = new Restaurant(1,new Position(100.0, 100.0));
        Restaurant r2 = new Restaurant(2,new Position(200.0, 200.0));

        Custumer c1 = new Custumer(1,new Position(100.2, 100.2));
        Custumer c2 = new Custumer(2,new Position(100.3, 100.3));

        Custumer c3 = new Custumer(3,new Position(200.2, 200.2));
        Custumer c4 = new Custumer(4,new Position(200.3, 200.3));

        Order o1 = new Order(1,r1,c1, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,15));
        Order o2 = new Order(2,r1,c2, LocalDateTime.of(2018,1,1,0,5),LocalDateTime.of(2018,1,1,0,25));

        Order o3 = new Order(3,r2,c3, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,15));
        Order o4 = new Order(4,r2,c4, LocalDateTime.of(2018,1,1,0,5),LocalDateTime.of(2018,1,1,0,25));

        final List<Order> orders = Stream.of(o1, o2, o3, o4).collect(Collectors.toList());

        final RouteChromosome route = vehicleRoutingGA.optimize(orders);

        Assert.assertTrue(route.isResolved());
        Assert.assertEquals(4, route.getRiders().size());

    }

    @Test
    public void iTShouldReturnResolutionWithTwoRidersWithLessThanThreeOrders () {

        VehicleRoutingGA vehicleRoutingGA = new VehicleRoutingGA(properties);

        Restaurant r1 = new Restaurant(1,new Position(100.0, 100.0));
        Restaurant r2 = new Restaurant(2,new Position(200.0, 200.0));

        Custumer c1 = new Custumer(1,new Position(100.2, 100.2));
        Custumer c2 = new Custumer(2,new Position(100.3, 100.3));

        Custumer c3 = new Custumer(3,new Position(200.2, 200.2));
        Custumer c4 = new Custumer(4,new Position(200.3, 200.3));
        Custumer c5 = new Custumer(5,new Position(200.2, 200.2));
        Custumer c6 = new Custumer(6,new Position(200.3, 200.3));
        Custumer c7 = new Custumer(7,new Position(200.2, 200.2));
        Custumer c8 = new Custumer(8,new Position(200.3, 200.3));

        Order o1 = new Order(1,r1,c1, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,15));
        Order o2 = new Order(2,r1,c2, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,30));

        Order o3 = new Order(3,r2,c3, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,15));
        Order o4 = new Order(4,r2,c4, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,30));
        Order o5 = new Order(5,r2,c5, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,15));
        Order o6 = new Order(6,r2,c6, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,30));
        Order o7 = new Order(7,r2,c7, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,15));
        Order o8 = new Order(8,r2,c8, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,30));

        final List<Order> orders = Stream.of(o1, o2, o3, o4, o5, o6, o7, o8).collect(Collectors.toList());

        final RouteChromosome route = vehicleRoutingGA.optimize(orders);

        Assert.assertTrue(route.isResolved());
        Assert.assertEquals(3, route.getRiders().size());

    }

}
