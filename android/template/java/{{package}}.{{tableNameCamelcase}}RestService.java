package {{package}};

import java.util.List;

import javax.inject.Named;

import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.SingleEntity;

public interface {{tableNameCamelcase}}RestService {
	
	@POST("/api/{{mappingUri}}")
	void create(@SingleEntity {{tableNameCamelcase}} {{tableNameLowercase}});
	
	@GET("/api/{{mappingUri}}")
	List<{{tableNameCamelcase}}> getList(@Named("page") int page);
	
	@GET("/api/{{mappingUri}}/{id}")
	{{tableNameCamelcase}} get(@Named("id") {{primaryKeyType}} id);
	
	@PUT("/api/{{mappingUri}}")
	void update(@SingleEntity {{tableNameCamelcase}} {{tableNameLowercase}});
	
	@DELETE("/api/{{mappingUri}}/{id}")
	{{tableNameCamelcase}} delete(@Named("id") {{primaryKeyType}} id);
}
