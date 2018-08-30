## Spring DI techniques
* https://www.zoltanraffai.com/blog/different-dependency-injection-techniques/

## Spring Boot
* Spring boot Dev tools: recognises what class loader is this being loaded by and will disable any functionality is production environment. Ex: If app run using java -jar.
* Add a macro for keymap cntrl + S so that each time you press it Spring Boot dev tools will restart the project quickly. This is for rapid development.
* Spring boot dev tools has a seperate class loader for the changed files so that this can be loaded seperately.
* Common Spring boot apps will have annotations such as:
  * @Configuration
  * @ComponentScan
  * @EnableAutoConfiguration
* All the above 3 annotations has been clubbed as one **@SpringBootApplication**.

## Generating an Auto Configuration
* Intelligent Decision making based on conditions:
   * Prescence/Abscence of a jar.
   * Prescence/Abscence of a Bean.
   * Prescence/Abscence of a Property.
   * Many More.
* **Auto-configuration report** to check what was auto configured and which one was not
  * Positive matches
  * Negative matches
  * Exclusions: Explicitly configured by the user.
  * Unconditional classes: Things which are auto configured by Spring regardless of any conditions
* Enable Auto configuration report:
  * debug=true
  * logging.level=debug
