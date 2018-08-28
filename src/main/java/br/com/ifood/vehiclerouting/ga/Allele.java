package br.com.ifood.vehiclerouting.ga;

import br.com.ifood.vehiclerouting.entity.Order;

public class Allele implements Comparable<Allele> {

    private int tripId;
    private Order order;

    public Allele(int tripId, Order order) {
        this.tripId = tripId;
        this.order = order;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public int compareTo(Allele o) {

        return Long.compare(o.tripId, this.tripId);

    }
}
