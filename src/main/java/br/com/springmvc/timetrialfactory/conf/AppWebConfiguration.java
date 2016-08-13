package br.com.springmvc.timetrialfactory.conf;

import static com.google.common.cache.CacheBuilder.newBuilder;

import java.util.ArrayList;
import java.util.List;
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

import br.com.springmvc.timetrialfactory.apis.paypal.PayPalCall;
import br.com.springmvc.timetrialfactory.controllers.AuthenticationController;
import br.com.springmvc.timetrialfactory.controllers.GamesController;
import br.com.springmvc.timetrialfactory.controllers.HomeController;
import br.com.springmvc.timetrialfactory.controllers.ShoppingCartController;
import br.com.springmvc.timetrialfactory.controllers.UserController;
import br.com.springmvc.timetrialfactory.controllers.VisitorController;
import br.com.springmvc.timetrialfactory.daos.PurchaseDAO;
import br.com.springmvc.timetrialfactory.daos.impl.GameDAOImpl;
import br.com.springmvc.timetrialfactory.daos.impl.UserDAOImpl;
import br.com.springmvc.timetrialfactory.models.LoggedUser;
import br.com.springmvc.timetrialfactory.models.ShoppingCart;
import br.com.springmvc.timetrialfactory.security.SecurityConfiguration;
import br.com.springmvc.timetrialfactory.services.GameServiceImpl;
import br.com.springmvc.timetrialfactory.services.UserServiceImpl;
import br.com.springmvc.timetrialfactory.validation.UserValidator;
import br.com.springmvc.timetrialfactory.viewresolver.JsonViewResolver;

@Configuration
@EnableWebMvc
@EnableCaching
@ComponentScan(basePackageClasses = { HomeController.class, GameDAOImpl.class, GameServiceImpl.class,
		UserServiceImpl.class, UserDAOImpl.class, PurchaseDAO.class, ShoppingCart.class, ShoppingCartController.class,
		PayPalCall.class, LoggedUser.class, VisitorController.class, UserValidator.class,
		AuthenticationController.class, UserController.class, GamesController.class })
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
}
