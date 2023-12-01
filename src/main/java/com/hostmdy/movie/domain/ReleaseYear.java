package com.hostmdy.movie.domain;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ReleaseYear {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Integer releasedYear;
	
	@OneToMany(mappedBy = "releaseYear",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Movie> movies;
	
	public ReleaseYear() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getReleasedYear() {
		return releasedYear;
	}

	public void setReleasedYear(Integer releasedYear) {
		this.releasedYear = releasedYear;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, releasedYear);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReleaseYear other = (ReleaseYear) obj;
		return Objects.equals(id, other.id) && Objects.equals(releasedYear, other.releasedYear);
	}

	@Override
	public String toString() {
		return "ReleaseYear [id=" + id + ", year=" + releasedYear + "]";
	}

}
