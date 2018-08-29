package br.com.ifood.vehiclerouting.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class OrderVO {

    @NotNull private Long id;
    @NotNull private Long restaurant;
    @NotNull private Long clientId;

    @NotNull
    @ApiModelProperty(example = "2017-12-20T12:31:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "America/Sao_Paulo")
    private Date pickup;

    @NotNull
    @ApiModelProperty(example = "2017-12-20T12:31:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "America/Sao_Paulo")
    private Date delivery;

    public OrderVO() {
    }

    public OrderVO(Long id, Long restaurant, Long clientId, Date pickup, Date delivery) {
        this.id = id;
        this.restaurant = restaurant;
        this.clientId = clientId;
        this.pickup = pickup;

        this.delivery = delivery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Long restaurant) {
        this.restaurant = restaurant;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Date getPickup() {
        return pickup;
    }

    public void setPickup(Date pickup) {
        this.pickup = pickup;
    }

    public Date getDelivery() {
        return delivery;
    }

    public void setDelivery(Date delivery) {
        this.delivery = delivery;
    }
}
