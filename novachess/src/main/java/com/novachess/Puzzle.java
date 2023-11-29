package com.novachess;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
class Puzzle {

	private @Id @GeneratedValue Long id;
	private String fen;
	private String moves;
	private int rating;
	private int popularity;

	Puzzle() {}

	Puzzle(String fen, String moves, int rating, int popularity) {
		this.fen = fen;
		this.moves = moves;
		this.rating = rating;
		this.popularity = popularity;
	}

	public Long getId() {
		return this.id;
	}

	public String getFen() {
		return this.fen;
	}

	public String getMoves() {
		return this.moves;
	}

	public int getRating() {
		return this.rating;
	}

	public int getPopularity() {
		return this.popularity;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFen(String fen) {
		this.fen = fen;
	}

	public void setMoves(String moves) {
		this.moves = moves;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Puzzle))
			return false;
		Puzzle puzzle = (Puzzle) o;
		return Objects.equals(this.id, puzzle.id) && Objects.equals(this.fen, puzzle.fen)
				&& Objects.equals(this.moves, puzzle.moves)
				&& Objects.equals(this.rating, puzzle.rating)
				&& Objects.equals(this.popularity, puzzle.popularity);
	}

    @Override
	public int hashCode() {
		return Objects.hash(this.id, this.fen, this.moves, this.rating, this.popularity);
	}

	@Override
	public String toString() {
		return "Puzzle{" + "id=" + this.id + ", fen='" + this.fen + '\'' + ", moves='" +
                this.moves + ", rating='" + this.rating + ", popularity='" + this.popularity + '\'' + '}';
	}
}
