package shop.tripn.api.security.config;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.tripn.api.security.domain.SecurityProvider;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SecurityProvider provider;
    private final UserDetailsService userDetailsService;

    @Bean PasswordEncoder encoder(){return new BCryptPasswordEncoder();}
    @Bean public PasswordEncoder passwordEncoder (){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean AuthenticationManager manager() throws Exception {return super.authenticationManagerBean();}
    @Bean ModelMapper mapper(){return new ModelMapper();}
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equalsIgnoreCase(encodedPassword);
            }
        });
        return authenticationProvider;
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS,"*/**")
                .antMatchers("/","/h2-console/");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/users/join").permitAll()
                .antMatchers("/users/login").permitAll()
                .antMatchers("/users/login/login").permitAll()
                .antMatchers("/users/mbti").permitAll()
                .antMatchers("/users/mbti/mbtiList").permitAll()
                .antMatchers("/users/mbti/mbti").permitAll()
                .antMatchers("/users/update").permitAll()
                .antMatchers("/users/updatePassword").permitAll()
                .antMatchers("/existsById/{username}").permitAll()
                .antMatchers("/mail/sendmail").permitAll()
                .antMatchers("/users/findAll").permitAll()
                .antMatchers("/users/list").permitAll()
                .antMatchers("/users/count").permitAll()
                .antMatchers("/users/userList/{username}/{birth}/{phone_number}").permitAll()
                .antMatchers("/users/userSearch").permitAll()
                .antMatchers("/userList/birth/{birth}/{phone_number}").permitAll()
                .antMatchers("/users/list/{keyword}").permitAll()
                .antMatchers("/boards/save").permitAll()
                .antMatchers("/boards/list").permitAll()
                .antMatchers("/boards/keyword/{keyword}").permitAll()
                .antMatchers("/user_files/uploadAjax").permitAll()
                .antMatchers("/h2-console/**/**").permitAll()
                .anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/login");
        //http.apply(new SecurityConfig(provider));
    }
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider());
        auth.authenticationProvider(provider);
    }
}