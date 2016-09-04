package godziszewski.patryk.ElectronicsStore.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
@PropertySource("classpath:/email.properties")
public class EmailConfig {
	
	@Autowired
	Environment environment;
	@Bean
	public JavaMailSender javaMailSender()
	{
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(environment.getRequiredProperty("mail.host"));
        mailSender.setPort(Integer.parseInt(
        		environment.getRequiredProperty("mail.port")));
        mailSender.setUsername(environment.getRequiredProperty("mail.username"));
        mailSender.setPassword(environment.getRequiredProperty("mail.password"));
        mailSender.setDefaultEncoding("UTF-8");
         
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", environment.getRequiredProperty("mail.smtp.starttls.enable"));
        javaMailProperties.put("mail.smtp.auth", environment.getRequiredProperty("mail.smtp.auth"));
        javaMailProperties.put("mail.transport.protocol", environment.getRequiredProperty("mail.transport.protocol"));
        javaMailProperties.put("mail.debug", environment.getRequiredProperty("mail.debug"));//Prints out everything on screen
         
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
		
	}
	@Bean
    public FreeMarkerConfigurationFactoryBean freeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean fmcf = new FreeMarkerConfigurationFactoryBean();
        fmcf.setTemplateLoaderPath("classpath:/emailTemplates/");
        return fmcf;
    }
}
