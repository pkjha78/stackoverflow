package com.altimetrik.stackoverflow.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tag_synonyms", schema = "stackoverflow")
public class TagSynonyms {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
