package br.com.ifood.vehiclerouting.controller;


//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = VehicleRoutingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application-test.properties")
public class RouterControllerTest {

  /*  //@Autowired
    //private MockMvc mvc;

    private ObjectMapper o = new ObjectMapper();


    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = null;

    @Before
    public void before () {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

    }

    @Test
    public void testOptimize () throws Exception {

        Stream.of(new CustomerVO(null,100.2,100.3),
                new CustomerVO(null,100.3,100.2))
                .forEach(c-> {

                    try {
                        String json = json = o.writeValueAsString(c);
                        HttpEntity<String> http = new HttpEntity<>(json, headers);

                        ResponseEntity<CustomerVO> responseEntity = restTemplate
                                .postForEntity("http://localhost:8080/costumers", http, CustomerVO.class);

                        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }


                });

        Stream.of(new RestaurantVO(null,100.0,100.0),
                new RestaurantVO(null,200.0,200.0))
                .forEach(r-> {

                    try {
                        String json = json = o.writeValueAsString(r);
                        HttpEntity<String> http = new HttpEntity<>(json, headers);

                        ResponseEntity<RestaurantVO> responseEntity = restTemplate
                                .postForEntity("http://localhost:8080/restaurants", http, RestaurantVO.class);

                        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }


                });

        Stream.of(
            new OrderVO(null, 1L, 1L,
                    LocalDateTime.of(2018,1,1,0,0),
                    LocalDateTime.of(2018,1,1,0,40)),
            new OrderVO(null, 1L, 1L,
                    LocalDateTime.of(2018,1,1,0,5),
                    LocalDateTime.of(2018,1,1,0,40)),
            new OrderVO(null, 1L, 1L,
                    LocalDateTime.of(2018,1,1,0,0),
                    LocalDateTime.of(2018,1,1,0,40)),
            new OrderVO(null, 1L, 1L,
                    LocalDateTime.of(2018,1,1,0,5),
                    LocalDateTime.of(2018,1,1,0,40)))
                .forEach(or-> {

                    try {
                        String json = json = o.writeValueAsString(or);
                        HttpEntity<String> http = new HttpEntity<>(json, headers);

                        ResponseEntity<RestaurantVO> responseEntity = restTemplate
                                .postForEntity("http://localhost:8080/orders", http, RestaurantVO.class);

                        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }


                });



    }*/

}
