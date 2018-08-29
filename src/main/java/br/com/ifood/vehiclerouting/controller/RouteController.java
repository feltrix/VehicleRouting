package br.com.ifood.vehiclerouting.controller;

import br.com.ifood.vehiclerouting.service.RouteService;
import br.com.ifood.vehiclerouting.vo.ResultVO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/routes")
public class RouteController {

     private final static Logger LOGGER = LoggerFactory.getLogger(RouteController.class);

     @Autowired private RouteService routeService;

     @ApiOperation("Optmize all pending orders")
     @GetMapping(value = "/optimize", produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<ResultVO> optmizeOrders () {

          LOGGER.info("Starting optmization");

          final ResultVO result = new ResultVO(routeService.optmizePendingOrders());

          LOGGER.info("Final result {}", result);

          return ResponseEntity.ok().body(result);

     }


}
