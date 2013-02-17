package {{package}};

import java.util.List;
import java.util.Date;

{{#columns}}
{{#foreignKey}}
import java.util.List;
import {{anotherPackage}};
{{/foreignKey}}
{{/columns}}

public class {{tableNameCamelcase}}{
	
	public {{tableNameCamelcase}}() {
		super();
	}
	
	{{#columns}}
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
}