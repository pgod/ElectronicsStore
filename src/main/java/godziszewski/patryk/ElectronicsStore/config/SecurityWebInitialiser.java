package godziszewski.patryk.ElectronicsStore.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

public class SecurityWebInitialiser extends AbstractSecurityWebApplicationInitializer {
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		super.beforeSpringSecurityFilterChain(servletContext);
		FilterRegistration.Dynamic encodingFilter = servletContext
				.addFilter("encoding-filter", new CharacterEncodingFilter());
		encodingFilter.setInitParameter("encoding", "UTF-8");
		encodingFilter.setInitParameter("forceEncoding", "true");
		/*the value false ensures that the CharacterEncodingFilter is the first filter in the chain
		 * "true" value adds the filter to the end of the filterChain*/
		encodingFilter.addMappingForUrlPatterns(null, false, "/*");
		}
}
