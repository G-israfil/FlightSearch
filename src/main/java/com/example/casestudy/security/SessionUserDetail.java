package com.example.casestudy.security;

import java.util.List;
import java.util.UUID;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class SessionUserDetail implements UserDetails {

    private List<GrantedAuthority> authorities           = null;
    private String                 password;
    private String                 username;
    private UUID                   uuid;
    private boolean                accountNonExpired     = true;
    private boolean                accountNonLocked      = true;
    private boolean                credentialsNonExpired = true;
    private boolean                enabled               = true;

}