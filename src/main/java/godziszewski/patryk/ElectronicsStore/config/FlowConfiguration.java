package godziszewski.patryk.ElectronicsStore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.executor.FlowExecutor;


@Configuration
public class FlowConfiguration extends AbstractFlowConfiguration {
	
	
	@Bean
	public FlowDefinitionRegistry flowRegistry() {
	    return getFlowDefinitionRegistryBuilder()
	        .setBasePath("/WEB-INF/flows")
	        .addFlowLocation("/checkout/checkout-flow.xml","checkout")
	        .build();
	}
	@Bean
	public FlowExecutor flowExecutor() {
	    return getFlowExecutorBuilder(flowRegistry()).build();
	}
	

	/*
	 * <bean id="flowHandlerMapping" class="org.springframework.webflow.mvc.servlet.FlowHandlerMapping">
		<property name="flowRegistry" ref="flowRegistry" />
	</bean>
	<bean id="flowHandlerAdapter" class="org.springframework.webflow.mvc.servlet.FlowHandlerAdapter">
		<property name="flowExecutor" ref="flowExecutor" />
	</bean>
	
	 */
	/*@Bean
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
	}*/
}
