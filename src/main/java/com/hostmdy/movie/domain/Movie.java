package com.hostmdy.movie.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	private String country;
	private String createdBy;
	@Lob
	private String starring;
	
	@Enumerated(EnumType.ORDINAL)
	private Type type;
	
	private Double rating;
	private Integer runtime;
	private LocalDate releaseDate; // Date
	private Integer view;
	
	@Lob
	private byte[] image;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	//One way
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "synopsis_id")
	private Synopsis synopsis;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private ReleaseYear releaseYear;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "movie_generes",joinColumns = @JoinColumn(name = "movie_id"),
	inverseJoinColumns = @JoinColumn(name ="genere_id"))
	private Set<Generes> generes = new HashSet<>();
	
	@OneToMany(mappedBy = "movie",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Episodes> episodes = new ArrayList<>();
	
	@OneToMany(mappedBy = "movie",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Photos> photos = new ArrayList<>();
	
	@OneToMany(mappedBy = "movie",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();
	
	public Movie() {}
	
	@PrePersist
	void onCreate() {
		this.createdAt = LocalDateTime.now();
	}
	
	@PreUpdate
	void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getStarring() {
		return starring;
	}

	public void setStarring(String starring) {
		this.starring = starring;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Integer getView() {
		return view;
	}

	public void setView(Integer view) {
		this.view = view;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public ReleaseYear getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(ReleaseYear releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Set<Generes> getGeneres() {
		return generes;
	}

	public void setGeneres(Set<Generes> generes) {
		this.generes = generes;
	}

	public List<Episodes> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episodes> episodes) {
		this.episodes = episodes;
	}

	public List<Photos> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photos> photos) {
		this.photos = photos;
	}

	public Synopsis getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(Synopsis synopsis) {
		this.synopsis = synopsis;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(image);
		result = prime * result
				+ Objects.hash(country, createdBy, id, rating, releaseDate, runtime, starring, title, type);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return Objects.equals(country, other.country) && Objects.equals(createdBy, other.createdBy)
				&& Objects.equals(id, other.id) && Arrays.equals(image, other.image)
				&& Objects.equals(rating, other.rating) && Objects.equals(releaseDate, other.releaseDate)
				&& Objects.equals(runtime, other.runtime) && Objects.equals(starring, other.starring)
				&& Objects.equals(title, other.title) && type == other.type;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", country=" + country
				+ ", createdBy=" + createdBy + ", starring=" + starring + ", type=" + type + ", rating=" + rating
				+ ", runtime=" + runtime + ", releaseDate=" + releaseDate + ", image=" + Arrays.toString(image) + "]";
	}
}
