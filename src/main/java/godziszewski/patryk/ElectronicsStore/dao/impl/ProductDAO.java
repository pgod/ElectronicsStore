package godziszewski.patryk.ElectronicsStore.dao.impl;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import godziszewski.patryk.ElectronicsStore.dao.ProductDao;
import godziszewski.patryk.ElectronicsStore.model.Product;
@Repository
public class ProductDAO extends AbstractDAO<Integer, Product> implements ProductDao{

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {
		Criteria criteria = createEntityCriteria();
        return (List<Product>) criteria.list();
	}
	
	@Override
	public Product getProductById(Integer productId) {
		 return getByKey(productId);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getProductsByCategory(String category) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("category", category));
		return (List<Product>) criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		Set <String> productCriterias = filterParams.keySet();
		
		Criteria criteria = createEntityCriteria();
		Disjunction or = Restrictions.disjunction();
		
		if(productCriterias.contains("brand"))
		{
			for(String brandName : filterParams.get("brand"))
			{
				or.add(Restrictions.eq("manufacturer",brandName));
			}
		}
		criteria.add(or);
		or=Restrictions.disjunction();
		if(productCriterias.contains("category"))
		{
			for(String categoryName : filterParams.get("category"))
			{
				or.add(Restrictions.eq("category",categoryName));
			}
		}
		criteria.add(or);
		return(List<Product>) criteria.list();
	}

	@Override
	public void addProduct(Product product) {
		persist(product);
		uploadProductImage(product);
	}
	private void uploadProductImage(Product product)
	{
		MultipartFile productImage = product.getProductImage();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String rootdirectory =  request.getServletContext().getRealPath("/");
		if(productImage != null && !productImage.isEmpty())
		{
			try {
				FileCopyUtils.copy(productImage.getBytes(), 
						new File( rootdirectory + "resources\\images\\"+product.getProductId()+".png"));
			}  catch (Exception e) {
				throw new RuntimeException("Error occoured while uploading image of the product", e);
			}
		}
	}
}
