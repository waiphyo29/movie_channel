package com.hostmdy.movie.domain;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Synopsis {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Lob
	private String synopsisEng;
	@Lob
	private String synopsisMm;
	
	public Synopsis() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSynopsisEng() {
		return synopsisEng;
	}

	public void setSynopsisEng(String synopsisEng) {
		this.synopsisEng = synopsisEng;
	}

	public String getSynopsisMm() {
		return synopsisMm;
	}

	public void setSynopsisMm(String synopsisMm) {
		this.synopsisMm = synopsisMm;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, synopsisEng, synopsisMm);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Synopsis other = (Synopsis) obj;
		return Objects.equals(id, other.id) && Objects.equals(synopsisEng, other.synopsisEng)
				&& Objects.equals(synopsisMm, other.synopsisMm);
	}

	@Override
	public String toString() {
		return "Synopsis [id=" + id + ", synopsisEng=" + synopsisEng + ", synopsisMm=" + synopsisMm + "]";
	}

}
