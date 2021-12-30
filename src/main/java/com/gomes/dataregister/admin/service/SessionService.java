package com.gomes.dataregister.admin.service;

import com.gomes.dataregister.admin.model.AuthUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    public AuthUserDetails getSessionUser() {
        return (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
