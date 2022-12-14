package com.letscode.ecommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.letscode.ecommerce.configuration.security.JWTAuthenticationFilter;
import com.letscode.ecommerce.configuration.security.JWTAuthorizationFilter;
import com.letscode.ecommerce.configuration.security.UserServiceImpl;

@Configuration
@EnableWebSecurity //Para ter a possibilidade de customizar
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class WebSecurityConfig {

    final UserServiceImpl userDetailsService;

    //Inicializa o servico, que sera utilizado para fazer a autenticacao.
    public WebSecurityConfig(UserServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    //objeto bean para a criptacao das senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //Todas as requests passarao por esse filtro
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //Criacao do AuthenticationManager, servico que cuida das autenticacoes. No caso, userDetailsService
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        http.cors().and().csrf().disable()//Habilita CORS (Cross-origin resource sharing, requests vindas de outro domain sao permitidas) e desabilita CSRF (Cross-site request forgery)
                .authorizeRequests()
                    //Requests para as URLs e metodos abaixo sao  permitidas SEM AUTENTICACAO
                    .antMatchers(HttpMethod.POST, "/cliente").permitAll()
                    .antMatchers(HttpMethod.POST, "/login").permitAll()
                    .antMatchers(HttpMethod.GET, "/lerProdutos").permitAll()
                    .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll() // Para testes
                    .antMatchers(HttpMethod.GET, "/hello").authenticated()
                    .antMatchers(HttpMethod.GET, "/cliente").permitAll() //Temporario para testes
                    .antMatchers("/instances/**").permitAll()
                    .antMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated() //Qualquerr outra requisicao obriga autenticacao
                .and()
                //Filtros para as outras requisicoes, que requerem autenticacao e autorizacaoo
                .addFilter(new JWTAuthenticationFilter(authenticationManager)) //autenticacao
                .addFilter(new JWTAuthorizationFilter(authenticationManager)) //autorizacao
                //Indicar qual authentication manager sera um utilizado
                .authenticationManager(authenticationManager)
                //Criar policy
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*");
            }
        };
    }

}

