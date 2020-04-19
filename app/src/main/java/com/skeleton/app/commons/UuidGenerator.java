package com.skeleton.app.commons;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;

/** Generator type used for simple UUID */
public class UuidGenerator implements IdentifierGenerator, Configurable {

  static final String GENERATOR = "simple-uuid";
  static final String GENERATOR_STRATEGY = "com.skeleton.app.commons.UuidGenerator";

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object obj)
      throws HibernateException {

    IdEntity entity = (IdEntity) obj;
    if (entity.getId() != null) {
      return entity.getId();
    }
    return String.valueOf(UUID.randomUUID());
  }

  @Override
  public void configure(Type type, Properties params, ServiceRegistry serviceRegistry)
      throws MappingException {
    // None
  }
}
