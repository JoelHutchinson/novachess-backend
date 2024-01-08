package com.novachess;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
class User {

    private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;
    private String name;
    private String email;
    private String password;
    private int puzzleRating;
	
	@JsonIgnore
	private Long currentPuzzleId;

    User() {}

    User(String name, String email, String password, int puzzleRating) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.puzzleRating = puzzleRating;
    }

    public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}

	public long getCurrentPuzzleId() {
		return this.currentPuzzleId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public int getPuzzleRating() {
        return this.puzzleRating;
    }

    public void setPuzzleRating(int puzzleRating) {
        this.puzzleRating = puzzleRating;
    }


    // Override equals, hashCode and toString methods

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name)
                && Objects.equals(this.email, user.email)
                && Objects.equals(this.password, user.password)
                && this.puzzleRating == user.puzzleRating;  // Include puzzleRating in equality check
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.email, this.password, this.puzzleRating);  // Include puzzleRating in hash
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", name='" + this.name + '\'' + ", email='" +
                this.email + '\'' + ", password='" + this.password + '\'' + ", puzzleRating=" + this.puzzleRating + '}';
    }
}
