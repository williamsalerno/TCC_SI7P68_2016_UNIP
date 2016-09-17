package br.com.timetrialfactory.maestro.conf;

import static com.google.common.cache.CacheBuilder.newBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.timetrialfactory.maestro.apis.paypal.PayPalCall;
import br.com.timetrialfactory.maestro.assembler.UserAssembler;
import br.com.timetrialfactory.maestro.controllers.AuthenticationController;
import br.com.timetrialfactory.maestro.controllers.GamesController;
import br.com.timetrialfactory.maestro.controllers.HomeController;
import br.com.timetrialfactory.maestro.controllers.ShoppingCartController;
import br.com.timetrialfactory.maestro.controllers.UserController;
import br.com.timetrialfactory.maestro.controllers.VisitorController;
import br.com.timetrialfactory.maestro.daos.impl.GameDAOImpl;
import br.com.timetrialfactory.maestro.daos.impl.LicenseDAOImpl;
import br.com.timetrialfactory.maestro.daos.impl.PurchaseDAOImpl;
import br.com.timetrialfactory.maestro.daos.impl.UserDAOImpl;
import br.com.timetrialfactory.maestro.email.EmailSender;
import br.com.timetrialfactory.maestro.models.LoggedUser;
import br.com.timetrialfactory.maestro.models.ShoppingCart;
import br.com.timetrialfactory.maestro.security.SecurityConfiguration;
import br.com.timetrialfactory.maestro.services.GameServiceImpl;
import br.com.timetrialfactory.maestro.services.LicenseServiceImpl;
import br.com.timetrialfactory.maestro.services.PurchaseServiceImpl;
import br.com.timetrialfactory.maestro.services.UserServiceImpl;
import br.com.timetrialfactory.maestro.validation.UserValidator;
import br.com.timetrialfactory.maestro.viewresolver.JsonViewResolver;

@Configuration
@EnableWebMvc
@EnableCaching
@ComponentScan(basePackageClasses = { HomeController.class, GameDAOImpl.class, GameServiceImpl.class,
		UserServiceImpl.class, UserDAOImpl.class, UserAssembler.class, PurchaseDAOImpl.class, PurchaseServiceImpl.class,
		LicenseDAOImpl.class, LicenseServiceImpl.class, ShoppingCart.class, ShoppingCartController.class,
		PayPalCall.class, LoggedUser.class, VisitorController.class, UserValidator.class,
		AuthenticationController.class, UserController.class, GamesController.class, EmailSender.class })
@Import({ SecurityConfiguration.class })
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public CacheManager CacheManager() {
		CacheBuilder<Object, Object> builder = newBuilder().maximumSize(100).expireAfterAccess(2, TimeUnit.SECONDS);
		GuavaCacheManager cacheManager = new GuavaCacheManager();
		cacheManager.setCacheBuilder(builder);
		return cacheManager;
	}

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
		bundle.setCacheSeconds(-1);
		return bundle;
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
		registry.addResourceHandler("/img/**").addResourceLocations("/img/");
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		resolvers.add(internalResourceViewResolver());
		resolvers.add(new JsonViewResolver());

		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(resolvers);
		resolver.setContentNegotiationManager(manager);
		return resolver;
	}

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LocaleChangeInterceptor());
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new CookieLocaleResolver();
	}

	@Bean
	public MailSender mailSender() {
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost("smtp.gmail.com");
		javaMailSenderImpl.setPassword("102938W!ll");
		javaMailSenderImpl.setPort(587);
		javaMailSenderImpl.setUsername("timetrial.fac@gmail.com");
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", true);
		mailProperties.put("mail.smtp.starttls.enable", true);
		javaMailSenderImpl.setJavaMailProperties(mailProperties);
		return javaMailSenderImpl;
	}
}
