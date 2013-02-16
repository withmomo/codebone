package org.codebone.domain.customer;

import org.codebone.framework.generic.AbstractDao;
import org.springframework.stereotype.Repository;
import java.io.Serializable;

@Repository
public class CustomerDao extends AbstractDao<Customer>{
	@Override
	protected Class<Customer> getEntityClass() {
		return Customer.class;
	}
	protected Serializable parse(String key) {
		return new Long(key);
	}
}
