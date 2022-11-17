package com.sop2steria.springbootecommerce.config;

import com.sop2steria.springbootecommerce.entity.Product;
import com.sop2steria.springbootecommerce.entity.ProductCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
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
    }
}
