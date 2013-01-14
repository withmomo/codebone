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
	private {{javaType}} {{name}} = {{{defaultValue}}};
	{{/columns}}
	
	{{#fkcolumns}}
	{{relationshipAnnotation}}({{option}})
	{{joinColumnAnnotation}}({{option}})
	private {{javaType}} {{name}} = new {{javaType}}();
	{{/fkcolumns}}
	
	{{#columns}}
	public {{javaType}} get{{nameCamelcase}}(){
		return {{name}};
	}

	public void set{{nameCamelcase}}({{javaType}} {{name}}){
		this.{{name}} = {{name}};
	}
	{{/columns}}
	{{#fkcolumns}}
	public {{javaType}} get{{nameCamelcase}}(){
		return {{name}};
	}

	public void set{{nameCamelcase}}({{javaType}} {{name}}){
		this.{{name}} = {{name}};
	}
	{{/fkcolumns}}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}