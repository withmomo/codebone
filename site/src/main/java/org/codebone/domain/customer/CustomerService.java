package org.codebone.domain.customer;

import java.util.List;

import org.apache.log4j.Logger;
import org.codebone.framework.BaseModel;
import org.codebone.framework.SuccessModel;
import org.codebone.framework.generic.AbstractDao;
import org.codebone.framework.generic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService{
	
	@Autowired
	private CustomerDao dao;
	
	protected static final Logger logger = Logger.getLogger(CustomerService.class);
	
	public AbstractDao getDao(){
		return dao;
	}
	
	public BaseModel create (Customer customer){
		logger.info("create model " + customer);
		getDao().create(customer);
		return new SuccessModel(customer);
	}
	
	public BaseModel read (String key){
		logger.info("read model by" + key);
		Customer customer = (Customer) getDao().read(key);
		return new SuccessModel(customer);
	}
	
	public BaseModel listAll(){
		logger.info("read model list All");
		List<Customer> list = getDao().listAll();
		return new SuccessModel(list);
	}
	
	public BaseModel list(int page){
		page=page-1;
		logger.info("read model list");
		List<Customer> list = getDao().list(page, 20+1);
		if(list.size()==21){
			list.remove(20);
			return new SuccessModel(list, true, getDao().count());
		}
		return new SuccessModel(list, false, getDao().count());
	}
	
	public BaseModel search(String property, String keyword, int page){
		page=page-1;
		logger.info("read model list");
		List<Customer> list = getDao().search(property, keyword, page, 20+1);
		if(list.size()==21){
			list.remove(20);
			return new SuccessModel(list, true, getDao().count());
		}
		return new SuccessModel(list, false, getDao().count());
	}
	
	public BaseModel update (Customer customer){
		logger.info("update model " + customer);
		Customer returnModel = (Customer)  getDao().update(customer);
		return new SuccessModel(returnModel);
	}
	
	public BaseModel delete (Customer customer){
		logger.info("delete model " + customer);
		getDao().delete(customer);
		return new SuccessModel();
	}
	
	public BaseModel delete (String idx){
		logger.info("delete model " + idx);
		getDao().delete(getDao().read(idx));
		return new SuccessModel();
	}
}
