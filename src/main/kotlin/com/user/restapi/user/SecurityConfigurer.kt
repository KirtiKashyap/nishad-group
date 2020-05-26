package com.user.restapi.user

import com.user.restapi.user.service.MyUserDetailService
import com.user.restapi.user.util.JwtRequestFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class SecurityConfigurer(@Autowired val myUserDetailService : MyUserDetailService, @Autowired val jwtRequestFilter : JwtRequestFilter) : WebSecurityConfigurerAdapter() {
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(myUserDetailService)
        //super.configure(auth)
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests().antMatchers("/authenticate","/register").permitAll().anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter::class.java)
    }
    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
    @Bean
    fun passwordEncoder() : PasswordEncoder{
        return NoOpPasswordEncoder.getInstance()
    }
}