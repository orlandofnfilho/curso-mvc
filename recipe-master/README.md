# Recipe

*Recipe* is an MVC application that manages food recipes.

## Business Case

Food recipes are usually stored on notes and sometimes are lost and forgotten. A solution is needed for preserving this recipe for future generations.
We need an application that is going to provide everyone the possibility to add recipes.

## Technology

*Recipe* is using the following technologies:

- Java [version: 8] (the language used to write the application)
- Maven [version: 3.6] (the tool for managing dependencies and building the project)
- Liquibase [version: 3.8.7] (for managing the database changes)
- H2 Database [version: 1.4.200] (for providing a relational database)
- Spring-Boot [version: 2.2.5.RELEASE] (the framework for creating spring application that just runs)
- Spring-Boot-Data-JPA [version: 2.2.5.RELEASE] (the dependency for easier access and manipulation of a relational database)
- Spring-Boot-Thymeleaf [version: 2.3.0 Release] (the Java template engine for both web and standalone environments)

## Implementation Details

We are creating a web application so we add the below dependency starter:

```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
```

The above dependency is used for building the web application, including RESTful applications using Spring MVC. It uses Tomcat as the default embedded container.

The following dependency entry uses Spring Boot’s Thymeleaf starter to make Thymeleaf available for rendering the view you’re about to create.

```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
```

Below are the dependencies needed for the web application to communicate with the database.

```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
```
Below we have configured the web application to communicate with H2 database:

```
#H2 DB 
spring.jpa.hibernate.ddl-auto=none
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:recipedb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=test
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

We have chosen Liquibase to manage database schema changes. Below is the dependency needed for including Liquibase:

```
		<dependency>
			<groupId>org.liquibase</groupId>
			<artifactId>liquibase-core</artifactId>
		</dependency>
```

Below is the configuration information for Liquibase:

```
#Liquibase 
spring.liquibase.change-log=classpath:db/liquibase-changelog.xml
spring.liquibase.enabled=true
```

Liquibase uses a changelog to explicitly list database changes in order. The changelog acts as a ledger of changes and contains a list of changesets (units of change) that Liquibase can execute on a database. The property ```spring.liquibase.enabled enable``` Liquibase in our application and we determine the location of changelog file in ```spring.liquibase.change-log``` property.

Below is the content of liquibase-changelog.xml:

```
<databaseChangeLog
	xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
         http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.1.xsd">

	<include file="changelog/01-create-recipe-scheme.xml"
	relativeToChangelogFile="true" />
	<include file="changelog/02-data-insert-recipes.xml"
		relativeToChangelogFile="true" />

</databaseChangeLog>
```
The 01-create-recipe-scheme.xml file has the changeset for creating the schema of the database, as we can see below:

```
<databaseChangeLog
	xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
         http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.1.xsd">
	<changeSet id="01" author="rshtishi">

		<createTable tableName="recipe">
			<column name="id" type="int" autoIncrement="true">
				<constraints nullable="false" primaryKey="true" />
			</column>
			<column name="name" type="varchar(30)">
				<constraints nullable="false" />
			</column>
			<column name="description" type="varchar(200)">
				<constraints nullable="true" />
			</column>
		</createTable>

	</changeSet>
</databaseChangeLog>
```

The 02-data-insert-recipes.xml file has the changeset for populating the tables with information. Below is the content of the file:

```
<databaseChangeLog
	xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
         http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.1.xsd">
	<changeSet id="02" author="rshtishi">
		<insert tableName="recipe">
			<column name="id" valueNumeric="1" />
			<column name="name" value="Apple Pie" />
			<column name="description" value="Recipe for apple pie." />
		</insert>
		<insert tableName="recipe">
			<column name="id" valueNumeric="2" />
			<column name="name" value="Sushi" />
			<column name="description" value="Recipe for sushi." />
		</insert>
	</changeSet>
</databaseChangeLog>
```

Configuring the handler serves static content from any file in /static directory:

```
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/img/**", "/css/**", "/js/**").addResourceLocations(
				"classpath:/static/img/", "classpath:/static/css/",
				"classpath:/static/js/");
	}
```

Creating a custom handler interceptor for logging in our web application.

```
public class LoggerInterceptor implements HandlerInterceptor {

	private static Logger logger = (Logger) LoggerFactory.getLogger(LoggerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("[preHandle][" + request.getClass() + "][" + request.getMethod() + "]:" + request.getRequestURI());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("[postHandle]" + request.getClass() + "][" + request.getMethod() + "]:" + request.getRequestURI());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		logger.info("[afterCompletion]" + request.getClass() + "][" + request.getMethod() + "]:" + request.getRequestURI());
	}

}
```

Registering the Logging Interceptor in our application:

```
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
	
	@Bean
	public LoggerInterceptor loggerInterceptor() {
		return new LoggerInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggerInterceptor());
	}
	
}
```

For the purpose of adding internationalization to our application, we have added an interceptor bean that will switch to a new locale based on the value of the ```language``` parameter appended to a request. 

```
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
	
	@Bean
	public LoggerInterceptor loggerInterceptor() {
		return new LoggerInterceptor();
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		return localeChangeInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggerInterceptor());
		registry.addInterceptor(localeChangeInterceptor());
	}
	
}
```

In order to take effect, ```localeChangeInterceptor``` bean needs to be added to the application's interceptor registry.

## Setup

Execute the following commands:

- mvn clean install (to build the project)
- mvn spring-boot:run (to run the project)

Access application in url below:

- http://localhost:8080/recipes [Http method: GET] (home page of recipes application)


