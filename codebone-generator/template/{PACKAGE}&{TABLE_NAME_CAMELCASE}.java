package <PACKAGE>;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codebone.framework.generic.AbstractModel;

@Entity
@Table
public class <TABLE_NAME_CAMELCASE> extends AbstractModel{
	<COLUMN_LOOP>
	<COLUMN_HIBERNATE_ID_ANNOATION>@Column
	private <COLUMN_TYPE> <COLUMN_NAME>;
	</COLUMN_LOOP>
	
	<COLUMN_LOOP>
	public <COLUMN_TYPE> get<COLUMN_NAME_CAMELCASE>(){
		return <COLUMN_NAME>;
	}

	public void set<COLUMN_NAME_CAMELCASE>(<COLUMN_TYPE> <COLUMN_NAME>){
		this.<COLUMN_NAME> = <COLUMN_NAME>;
	}
	</COLUMN_LOOP>
}