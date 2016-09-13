package godziszewski.patryk.ElectronicsStore.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import godziszewski.patryk.ElectronicsStore.model.Order;
import godziszewski.patryk.ElectronicsStore.service.MailService;

@Service
public class MailServiceImpl implements MailService{
	
	 @Autowired
	 JavaMailSender mailSender;
	 
	 @Autowired
	 Configuration freemarkerConfiguration;

	 @Override
	 public void sendEmail(Order order) {
		 MimeMessagePreparator preparator = getMessagePreparator(order);
		 try {
			 mailSender.send(preparator);
			 } catch (MailException ex) {
				 System.err.println(ex.getMessage());
				 }
		 }
	 private MimeMessagePreparator getMessagePreparator(Order order) {
		 MimeMessagePreparator preparator = new MimeMessagePreparator() {
			 @Override
			 public void prepare(MimeMessage mimeMessage) throws Exception {
				 MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false);
				 
				 helper.setSubject("Order Confirmation");
				 helper.setTo(order.getCustomer().getEmail());
				 
				 Map<String, Object> model = new HashMap<String, Object>();
				 model.put("order", order);
				 
				 helper.setText(getFreeMarkerTemplateContent(model), true);
				 }};
				 return preparator;
				 }
	 private String getFreeMarkerTemplateContent(Map<String, Object> model){
		 StringBuffer content = new StringBuffer();
		 try{
			 content.append(FreeMarkerTemplateUtils.processTemplateIntoString( 
					 freemarkerConfiguration.getTemplate("orderSentTemplate.txt"),model));
			 return content.toString();
			 }catch(Exception e){
				 System.out.println("Exception occured while processing template:"+e.getMessage());
				 }
		 return "";
		 }
}
