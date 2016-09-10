package godziszewski.patryk.ElectronicsStore.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;
import org.springframework.web.util.UrlPathHelper;

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
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		urlPathHelper.setRemoveSemicolonContent(false);
		configurer.setUrlPathHelper(urlPathHelper);
		}
	@Override
	public Validator getValidator() {
		return validator();
		}
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resource/**").addResourceLocations("/resources/");
	    }
	@Bean
	TilesConfigurer tilesConfigurer() {
		TilesConfigurer tiles = new TilesConfigurer();
		tiles.setDefinitions(new String[] {
				"/WEB-INF/tiles/definition/tile-definition.xml"
		});
		tiles.setCheckRefresh(true);
		return tiles;
		}
	@Bean
	public ViewResolver tilesViewResolver() {
		TilesViewResolver tv = new TilesViewResolver();
		tv.setOrder(-2);
		return  tv;
		}
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
		rb.setBasename("messages");
		return rb;
		}
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
		}
	@Bean
	public ContentNegotiatingViewResolver contentResolver() {
		ContentNegotiatingViewResolver cn = new ContentNegotiatingViewResolver();
	
		List<View> listOfViews = new ArrayList<View>();
		listOfViews.add(jsonView());
		listOfViews.add(xmlView());
	
		cn.setDefaultViews(listOfViews);
		
		return cn;
		}
	@Bean
	public MappingJackson2JsonView jsonView() {
		MappingJackson2JsonView mj= new MappingJackson2JsonView();
		mj.setPrettyPrint(true);
		return mj;
		}
	@Bean
	public MarshallingView xmlView() {
		Jaxb2Marshaller ja = new Jaxb2Marshaller();
		ja.setClassesToBeBound(Product.class);
		MarshallingView mv = new MarshallingView(ja);
		return mv;
		}
	@Bean
	public Validator validator() {
		LocalValidatorFactoryBean lv = new LocalValidatorFactoryBean();
		lv.setValidationMessageSource(messageSource());
		return lv;
		}
}
