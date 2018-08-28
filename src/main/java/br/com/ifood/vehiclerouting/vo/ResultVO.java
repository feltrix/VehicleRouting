package br.com.ifood.vehiclerouting.vo;

import br.com.ifood.vehiclerouting.ga.RouteChromosome;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ResultVO {

    private List<RouteVO> routes;

    public ResultVO(List<RouteVO> routes) {
        this.routes = routes;
    }

    public ResultVO(RouteChromosome routeChromosome) {
        routes = routeChromosome.getRiders()
                .stream()
                .map(RouteVO::new)
                .collect(Collectors.toList());

    }

    public List<RouteVO> getRoutes() {
        return routes;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "parser error";
    }
}
