package com.sop2steria.springbootecommerce.dao;

import com.sop2steria.springbootecommerce.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "countries",path = "countries")
public interface CountryRepository extends JpaRepository<Country,Integer> {
}
