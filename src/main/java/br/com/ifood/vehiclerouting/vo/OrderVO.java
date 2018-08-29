package br.com.ifood.vehiclerouting.vo;

import br.com.ifood.vehiclerouting.entity.Order;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.time.ZoneId;
import java.util.Date;

public class OrderVO {

    private Long id;
    @NotNull private Long restaurantId;
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

    public OrderVO(Long id, Long restaurantId, Long clientId, Date pickup, Date delivery) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.clientId = clientId;
        this.pickup = pickup;

        this.delivery = delivery;
    }

    public OrderVO(Order order) {
        this.id = order.getId();
        this.restaurantId = order.getRestaurant().getId();
        this.clientId = order.getCustumer().getId();
        this.pickup = Date.from(order.getPickupDate()
                            .atZone(ZoneId.systemDefault())
                            .toInstant());


        this.delivery = Date.from(order.getDeliveryDate()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurant) {
        this.restaurantId = restaurant;
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
