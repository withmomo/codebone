package {{package}};

import org.codebone.R;
import org.codebone.RestActivity;
import java.util.List;

{{#columns}}
{{#foreignKey}}
import {{anotherPackage}};
{{/foreignKey}}
{{/columns}}

import retrofit.http.RetrofitError;

import android.os.Bundle;
import android.widget.TextView;

public class {{tableNameCamelcase}}Activity extends RestActivity {

	{{tableNameCamelcase}}RestService {{tableNameVariablecase}}RestService = restAdapter.create({{tableNameCamelcase}}RestService.class);
	List<{{tableNameCamelcase}}> {{tableNameVariablecase}} = null;
	TextView textview;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		run();
	}
	
	@Override
	protected void beforeAsyncJob() {
		textview = (TextView)findViewById(R.id.textview);
	}

	@Override
	protected void doAsyncJob() {
		try {
			{{tableNameVariablecase}} = {{tableNameVariablecase}}RestService.getList(1);
		} catch(RetrofitError e) {
			e.getMessage();
		}
	}

	@Override
	protected void afterAsyncJob() {
		textview.setText({{tableNameVariablecase}}.toString());
	}
}