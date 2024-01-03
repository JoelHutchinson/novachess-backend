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
	private @JsonIgnore String password;

	User() {}

	User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.setPassword(password);
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;
		User user = (User) o;
		return Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name)
				&& Objects.equals(this.email, user.email)
				&& Objects.equals(this.password, user.password);
	}

    @Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.email, this.password);
	}

	@Override
	public String toString() {
		return "User{" + "id=" + this.id + ", name='" + this.name + '\'' + ", email='" +
                this.email + ", password='" + this.password + '\'' + '}';
	}
}
