package godziszewski.patryk.ElectronicsStore.config;

import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



public class ElectronicsStoreWebInitialiser extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		FilterRegistration.Dynamic encodingFilter = servletContext
				.addFilter("encoding-filter", new CharacterEncodingFilter());
	    encodingFilter.setInitParameter("encoding", "UTF-8");
	    encodingFilter.setInitParameter("forceEncoding", "true");
	    /*the value false ensures that the CharacterEncodingFilter is the first filter in the chain,
	    the value true adds the filter to the end of the filterChain*/
	    encodingFilter.addMappingForUrlPatterns(null, false, "/*");
	}
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(
				new MultipartConfigElement("/tmp/images",
						2097152, 4194304, 0));
	}
}
