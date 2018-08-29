package br.com.ifood.vehiclerouting.repository;

import br.com.ifood.vehiclerouting.entity.Customer;
import br.com.ifood.vehiclerouting.entity.Order;
import br.com.ifood.vehiclerouting.entity.Position;
import br.com.ifood.vehiclerouting.entity.Restaurant;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class OrderRepository {

    public List<Order> findOrdersPending () {
        Restaurant r1 = new Restaurant(1,new Position(100.0, 100.0));
        Restaurant r2 = new Restaurant(2,new Position(200.0, 200.0));

        Customer c1 = new Customer(1,new Position(100.2, 100.2));
        Customer c2 = new Customer(2,new Position(100.3, 100.3));

        Customer c3 = new Customer(3,new Position(200.2, 200.2));
        Customer c4 = new Customer(4,new Position(200.3, 200.3));
        Customer c5 = new Customer(5,new Position(200.2, 200.2));
        Customer c6 = new Customer(6,new Position(200.3, 200.3));
        Customer c7 = new Customer(7,new Position(200.2, 200.2));
        Customer c8 = new Customer(8,new Position(200.3, 200.3));

        Order o1 = new Order(1,r1,c1, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,15));
        Order o2 = new Order(2,r1,c2, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,30));

        Order o3 = new Order(3,r2,c3, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,15));
        Order o4 = new Order(4,r2,c4, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,30));
        Order o5 = new Order(5,r2,c5, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,15));
        Order o6 = new Order(6,r2,c6, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,30));
        Order o7 = new Order(7,r2,c7, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,15));
        Order o8 = new Order(8,r2,c8, LocalDateTime.of(2018,1,1,0,0),LocalDateTime.of(2018,1,1,0,30));

        return Stream.of(o1, o2, o3, o4, o5, o6, o7, o8).collect(Collectors.toList());
    }

}
