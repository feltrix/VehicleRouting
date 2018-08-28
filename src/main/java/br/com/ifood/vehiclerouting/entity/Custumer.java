package br.com.ifood.vehiclerouting.entity;

public class Custumer {
	
	private final long id;
	private final Position position;

	public Custumer() {
		id = 0;
		position = null;
	}

	public Custumer(long id, Position position) {
		super();
		this.id = id;
		this.position = position;
	}

	public long getId() {
		return id;
	}

	public Position getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return "Custumer{" +
				"id=" + id +
				", position=" + position +
				'}';
	}
}
