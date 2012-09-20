package org.codebone.domain.user;{

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
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private String Host = "";
	
	@Column
	private String User = "";
	
	@Column
	private String Password = "";
	
	@Column
	private String Select_priv = "N";
	
	@Column
	private String Insert_priv = "N";
	
	@Column
	private String Update_priv = "N";
	
	@Column
	private String Delete_priv = "N";
	
	@Column
	private String Create_priv = "N";
	
	@Column
	private String Drop_priv = "N";
	
	@Column
	private String Reload_priv = "N";
	
	@Column
	private String Shutdown_priv = "N";
	
	@Column
	private String Process_priv = "N";
	
	@Column
	private String File_priv = "N";
	
	@Column
	private String Grant_priv = "N";
	
	@Column
	private String References_priv = "N";
	
	@Column
	private String Index_priv = "N";
	
	@Column
	private String Alter_priv = "N";
	
	@Column
	private String Show_db_priv = "N";
	
	@Column
	private String Super_priv = "N";
	
	@Column
	private String Create_tmp_table_priv = "N";
	
	@Column
	private String Lock_tables_priv = "N";
	
	@Column
	private String Execute_priv = "N";
	
	@Column
	private String Repl_slave_priv = "N";
	
	@Column
	private String Repl_client_priv = "N";
	
	@Column
	private String Create_view_priv = "N";
	
	@Column
	private String Show_view_priv = "N";
	
	@Column
	private String Create_routine_priv = "N";
	
	@Column
	private String Alter_routine_priv = "N";
	
	@Column
	private String Create_user_priv = "N";
	
	@Column
	private String Event_priv = "N";
	
	@Column
	private String Trigger_priv = "N";
	
	@Column
	private String Create_tablespace_priv = "N";
	
	@Column
	private String ssl_type = "";
	
	@Column
	private String ssl_cipher = "";
	
	@Column
	private String x509_issuer = "";
	
	@Column
	private String x509_subject = "";
	
	@Column
	private Integer max_questions = 0;
	
	@Column
	private Integer max_updates = 0;
	
	@Column
	private Integer max_connections = 0;
	
	@Column
	private Integer max_user_connections = 0;
	
	@Column
	private String plugin = "";
	
	@Column
	private String authentication_string = "";
	
	
	
	public String getHost(){
		return Host;
	}

	public void setHost(String Host){
		this.Host = Host;
	}
	
	public String getUser(){
		return User;
	}

	public void setUser(String User){
		this.User = User;
	}
	
	public String getPassword(){
		return Password;
	}

	public void setPassword(String Password){
		this.Password = Password;
	}
	
	public String getSelect_priv(){
		return Select_priv;
	}

	public void setSelect_priv(String Select_priv){
		this.Select_priv = Select_priv;
	}
	
	public String getInsert_priv(){
		return Insert_priv;
	}

	public void setInsert_priv(String Insert_priv){
		this.Insert_priv = Insert_priv;
	}
	
	public String getUpdate_priv(){
		return Update_priv;
	}

	public void setUpdate_priv(String Update_priv){
		this.Update_priv = Update_priv;
	}
	
	public String getDelete_priv(){
		return Delete_priv;
	}

	public void setDelete_priv(String Delete_priv){
		this.Delete_priv = Delete_priv;
	}
	
	public String getCreate_priv(){
		return Create_priv;
	}

	public void setCreate_priv(String Create_priv){
		this.Create_priv = Create_priv;
	}
	
	public String getDrop_priv(){
		return Drop_priv;
	}

	public void setDrop_priv(String Drop_priv){
		this.Drop_priv = Drop_priv;
	}
	
	public String getReload_priv(){
		return Reload_priv;
	}

	public void setReload_priv(String Reload_priv){
		this.Reload_priv = Reload_priv;
	}
	
	public String getShutdown_priv(){
		return Shutdown_priv;
	}

	public void setShutdown_priv(String Shutdown_priv){
		this.Shutdown_priv = Shutdown_priv;
	}
	
	public String getProcess_priv(){
		return Process_priv;
	}

	public void setProcess_priv(String Process_priv){
		this.Process_priv = Process_priv;
	}
	
	public String getFile_priv(){
		return File_priv;
	}

	public void setFile_priv(String File_priv){
		this.File_priv = File_priv;
	}
	
	public String getGrant_priv(){
		return Grant_priv;
	}

	public void setGrant_priv(String Grant_priv){
		this.Grant_priv = Grant_priv;
	}
	
	public String getReferences_priv(){
		return References_priv;
	}

	public void setReferences_priv(String References_priv){
		this.References_priv = References_priv;
	}
	
	public String getIndex_priv(){
		return Index_priv;
	}

	public void setIndex_priv(String Index_priv){
		this.Index_priv = Index_priv;
	}
	
	public String getAlter_priv(){
		return Alter_priv;
	}

	public void setAlter_priv(String Alter_priv){
		this.Alter_priv = Alter_priv;
	}
	
	public String getShow_db_priv(){
		return Show_db_priv;
	}

	public void setShow_db_priv(String Show_db_priv){
		this.Show_db_priv = Show_db_priv;
	}
	
	public String getSuper_priv(){
		return Super_priv;
	}

	public void setSuper_priv(String Super_priv){
		this.Super_priv = Super_priv;
	}
	
	public String getCreate_tmp_table_priv(){
		return Create_tmp_table_priv;
	}

	public void setCreate_tmp_table_priv(String Create_tmp_table_priv){
		this.Create_tmp_table_priv = Create_tmp_table_priv;
	}
	
	public String getLock_tables_priv(){
		return Lock_tables_priv;
	}

	public void setLock_tables_priv(String Lock_tables_priv){
		this.Lock_tables_priv = Lock_tables_priv;
	}
	
	public String getExecute_priv(){
		return Execute_priv;
	}

	public void setExecute_priv(String Execute_priv){
		this.Execute_priv = Execute_priv;
	}
	
	public String getRepl_slave_priv(){
		return Repl_slave_priv;
	}

	public void setRepl_slave_priv(String Repl_slave_priv){
		this.Repl_slave_priv = Repl_slave_priv;
	}
	
	public String getRepl_client_priv(){
		return Repl_client_priv;
	}

	public void setRepl_client_priv(String Repl_client_priv){
		this.Repl_client_priv = Repl_client_priv;
	}
	
	public String getCreate_view_priv(){
		return Create_view_priv;
	}

	public void setCreate_view_priv(String Create_view_priv){
		this.Create_view_priv = Create_view_priv;
	}
	
	public String getShow_view_priv(){
		return Show_view_priv;
	}

	public void setShow_view_priv(String Show_view_priv){
		this.Show_view_priv = Show_view_priv;
	}
	
	public String getCreate_routine_priv(){
		return Create_routine_priv;
	}

	public void setCreate_routine_priv(String Create_routine_priv){
		this.Create_routine_priv = Create_routine_priv;
	}
	
	public String getAlter_routine_priv(){
		return Alter_routine_priv;
	}

	public void setAlter_routine_priv(String Alter_routine_priv){
		this.Alter_routine_priv = Alter_routine_priv;
	}
	
	public String getCreate_user_priv(){
		return Create_user_priv;
	}

	public void setCreate_user_priv(String Create_user_priv){
		this.Create_user_priv = Create_user_priv;
	}
	
	public String getEvent_priv(){
		return Event_priv;
	}

	public void setEvent_priv(String Event_priv){
		this.Event_priv = Event_priv;
	}
	
	public String getTrigger_priv(){
		return Trigger_priv;
	}

	public void setTrigger_priv(String Trigger_priv){
		this.Trigger_priv = Trigger_priv;
	}
	
	public String getCreate_tablespace_priv(){
		return Create_tablespace_priv;
	}

	public void setCreate_tablespace_priv(String Create_tablespace_priv){
		this.Create_tablespace_priv = Create_tablespace_priv;
	}
	
	public String getSsl_type(){
		return ssl_type;
	}

	public void setSsl_type(String ssl_type){
		this.ssl_type = ssl_type;
	}
	
	public String getSsl_cipher(){
		return ssl_cipher;
	}

	public void setSsl_cipher(String ssl_cipher){
		this.ssl_cipher = ssl_cipher;
	}
	
	public String getX509_issuer(){
		return x509_issuer;
	}

	public void setX509_issuer(String x509_issuer){
		this.x509_issuer = x509_issuer;
	}
	
	public String getX509_subject(){
		return x509_subject;
	}

	public void setX509_subject(String x509_subject){
		this.x509_subject = x509_subject;
	}
	
	public Integer getMax_questions(){
		return max_questions;
	}

	public void setMax_questions(Integer max_questions){
		this.max_questions = max_questions;
	}
	
	public Integer getMax_updates(){
		return max_updates;
	}

	public void setMax_updates(Integer max_updates){
		this.max_updates = max_updates;
	}
	
	public Integer getMax_connections(){
		return max_connections;
	}

	public void setMax_connections(Integer max_connections){
		this.max_connections = max_connections;
	}
	
	public Integer getMax_user_connections(){
		return max_user_connections;
	}

	public void setMax_user_connections(Integer max_user_connections){
		this.max_user_connections = max_user_connections;
	}
	
	public String getPlugin(){
		return plugin;
	}

	public void setPlugin(String plugin){
		this.plugin = plugin;
	}
	
	public String getAuthentication_string(){
		return authentication_string;
	}

	public void setAuthentication_string(String authentication_string){
		this.authentication_string = authentication_string;
	}
	
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
	public User() {
		super();
	}
	
	
}
