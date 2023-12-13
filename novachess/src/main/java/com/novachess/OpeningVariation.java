package com.novachess;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
class OpeningVariation {

	private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;
	private String startFen;
	private String sanMoves;

	OpeningVariation() {}

	OpeningVariation(String startFen, String sanMoves) {
		this.startFen = startFen;
		this.sanMoves = sanMoves;
	}

	public Long getId() {
		return this.id;
	}

	public String getStartFen() {
		return this.startFen;
	}

	public String getSanMoves() {
		return this.sanMoves;
	}

    public void setId(Long id) {
		this.id = id;
	}

	public void setStartFen(String startFen) {
		this.startFen = startFen;
	}

	public void setSanMoves(String sanMoves) {
		this.sanMoves = sanMoves;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof OpeningVariation))
			return false;
		OpeningVariation openingVariation = (OpeningVariation) o;
		return Objects.equals(this.id, openingVariation.id) && Objects.equals(this.startFen, openingVariation.startFen)
				&& Objects.equals(this.sanMoves, openingVariation.sanMoves);
	}

    @Override
	public int hashCode() {
		return Objects.hash(this.id, this.startFen, this.sanMoves);
	}

	@Override
	public String toString() {
		return "OpeningVariation{" + "id=" + this.id + ", startFen='" + this.startFen + '\'' + ", sanMoves='" +
                this.sanMoves + '\'' + '}';
	}
}
