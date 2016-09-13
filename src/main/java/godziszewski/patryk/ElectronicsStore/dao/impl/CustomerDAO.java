package godziszewski.patryk.ElectronicsStore.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import godziszewski.patryk.ElectronicsStore.dao.CustomerRepository;
import godziszewski.patryk.ElectronicsStore.model.Customer;
@Repository
public class CustomerDAO extends AbstractDAO<Integer, Customer> implements CustomerRepository {

	@Override
	public Customer getCustomerByEmail(String email) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("email", email));
        return (Customer) criteria.uniqueResult();
	}
	@Override
	public void save(Customer customer) {
		persist(customer);
	}
	@Override
	public Customer getCustomerById(Integer id) {
		return getByKey(id);
	}
}
