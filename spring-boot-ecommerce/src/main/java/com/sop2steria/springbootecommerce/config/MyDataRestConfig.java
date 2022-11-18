package com.sop2steria.springbootecommerce.config;

import com.sop2steria.springbootecommerce.entity.Product;
import com.sop2steria.springbootecommerce.entity.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    private EntityManager entityManager;


    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager){
        entityManager=theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
    HttpMethod[] theUnsupportedactions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

    //disable Httpmethods for Product: Put, Post, Delete
        config.getExposureConfiguration().forDomainType(Product.class)
                .withItemExposure((metadata,httpMethods)->httpMethods.disable(theUnsupportedactions))
                .withCollectionExposure((metadata,httpMethods)->httpMethods.disable(theUnsupportedactions));

        //disable Httpmethods for ProdctCategory: Put, Post, Delete
        config.getExposureConfiguration().forDomainType(ProductCategory.class)
                .withItemExposure((metadata,httpMethods)->httpMethods.disable(theUnsupportedactions))
                .withCollectionExposure((metadata,httpMethods)->httpMethods.disable(theUnsupportedactions));
        //internal method
        exposeIds(config);
    }

    private void exposeIds(RepositoryRestConfiguration config){

        // get a list of all entity classes from entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        //create an array of entity types
        List<Class> entityClasses = new ArrayList<>();

        //get the entity type for tje entities
        for(EntityType tempEntityType: entities){
            entityClasses.add(tempEntityType.getJavaType());
        }
        // expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
