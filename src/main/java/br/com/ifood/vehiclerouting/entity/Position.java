package br.com.ifood.vehiclerouting.entity;

public class Position {
	
	private final double lat;
	private final double lon;

	public Position() {
		lat = 0;
		lon = 0;
	}

	public Position(double lat, double lon) {
		super();
		this.lat = lat;
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}
	
	public double calculateDistance(final Position from) {
		return Math.hypot(from.getLat() - lat, from.getLon() -lon);
	}

	@Override
	public String toString() {
		return "Position{" +
				"lat=" + lat +
				", lon=" + lon +
				'}';
	}
}
