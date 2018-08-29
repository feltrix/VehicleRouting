package br.com.ifood.vehiclerouting.entity;

import br.com.ifood.vehiclerouting.vo.CustomerVO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
public class Customer {

	@Id
	private Long id;
	private Position position;

	public Customer() {
		id = 0L;
		position = null;
	}

	public Customer(long id, Position position) {
		this.id = id;
		this.position = position;
	}

	public Customer(final CustomerVO customerVO) {
		this.id = customerVO.getId();
		this.position = new Position(customerVO.getLat(),customerVO.getLon());
	}

	public long getId() {
		return id;
	}

	public Position getPosition() {
		return position;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Custumer{" +
				"id=" + id +
				", position=" + position +
				'}';
	}
}
