package br.com.ifood.vehiclerouting.ga;

import br.com.ifood.vehiclerouting.configuration.GAProperties;
import br.com.ifood.vehiclerouting.entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.stream.Collectors;


public class RouteChromosome implements Comparable<RouteChromosome>{

	private List<Trip> trips;
	
	private List<Rider> riders;
	
	private final int[] genes;
	
	private double fitness;

	private final List<Order> orders;
	
	private boolean resolved;

	private final GAProperties properties;

	public RouteChromosome() {

		properties = null;
		orders = null;
		genes = new int[0];
	}

	public RouteChromosome(final List<Order> orders, final GAProperties properties) {
		this.properties = properties;
		this.orders = Collections.unmodifiableList(orders);
		this.genes = randomGenes(orders.size());
		initialize();
		this.fitness = calculateFitness();
	}
	
	private RouteChromosome(final List<Order> orders, final int[] genes, final GAProperties properties) {
		this.orders = Collections.unmodifiableList(orders);
		this.genes = genes;
		this.properties = properties;
		initialize();
		this.fitness = calculateFitness();
	}
	
	public RouteChromosome[] crossover(final RouteChromosome parent) {
		
		final Random rd = new Random();
		
		final int genesSize = orders.size();
		
		final RouteChromosome[] children = new RouteChromosome[2];
		
		int[] child1 = new int[genesSize];
		int[] child2 = new int[genesSize];
		
		int[] parent1 = this.genes.clone();
		int[] parent2 = parent.genes.clone();

		int cut1 = rd.nextInt(genesSize - 1);
		int cut2 = rd.nextInt(genesSize - cut1 + 1) + cut1;

		if (Math.random() < properties.getCrossoverRate()) {

			System.arraycopy(parent1, 0, child1, 0, cut1);
			System.arraycopy(parent2, cut1, child1, cut1, cut2 - cut1);
			System.arraycopy(parent1, cut2, child1, cut2, genesSize - cut2);


			System.arraycopy(parent2, 0, child2, 0, cut1);
			System.arraycopy(parent1, cut1, child2, cut1, cut2 - cut1);
			System.arraycopy(parent2, cut2, child2, cut2, genesSize - cut2);
			
			children[0] = new RouteChromosome(orders, mutate(child1), properties);
			children[1] = new RouteChromosome(orders, mutate(child2), properties);

		} else {
			
			children[0] = new RouteChromosome(orders,parent1, properties);
			children[1] = new RouteChromosome(orders,parent2, properties);

		}
		
		return children;
	}
	
	private int[] mutate(final int[] genes) {
		final Random rd = new Random();
		for (int i = 0; i < genes.length; i++) {
			if(rd.nextFloat() <= properties.getMutationRate()) {
				genes[i] = rd.nextInt(genes.length * properties.getConstraints().getHard().getChargeLimit());
			}
		}
		return genes;
	}
	
	public double calculateFitness() {
		
		final long sumPickupTimeAhead = trips.stream().mapToLong(Trip::howManyMinutesPickupIsAhead).sum(); //HARD
		/*final long countExtraChargeOverThree = riders.stream()
				.mapToLong(Rider::countExtraChargeOverThreeInAllTrips)
				.sum();*///HARD

		final long countExtraRestaurants = riders.stream()
				.mapToLong(Rider::countExtraRestaurants)
				.sum();

		final long countExtraCharge = riders.stream()
				.mapToLong(r -> r.countExtraChargeOver(
						properties.getConstraints().getHard().getChargeLimit()
				))
				.sum();

		final long sumDeliveryTimeDelayed = trips.stream().mapToLong(Trip::howManyMinutesDeliveryIsLate).sum(); //SOFT
		final long countRiders = riders.stream().map(Rider::getTrips).filter(tps-> !tps.isEmpty()).count() -1;//SOFT
		
		double sumConstraints = (countExtraRestaurants * properties.getConstraints().getHard().getExtraRestaurants())
				+ (sumPickupTimeAhead * properties.getConstraints().getHard().getPickupTimeAhead())
				+ (countExtraCharge * properties.getConstraints().getHard().getExtraCharge())

				+ (sumDeliveryTimeDelayed * properties.getConstraints().getSoft().getDeliveryTimeDelayed())
				+ (countRiders * properties.getConstraints().getSoft().getAmountRiders());

		this.resolved = sumPickupTimeAhead + countExtraCharge + countExtraRestaurants == 0;
		return 1/(1+sumConstraints);
		
	}
	
	private void initialize() {
		
		this.trips = new ArrayList<> ();
		final NavigableMap<Integer, Order> orderByTripId = new TreeMap<>();

		riders = orders.stream().map(o->new Rider()).collect(Collectors.toList());

		List<Allele> alleles = new ArrayList<>();
		
		for(int i=0; i<genes.length; i++) {
			alleles.add(new Allele(genes[i], orders.get(i)));
		}

		Collections.sort(alleles);

		alleles.forEach(alelo -> {
			final int riderIndex = alelo.getTripId() % orders.size();
			final Rider rider = riders.get(riderIndex);
			trips.add(rider.addTrip(alelo.getOrder(),alelo.getTripId(), riderIndex));
		});
		
	}
	
	private int[] randomGenes(int size){
		
		final Random random = new Random();
		final int maxAlleleValue = properties.getConstraints().getHard().getChargeLimit() * size;
		return random.ints(size,0,maxAlleleValue).toArray();
	}
	
	public List<Rider> getRiders() {
		return riders.stream().filter(r -> !r.getTrips().isEmpty()).collect(Collectors.toList());
	}
	
	@Override
	public int compareTo(RouteChromosome o) {

		return Double.compare(this.fitness, o.fitness);

	}
	
	public double getFitness() {
		return fitness;
	}
	
	public boolean isResolved() {
		return resolved;
	}

	@Override
	public String toString() {
		ObjectMapper objectMapper = new ObjectMapper();
		//objectMapper.registerModule(new ParameterNamesModule())
		//		.registerModule(new Jdk8Module())
		//		.registerModule(new JavaTimeModule());
		try {
			return objectMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return "Parser error";

	}
}
