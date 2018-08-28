package br.com.ifood.vehiclerouting.entity;

public class Restaurant {
	
	private final long id;
	private final Position position;

	public Restaurant() {
		id = 0;
		position = null;
	}

	public Restaurant(final long id, final Position position) {
		this.id = id;
		this.position = position;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		final Restaurant other = (Restaurant) obj;
		
		if (id != other.id) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "Restaurant{" +
				"id=" + id +
				", position=" + position +
				'}';
	}
}
