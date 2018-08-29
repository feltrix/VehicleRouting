package br.com.ifood.vehiclerouting.vo;

import br.com.ifood.vehiclerouting.entity.Restaurant;

import javax.validation.constraints.NotNull;

public class RestaurantVO {

    private Long id;

    @NotNull
    private Double lon;

    @NotNull
    private Double lat;


    public RestaurantVO() {
    }

    public RestaurantVO(Long id, @NotNull Double lon, @NotNull Double lat) {
        this.id = id;
        this.lon = lon;
        this.lat = lat;
    }

    public RestaurantVO(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.lon = restaurant.getPosition().getLon();
        this.lat = restaurant.getPosition().getLat();
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
