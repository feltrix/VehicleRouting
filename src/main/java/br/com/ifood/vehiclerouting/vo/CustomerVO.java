package br.com.ifood.vehiclerouting.vo;

import br.com.ifood.vehiclerouting.entity.Customer;

import javax.validation.constraints.NotNull;

public class CustomerVO {

    //@NotNull
    private Long id;

    @NotNull
    private Double lon;

    @NotNull
    private Double lat;


    public CustomerVO() {
    }

    public CustomerVO(Customer customer) {
        this.id = customer.getId();
        this.lon = customer.getPosition().getLon();
        this.lat = customer.getPosition().getLat();
    }

    public CustomerVO(@NotNull Long id, @NotNull Double lon, @NotNull Double lat) {
        this.id = id;
        this.lon = lon;
        this.lat = lat;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

}
