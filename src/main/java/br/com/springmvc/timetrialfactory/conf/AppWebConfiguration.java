package br.com.springmvc.timetrialfactory.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.springmvc.timetrialfactory.apis.paypal.PayPalCall;
import br.com.springmvc.timetrialfactory.controllers.HomeController;
import br.com.springmvc.timetrialfactory.controllers.PaymentController;
import br.com.springmvc.timetrialfactory.daos.GameDAO;
import br.com.springmvc.timetrialfactory.daos.PurchaseDAO;
import br.com.springmvc.timetrialfactory.models.ShoppingCart;
import br.com.springmvc.timetrialfactory.models.UserWeb;

@EnableWebMvc
@ComponentScan(basePackageClasses = { HomeController.class, GameDAO.class, PurchaseDAO.class, ShoppingCart.class,
		PaymentController.class, PayPalCall.class, UserWeb.class })
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposedContextBeanNames("shoppingCart");
		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("/WEB-INF/messages");
		bundle.setDefaultEncoding("UTF-8");
		bundle.setCacheSeconds(1);
		return bundle;
	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
