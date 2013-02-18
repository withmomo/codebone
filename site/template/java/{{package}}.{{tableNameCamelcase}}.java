package {{package}};

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

{{#columns}}
{{#foreignKey}}
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;

import java.util.List;
import {{anotherPackage}};
{{/foreignKey}}
{{/columns}}

import javax.xml.bind.annotation.*;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name="{{tableName}}")
@XmlRootElement(name = "{{tableName}}")
@XmlAccessorType(XmlAccessType.FIELD)
public class {{tableNameCamelcase}}{
	
	public {{tableNameCamelcase}}() {
		super();
	}
	
	{{#columns}}
	{{#primaryKey}}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	{{/primaryKey}}
	{{#foreignKey}}
	{{relationAnnotation}}
	{{{optionAnnotation}}}
	{{/foreignKey}}
	{{^foreignKey}}
	@Column
	{{/foreignKey}}
	private {{{javaType}}} {{name}} = {{{defaultValue}}};
	
	{{/columns}}
	
	{{#columns}}
	public {{{javaType}}} get{{nameCamelcase}}(){
		return {{name}};
	}

	public void set{{nameCamelcase}}({{{javaType}}} {{name}}){
		this.{{name}} = {{name}};
	}
	{{/columns}}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}