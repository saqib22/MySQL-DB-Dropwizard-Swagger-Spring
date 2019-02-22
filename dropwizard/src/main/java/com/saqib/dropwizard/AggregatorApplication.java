package com.saqib.dropwizard;

import java.util.EnumSet;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class AggregatorApplication extends Application<AggregatorConfiguration> {
	public AggregatorApplication() {
	}
	public static void main(String[] args) throws Exception {
		new AggregatorApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<AggregatorConfiguration> bootstrap) {
		bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
				bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));
		bootstrap.addBundle(new AssetsBundle());
		bootstrap.addBundle(new ViewBundle<AggregatorConfiguration>() {
			@Override
			public Map<String, Map<String, String>> getViewConfiguration(AggregatorConfiguration configuration) {
				return configuration.getViewRendererConfiguration();
			}
		});

		bootstrap.addBundle(new SwaggerBundle<AggregatorConfiguration>() {
			@Override
			public SwaggerBundleConfiguration getSwaggerBundleConfiguration(AggregatorConfiguration configuration) {
				return configuration.getSwaggerBundleConfiguration();
			}
		});
	}

	@Override
	public void run(AggregatorConfiguration configuration, Environment environment) {

		final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

		// Configure CORS parameters
		cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
		cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM,
				"X-Requested-With,Content-Type,Accept,Origin,Authorization");
		cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
		cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");
		// Add URL mapping
		cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
		PersonResources idmsDocsResource = new PersonResources(environment.getValidator());
		environment.jersey().register(idmsDocsResource);

	}	
}
