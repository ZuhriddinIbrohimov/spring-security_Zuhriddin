package zuhriddinscode.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import zuhriddinscode.entity.ProfileEntity;
import zuhriddinscode.enums.GeneralStatus;
import zuhriddinscode.enums.ProfileRoleEnum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String phone;
    private String password;
    private ProfileRoleEnum role;
    private GeneralStatus status;


    public CustomUserDetails(ProfileEntity profile) {
        this.phone = profile.getPhone();
        this.password = profile.getPassword();
        this.role = profile.getRole();
        this.status = profile.getStatus();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = new ArrayList< >();
        roles.add(new SimpleGrantedAuthority(role.name()));
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status.equals(GeneralStatus.ACTIVE);
    }  // bloklanganmi

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    } // permissonlari vaqti otib ketmaganmi

    @Override
    public boolean isEnabled() {
        return true;   // 23 lesson security codeuz
    }  // sistemada user ihlashi mumkinmi

}