package godziszewski.patryk.ElectronicsStore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;


@Configuration
public class FlowConfiguration extends AbstractFlowConfiguration {
	
	
	@Bean
	public FlowDefinitionRegistry flowRegistry() {
	    return getFlowDefinitionRegistryBuilder()
	        .setBasePath("/WEB-INF/flows")
	        .addFlowLocationPattern("/**/*-flow.xml")
	        .build();
	}
	@Bean
	public FlowExecutor flowExecutor() {
	    return getFlowExecutorBuilder(flowRegistry()).build();
	}

	@Bean
	public FlowHandlerMapping flowHandlerMapping()
	{
		FlowHandlerMapping fh = new FlowHandlerMapping();
		fh.setFlowRegistry(flowRegistry());
		return fh;
	}
	@Bean
	public FlowHandlerAdapter flowHandlerAdapter()
	{
		FlowHandlerAdapter fh = new FlowHandlerAdapter();
		fh.setFlowExecutor(flowExecutor());
		return fh;
	}
}
