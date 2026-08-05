PS C:\Users\alois\Desktop\CDA\ProjetSpringBoot\backend> mvn test
[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------< fr.cda:gestion-scolaire-api >---------------------
[INFO] Building gestion-scolaire-api 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ gestion-scolaire-api ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] 
[INFO] --- compiler:3.14.0:compile (default-compile) @ gestion-scolaire-api ---
[INFO] Nothing to compile - all classes are up to date.
[INFO] 
[INFO] --- resources:3.3.1:testResources (default-testResources) @ gestion-scolaire-api ---
[INFO] skip non existing resourceDirectory C:\Users\alois\Desktop\CDA\ProjetSpringBoot\backend\src\test\resources
[INFO] 
[INFO] --- compiler:3.14.0:testCompile (default-testCompile) @ gestion-scolaire-api ---
[INFO] Nothing to compile - all classes are up to date.
[INFO] 
[INFO] --- surefire:3.5.3:test (default-test) @ gestion-scolaire-api ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running fr.cda.ecole.GestionScolaireApiApplicationTests
11:29:16.671 [main] INFO org.springframework.boot.devtools.restart.RestartApplicationListener -- Restart disabled due to context in which it is running

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.5.4)

2026-08-05T11:29:16.940+02:00  INFO 27896 --- [gestion-scolaire-api] [           main] f.c.e.GestionScolaireApiApplicationTests : Starting GestionScolaireApiApplicationTests using Java 21.0.11 with PID 27896 (started by alois in C:\Users\alois\Desktop\CDA\ProjetSpringBoot\backend)
2026-08-05T11:29:16.941+02:00  INFO 27896 --- [gestion-scolaire-api] [           main] f.c.e.GestionScolaireApiApplicationTests : No active profile set, falling back to 1 default profile: "default"
2026-08-05T11:29:18.360+02:00  WARN 27896 --- [gestion-scolaire-api] [           main] .s.s.UserDetailsServiceAutoConfiguration : 

Using generated security password: 351c2913-5437-4018-8129-cf5957207b45

This generated password is for development use only. Your security configuration must be updated before running your application in production.

2026-08-05T11:29:18.362+02:00  INFO 27896 --- [gestion-scolaire-api] [           main] r$InitializeUserDetailsManagerConfigurer : Global AuthenticationManager configured with UserDetailsService bean with name inMemoryUserDetailsManager
2026-08-05T11:29:18.486+02:00  INFO 27896 --- [gestion-scolaire-api] [           main] f.c.e.GestionScolaireApiApplicationTests : Started GestionScolaireApiApplicationTests in 1.83 seconds (process running for 2.516)
Mockito is currently self-attaching to enable the inline-mock-maker. This will no longer work in future releases of the JDK. Please add Mockito as an agent to your build as described in Mockito's documentation: https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/Mockito.html#0.3
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
WARNING: A Java agent has been loaded dynamically (C:\Users\alois\.m2\repository\net\bytebuddy\byte-buddy-agent\1.17.6\byte-buddy-agent-1.17.6.jar)
WARNING: If a serviceability tool is in use, please run with -XX:+EnableDynamicAgentLoading to hide this warning
WARNING: If a serviceability tool is not in use, please run with -Djdk.instrument.traceUsage for more information
WARNING: Dynamic loading of agents will be disallowed by default in a future release
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.552 s -- in fr.cda.ecole.GestionScolaireApiApplicationTests
[INFO] Running fr.cda.ecole.integration.EleveControllerIntegrationTest
2026-08-05T11:29:18.987+02:00  INFO 27896 --- [gestion-scolaire-api] [           main] o.s.b.d.r.RestartApplicationListener     : Restart disabled due to context in which it is running

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.5.4)

2026-08-05T11:29:19.012+02:00  INFO 27896 --- [gestion-scolaire-api] [           main] f.c.e.i.EleveControllerIntegrationTest   : Starting EleveControllerIntegrationTest using Java 21.0.11 with PID 27896 (started by alois in C:\Users\alois\Desktop\CDA\ProjetSpringBoot\backend)
2026-08-05T11:29:19.012+02:00  INFO 27896 --- [gestion-scolaire-api] [           main] f.c.e.i.EleveControllerIntegrationTest   : No active profile set, falling back to 1 default profile: "default"
2026-08-05T11:29:19.658+02:00  INFO 27896 --- [gestion-scolaire-api] [           main] eAuthenticationProviderManagerConfigurer : Global AuthenticationManager configured with AuthenticationProvider bean with name authenticationProvider
2026-08-05T11:29:19.658+02:00  WARN 27896 --- [gestion-scolaire-api] [           main] r$InitializeUserDetailsManagerConfigurer : Global AuthenticationManager configured with an AuthenticationProvider bean. UserDetailsService beans will not be used by Spring Security for automatically configuring username/password login. Consider removing the AuthenticationProvider bean. Alternatively, consider using the UserDetailsService in a manually instantiated DaoAuthenticationProvider. If the current configuration is intentional, to turn off this warning, increase the logging level of 'org.springframework.security.config.annotation.authentication.configuration.InitializeUserDetailsBeanManagerConfigurer' to ERROR
2026-08-05T11:29:20.012+02:00  INFO 27896 --- [gestion-scolaire-api] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2026-08-05T11:29:20.012+02:00  INFO 27896 --- [gestion-scolaire-api] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2026-08-05T11:29:20.012+02:00  INFO 27896 --- [gestion-scolaire-api] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
2026-08-05T11:29:20.050+02:00  INFO 27896 --- [gestion-scolaire-api] [           main] f.c.e.i.EleveControllerIntegrationTest   : Started EleveControllerIntegrationTest in 1.063 seconds (process running for 4.083)
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.665 s -- in fr.cda.ecole.integration.EleveControllerIntegrationTest
[INFO] Running fr.cda.ecole.service.EleveServiceTest
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.147 s -- in fr.cda.ecole.service.EleveServiceTest
[INFO] Running fr.cda.ecole.service.NoteServiceTest
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.062 s -- in fr.cda.ecole.service.NoteServiceTest
[INFO] Running fr.cda.ecole.service.UserServiceTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.134 s -- in fr.cda.ecole.service.UserServiceTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 17, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  7.398 s
[INFO] Finished at: 2026-08-05T11:29:22+02:00
[INFO] ------------------------------------------------------------------------