package com.example.casestudy.service;

import com.example.casestudy.dto.TenantDto;
import com.example.casestudy.entity.Tenant;
import com.example.casestudy.repository.TenantRepository;
import com.example.casestudy.security.JwtTokenUtil;
import com.example.casestudy.security.SessionUserDetail;
import com.example.casestudy.security.TokenInfo;
import com.example.casestudy.utils.DigestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TenantService implements UserDetailsService {
    private final TenantRepository tenantRepository;
    private final JwtTokenUtil tokenUtil;
    public void createTenant(TenantDto tenantDto){
        final Tenant tenant = new Tenant();
        final String salt = RandomStringUtils.randomAlphanumeric(16);
        tenant.setSalt(salt);
        tenant.setUuid(UUID.randomUUID());
        tenant.setHash(DigestUtils.sha256Hex(tenantDto.getPassword() + salt));
        tenant.setUsername(tenantDto.getUsername());
        this.tenantRepository.save(tenant);
    }

    public TokenInfo login(TenantDto loginForm){
        Tenant tenant = this.tenantRepository.findTenantByUsername(loginForm.getUsername());
        if(Objects.isNull(tenant)) throw new RuntimeException("User not found!");
        var hash = DigestUtils.sha256Hex(loginForm.getPassword() + tenant.getSalt());

        if (!hash.equals(tenant.getHash())) {
            throw new RuntimeException("Incorrect username or password");
        }
        SessionUserDetail tenantInfo = new SessionUserDetail();
        BeanUtils.copyProperties(tenant, tenantInfo);
        tenantInfo.setUsername(tenant.getUsername());

        return TokenInfo.builder().accessToken(this.tokenUtil.generateToken(tenantInfo)).build();
    }
    public SessionUserDetail loadUserByUsername(String username){
        final Tenant tenant = this.tenantRepository.findTenantByUsername(username);
         if(Objects.isNull(tenant)) throw new RuntimeException("User not found!");
        SessionUserDetail result = new SessionUserDetail();
        result.setUsername(tenant.getUsername());
        result.setPassword(tenant.getHash());
        result.setUuid(tenant.getUuid());
        result.setAuthorities(new ArrayList<>());
        return result;
    }

    public Tenant getTenantByUsername(String username){
        return this.tenantRepository.findTenantByUsername(username);
    }
}
