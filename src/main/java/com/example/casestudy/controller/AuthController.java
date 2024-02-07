package com.example.casestudy.controller;


import com.example.casestudy.dto.LoginDto;
import com.example.casestudy.dto.TenantDto;
import com.example.casestudy.security.TokenInfo;
import com.example.casestudy.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {

    private final TenantService tenantService;
    @PostMapping("/createTenant")
    public ResponseEntity<String> createTenant(@RequestBody TenantDto tenantDto){
        this.tenantService.createTenant(tenantDto);
        return ResponseEntity.ok("Tenant created successfully");
    }
    @PostMapping("/login")
    public TokenInfo login(@RequestBody TenantDto loginForm){
        return this.tenantService.login(loginForm);
    }
}
