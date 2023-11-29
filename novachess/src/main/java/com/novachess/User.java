package com.novachess;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
class User {

	private @Id @GeneratedValue Long id;
	private String username;
	private String password;

	User() {}

	User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return this.id;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;
		User user = (User) o;
		return Objects.equals(this.id, user.id) && Objects.equals(this.username, user.username)
				&& Objects.equals(this.password, user.password);
	}

    @Override
	public int hashCode() {
		return Objects.hash(this.id, this.username, this.password);
	}

	@Override
	public String toString() {
		return "Puzzle{" + "id=" + this.id + ", username='" + this.username + '\'' + ", password='" +
                this.password + '\'' + '}';
	}
}
