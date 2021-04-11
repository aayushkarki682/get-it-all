package com.springframework.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class PasswordEncoderFactories {
    public static PasswordEncoder createDelegatingPasswordEncoder(){
        String encodingId = "bcrypt10";
        Map<String, PasswordEncoder> encoders =new HashMap<>();
        encoders.put(encodingId, new BCryptPasswordEncoder(10));
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("ldap", new org.springframework.security.crypto.password.LdapShaPasswordEncoder());
        encoders.put("noop", org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
        encoders.put("sha256", new org.springframework.security.crypto.password.StandardPasswordEncoder());

        return new DelegatingPasswordEncoder(encodingId, encoders);
    }

    //cannot instantiate this class
    private PasswordEncoderFactories(){}
}
