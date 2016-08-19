package godziszewski.patryk.ElectronicsStore.domain.repository.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import godziszewski.patryk.ElectronicsStore.domain.Product;
import godziszewski.patryk.ElectronicsStore.domain.repository.ProductRepository;
@Repository
@Transactional
public class ProductDAO extends AbstractDAO<Integer, Product> implements ProductRepository{

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
	public List<Product> getProductsByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProduct(Product product) {
		persist(product);
		
		//Uploading product image
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
