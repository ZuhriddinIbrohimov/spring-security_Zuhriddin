package zuhriddinscode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;

@Configuration
@EnableWebSecurity
public class SpringConfig {

    //Authentication  Ya'ni berilgan login va parolli user bor yoki yo'qligini aniqlash.
    @Bean
    public AuthenticationProvider authenticationProvider(){
        String password = UUID.randomUUID().toString();
        System.out.println("using generated security password mazgi: "+password);

        UserDetails userDetails = User.builder()
                .username("user")
                .password("{noop}"+password)
                .roles("USER","ADMIN","LATTA")
                .build();

        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsPasswordService(new InMemoryUserDetailsManager(userDetails));
        return daoAuthenticationProvider;
    }


    //Authorization
    // Foydalanuvchiga ruxsat berish qaysi apilarga kirish
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception {
        http.authorizeHttpRequests( authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry
                    .requestMatchers("/task").permitAll()  // bu yerda task url ga kirish uchun ruxsat berdik hammaga
                    .requestMatchers("/task/*").permitAll()
                    .requestMatchers(HttpMethod.GET, "/task/*").permitAll()  // faqat get uchun
                    .requestMatchers("/task/finished/all", "task/my/all").permitAll()
                    .requestMatchers("/admin/dashboard").hasRole("Admin")   //admin rolli  admin apiga kira oladi
                    .requestMatchers("/").hasAnyRole("ADMIN", "USER")    //  bir nechta  role berish mumkin

                    .anyRequest().authenticated();  // qolgan barcha requestni authenticate qilish kerak
        });
//          .formLogin(Customizer.withDefaults());   // spring security taqdim qilgan login bu api + web yani oddiysi http basic ni , api chiqarmasdan
        http.httpBasic(Customizer.withDefaults()); // http basic example
//
//        http.csrf(Customizer.withDefaults()); // csrf yoqilgan
//        http.cors(Customizer.withDefaults()); // cors yoqilgan

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);     // mobile , frotend bo'lgfan payti o'chirib qo'yamiz
        return http.build();
    }

}