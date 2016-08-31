package godziszewski.patryk.ElectronicsStore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;
import org.springframework.webflow.security.SecurityFlowExecutionListener;

public class FlowConfiguration extends AbstractFlowConfiguration {

	@Autowired
	Validator validator;
	@Bean
	public FlowDefinitionRegistry flowRegistry() {
	    return getFlowDefinitionRegistryBuilder()
	    		//flow registry makes flow not recognize beans ?
		    //.setFlowBuilderServices(flowBuilderServices())
	        .setBasePath("/WEB-INF/flows")
	        .addFlowLocationPattern("/**/*-flow.xml")
	        .build();
	}
	@Bean
	public FlowExecutor flowExecutor() {
	    return getFlowExecutorBuilder(flowRegistry())
	    			.addFlowExecutionListener(securityFlowExecutionListener())
	    			.build();
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
	@Bean
	public FlowBuilderServices flowBuilderServices()
	{
		return getFlowBuilderServicesBuilder()
				.setValidator(validator)
				.build();
	}
	@Bean
	public SecurityFlowExecutionListener securityFlowExecutionListener()
	{
		return new SecurityFlowExecutionListener();
	}
}
