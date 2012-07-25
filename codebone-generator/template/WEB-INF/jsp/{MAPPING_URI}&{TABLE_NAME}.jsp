package <PACKAGE>;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codebone.framework.generic.AbstractModel;

@Entity
@Table
public class <TABLE_NAME_CAMELCASE> extends AbstractModel{
	<COLUMN_LOOP>
	<COLUMN_ID>@Column
	private <COLUMN_TYPE> <COLUMN_NAME>;
	</COLUMN_LOOP>
	
	<COLUMN_LOOP>
	private <COLUMN_TYPE> get<COLUMN_NAME_CAMELCASE>(){
		return <COLUMN_NAME>;
	}

	private void set<COLUMN_NAME_CAMELCASE>(<COLUMN_TYPE> <COLUMN_NAME>){
		this.<COLUMN_NAME> = <COLUMN_NAME>;
	}
	</COLUMN_LOOP>
}