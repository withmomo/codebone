/**
 * Copyright © 2011 Software in Life Inc. All rights reserved.
 */
package com.teletalkvi.User;

import javax.ws.rs.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;



@Path("/{{tableName}}")
@Component
@Api(value = "/{{tableName}}", basePath="/rest", description = "{{tableName}} API")
@Scope("singleton")
public class {{tableNameCamelcase}}Rest{

	/**
	 * Logger
	 */
	private static final Logger logger = Logger.getLogger("rest");

	/**
	 * Service
	 */
	@Autowired(required=true)
	private {{tableNameCamelcase}}Service service;

	@POST
	@Consumes("{application/json, application/xml}")
	@Produces("{application/json, application/xml}")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "{{tableNameCamelcase}} Create")
	public BaseModel create(
			@ApiParam(value="Creating {{tableNameCamelcase}} Data")@ModelAttribute {{tableNameCamelcase}} model) {
		logger.debug( "data is" + model);
		// Create Model
		BaseModel returnModel = service.create(model);
		// Return Result
		return returnModel;
	}
	
	@GET
	@Path("/{idx}")
	@Consumes("{application/json, application/xml}")
	@Produces("{application/json, application/xml}")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "{{tableNameCamelcase}} Read")
	public BaseModel read(
			@ApiParam(value="Reading {{tableNameCamelcase}} index")@PathParam("idx") {{primaryKeyType}} idx) {
		logger.debug( "idx is " + idx);
		// Create Model
		BaseModel returnModel = service.read(idx);
		// Return Result
		return returnModel;
	}
	
	@PUT
	@Consumes("{application/json, application/xml}")
	@Produces("{application/json, application/xml}")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "{{tableNameCamelcase}} Update")
	public BaseModel create(
			@ApiParam(value="Updating {{tableNameCamelcase}} Data")@ModelAttribute {{tableNameCamelcase}} model) {
		logger.debug( "data is" + model);
		// Create Model
		BaseModel returnModel = service.update(model);
		// Return Result
		return returnModel;
	}
	
	@DELETE
	@Path("/{idx}")
	@Consumes("{application/json, application/xml}")
	@Produces("{application/json, application/xml}")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "{{tableNameCamelcase}} Delete")
	public BaseModel read(
			@ApiParam(value="Deleting {{tableNameCamelcase}} index")@PathParam("idx") {{primaryKeyType}} idx) {
		logger.debug( "idx is " + idx);
		// Create Model
		BaseModel returnModel = service.delete(idx);
		// Return Result
		return returnModel;
	}
}
