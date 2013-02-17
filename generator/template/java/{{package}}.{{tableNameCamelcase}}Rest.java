package {{package}};

import java.util.List;

import javax.ws.rs.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.codebone.framework.BaseModel;
import org.codebone.framework.SuccessModel;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;



@Path("/{{mappingUri}}")
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
	@Consumes({ "application/xml", "application/json" })
	@Produces({ "application/xml", "application/json" })
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "{{tableNameCamelcase}} Create")
	public {{tableNameCamelcase}} create(
			@ApiParam(value="Creating {{tableNameCamelcase}} Data")@ModelAttribute {{tableNameCamelcase}} model) {
		logger.debug( "data is" + model);
		// Create Model
		BaseModel returnModel = service.create(model);
		// Return Result
		return ({{tableNameCamelcase}}) returnModel.getData();
	}
	
	@GET
	@Path("/{idx}")
	@Consumes({ "application/xml", "application/json" })
	@Produces({ "application/xml", "application/json" })
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "{{tableNameCamelcase}} Read")
	public {{tableNameCamelcase}} read(
			@ApiParam(value="Reading {{tableNameCamelcase}} index")@PathParam("idx") {{primaryKeyType}} idx) {
		logger.debug( "idx is " + idx);
		// Create Model
		BaseModel returnModel = service.read(idx);
		// Return Result
		return ({{tableNameCamelcase}}) returnModel.getData();
	}
	
	@GET
	@Path("/")
	@Consumes({ "application/xml", "application/json" })
	@Produces({ "application/xml", "application/json" })
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "{{tableNameCamelcase}} List Read")
	public List<{{tableNameCamelcase}}> list(
			@ApiParam(value="List Page")@QueryParam("page") Integer page) {

		if( page == null )
			page = 1;
		
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
	@ApiOperation(value = "{{tableNameCamelcase}} Update")
	public {{tableNameCamelcase}} update(
			@ApiParam(value="Updating {{tableNameCamelcase}} Data")@ModelAttribute {{tableNameCamelcase}} model) {
		logger.debug( "data is" + model);
		// Create Model
		BaseModel returnModel = service.update(model);
		// Return Result
		return ({{tableNameCamelcase}}) returnModel.getData();
	}
	
	@DELETE
	@Path("/{idx}")
	@Consumes({ "application/xml", "application/json" })
	@Produces({ "application/xml", "application/json" })
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "{{tableNameCamelcase}} Delete")
	public BaseModel delete(
			@ApiParam(value="Deleting {{tableNameCamelcase}} index")@PathParam("idx") {{primaryKeyType}} idx) {
		logger.debug( "idx is " + idx);
		// Create Model
		BaseModel returnModel = service.delete(idx);
		// Return Result
		return returnModel;
	}
}
