package {{package}};

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table
public class {{tableNameCamelcase}}{
	
	public {{tableNameCamelcase}}() {
		super();
	}
	
	{{#columns}}
	{{#primaryKey}}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	{{/primaryKey}}
	@Column
	private {{typeName}} {{name}} = {{{defaultValue}}};
	{{/columns}}
	
	{{#columns}}
	public {{typeName}} get{{nameCamelcase}}(){
		return {{name}};
	}

	public void set{{nameCamelcase}}({{typeName}} {{name}}){
		this.{{name}} = {{name}};
	}
	{{/columns}}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}