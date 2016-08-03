package godziszewski.patryk.ElectronicsStore.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;

import godziszewski.patryk.ElectronicsStore.domain.Product;

@Configuration
@Import(godziszewski.patryk.ElectronicsStore.config.FlowConfiguration.class)
@EnableWebMvc
@ComponentScan(basePackages = "godziszewski.patryk")
public class WebConfig extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver viewResolver() {
	InternalResourceViewResolver resolver =	new InternalResourceViewResolver();
	resolver.setPrefix("/WEB-INF/views/");
	resolver.setSuffix(".jsp");
	resolver.setExposeContextBeansAsAttributes(true); 
	return resolver;
	}
	
	//enabling matrix variables
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
	     RequestMappingHandlerMapping requestMappingHandlerMapping = new RequestMappingHandlerMapping();
	    requestMappingHandlerMapping.setRemoveSemicolonContent(false); 
	    
	    return requestMappingHandlerMapping;
	}
	
	
	@Override
	public Validator getValidator() {
		return validator();
	}
	
		
	
	
	/*@Override
	public void configureDefaultServletHandling (
	DefaultServletHandlerConfigurer configurer) {
	configurer.enable();
	}*/
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resource/**").addResourceLocations("/resources/");
	}
	@Bean
	public ResourceBundleMessageSource messageSource()
	{
		ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
		rb.setBasename("messages");
		return rb;
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver()
	{
		CommonsMultipartResolver cm = new CommonsMultipartResolver();
		cm.setMaxUploadSize(10240000);
		return cm;
	}


@Bean
public ContentNegotiatingViewResolver contentResolver()
{
	ContentNegotiatingViewResolver cn = new ContentNegotiatingViewResolver();
	Map<String,String> mediaTypesMap = new HashMap<String,String>();
	
	
	mediaTypesMap.put("json", "application/json;charset=UTF-8");
	mediaTypesMap.put("xml", "application/xml;charset=UTF-8");
	mediaTypesMap.put("html", "text/html;charset=UTF-8");
	
	List<View> listOfViews = new ArrayList<View>();
	listOfViews.add(jsonView());
	listOfViews.add(xmlView());
	
	cn.setDefaultViews(listOfViews);
	cn.setMediaTypes(mediaTypesMap);
	return cn;
}

@Bean
public MappingJacksonJsonView jsonView()
{
	MappingJacksonJsonView mj= new MappingJacksonJsonView();
	mj.setPrettyPrint(true);
	return mj;
}



@Bean
public MarshallingView xmlView()
{
	Jaxb2Marshaller ja = new Jaxb2Marshaller();
	ja.setClassesToBeBound(Product.class);
	
	MarshallingView mv = new MarshallingView(ja);
	
	return mv;
}
@Bean
public LocalValidatorFactoryBean validator()
{
	LocalValidatorFactoryBean lv = new LocalValidatorFactoryBean();
	lv.setValidationMessageSource(messageSource());
	return lv;
}

}
