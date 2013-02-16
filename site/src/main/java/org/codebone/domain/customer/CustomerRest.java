package org.codebone.domain.customer;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.codebone.framework.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;



@Path("/customer")
@Component
@Api(value = "/customer", basePath="/rest", description = "customer API")
@Scope("singleton")
public class CustomerRest{

	/**
	 * Logger
	 */
	private static final Logger logger = Logger.getLogger("rest");

	/**
	 * Service
	 */
	@Autowired(required=true)
	private CustomerService service;

	@POST
	@Consumes({ "application/xml", "application/json" })
	@Produces({ "application/xml", "application/json" })
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Customer Create")
	public Customer create(
			@ApiParam(value="Creating Customer Data", required=true) @ModelAttribute Customer model) {
		logger.debug( "data is" + model);
		// Create Model
		BaseModel returnModel = service.create(model);
		// Return Result
		return (Customer) returnModel.getData();
	}
	
	@GET
	@Path("/{idx}")
	@Consumes({ "application/xml", "application/json" })
	@Produces({ "application/xml", "application/json" })
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Customer Read")
	public Customer read(
			@ApiParam(value="Reading Customer index") @PathParam("idx") String idx) {
		logger.debug( "idx is " + idx);
		// Create Model
		BaseModel returnModel = service.read(idx);
		// Return Result
		return (Customer) returnModel.getData();
	}
	
	@GET
	@Path("/list")
	@Consumes({ "application/xml", "application/json" })
	@Produces({ "application/xml", "application/json" })
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Customer List Read")
	public List<Customer> list(
			@ApiParam(value="List Page") @QueryParam("page") Integer page) {
		logger.debug( "page is " + page);
		// Create Model
		BaseModel returnModel = service.list(page);
		// Return Result
		return (List) returnModel.getData();
	}
	
	@PUT
	@Consumes({ "application/xml", "application/json" })
	@Produces({ "application/xml", "application/json" })
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Customer Update")
	public Customer update(
			@ApiParam(value="Updating Customer Data") @ModelAttribute Customer model) {
		logger.debug( "data is" + model);
		// Create Model
		BaseModel returnModel = service.update(model);
		// Return Result
		return (Customer) returnModel.getData();
	}
	
	@DELETE
	@Path("/{idx}")
	@Consumes({ "application/xml", "application/json" })
	@Produces({ "application/xml", "application/json" })
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Customer Delete")
	public BaseModel delete(
			@ApiParam(value="Deleting Customer index")@PathParam("idx") String idx) {
		logger.debug( "idx is " + idx);
		// Create Model
		BaseModel returnModel = service.delete(idx);
		// Return Result
		return returnModel;
	}
}
