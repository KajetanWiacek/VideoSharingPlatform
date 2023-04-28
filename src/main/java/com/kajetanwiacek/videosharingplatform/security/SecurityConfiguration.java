package com.kajetanwiacek.videosharingplatform.security;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfiguration {
  //    UserDetailsServiceImpl userDetailsService;
  //
  //    @Autowired
  //    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService) {
  //        this.userDetailsService = userDetailsService;
  //    }
  //
  //    @Override
  //    protected void configure(HttpSecurity http) throws Exception {
  //        http
  //                .csrf().disable()
  //                .authorizeRequests()
  //                .antMatchers("/users/register")
  //                .permitAll()
  //                .anyRequest()
  //                .authenticated()
  //                .and()
  //                .httpBasic()
  //                .and()
  //                .formLogin();
  //    }
  //
  //    @Bean
  //    public PasswordEncoder getPasswordEncoder(){
  //        return new BCryptPasswordEncoder();
  //    }
  //
  //    @Override
  //    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  //        auth
  //                .userDetailsService(userDetailsService)
  //                .passwordEncoder(getPasswordEncoder());
  //    }
}
