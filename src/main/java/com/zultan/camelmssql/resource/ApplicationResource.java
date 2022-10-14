package com.zultan.camelmssql.resource;

import javax.sql.DataSource;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.stereotype.Component;


@Component
public class ApplicationResource extends RouteBuilder {

    @Autowired
    DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(AbstractDataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    
	@Override
	public void configure() throws Exception {
		
        from("timer:myTimer?repeatCount=1")
        .setBody(simple("select top 10 * from emp"))
        .log("SQL: ${body}")
        .to("jdbc:dataSource")
        .log("Out: ${body}");
        
	}

}
