package com.skeleton.app.commons;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import static com.skeleton.app.commons.UuidGenerator.GENERATOR;
import static com.skeleton.app.commons.UuidGenerator.GENERATOR_STRATEGY;

/** Common specified entity model with a generated UID using custom process */
@MappedSuperclass
@Setter
@Getter
@EqualsAndHashCode
public abstract class AbstractUuidEntity implements IdEntity {

  @Id
  @Column(name = "ID", unique = true, nullable = false)
  @GeneratedValue(generator = GENERATOR)
  @GenericGenerator(name = GENERATOR, strategy = GENERATOR_STRATEGY)
  protected String id;
}
