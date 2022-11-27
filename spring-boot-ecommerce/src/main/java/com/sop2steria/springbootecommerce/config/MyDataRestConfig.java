package com.sop2steria.springbootecommerce.config;

import com.sop2steria.springbootecommerce.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager){
        entityManager=theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
    HttpMethod[] theUnsupportedactions = {HttpMethod.PUT, HttpMethod.POST,
                                    HttpMethod.DELETE, HttpMethod.PATCH};

    //disable Httpmethods for Product: Put, Post, Delete
        disableHttpMethods(Product.class, config,theUnsupportedactions);

        //disable Httpmethods for ProdctCategory: Put, Post, Delete
        disableHttpMethods(ProductCategory.class, config,theUnsupportedactions);
        //disable Httpmethods for State: Put, Post, Delete
        disableHttpMethods(State.class, config,theUnsupportedactions);
        //disable Httpmethods for Country: Put, Post, Delete
        disableHttpMethods(Country.class, config,theUnsupportedactions);
        //disable Httpmethods for Order: Put, Post, Delete
        disableHttpMethods(Order.class, config,theUnsupportedactions);

        //internal method
        exposeIds(config);
        //configure cors mapping
        cors.addMapping(config.getBasePath()+"/**").allowedOrigins(theAllowedOrigins);
    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions){

        config.getExposureConfiguration().forDomainType(theClass)
                .withItemExposure((metadata,httpMethods)->httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metadata,httpMethods)->httpMethods.disable(theUnsupportedActions));
    }


    private void exposeIds(RepositoryRestConfiguration config){

        // get a list of all entity classes from entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        //create an array of entity types
        List<Class> entityClasses = new ArrayList<>();

        //get the entity type for the entities
        for(EntityType tempEntityType: entities){
            entityClasses.add(tempEntityType.getJavaType());
        }
        // expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
