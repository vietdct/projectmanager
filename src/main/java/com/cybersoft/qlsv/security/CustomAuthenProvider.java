package com.cybersoft.qlsv.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;

import com.cybersoft.qlsv.entity.UserEntity;
import com.cybersoft.qlsv.repository.UserRepository;

@Service
public class CustomAuthenProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override /**Hàm chứng thực */
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = String.valueOf(authentication.getPrincipal());
        // String userName = String.valueOf(authentication.getPrincipal());
        String password = String.valueOf(authentication.getCredentials());

        
        UserEntity userEntity = userRepository.findByUserName(id);
        if(userEntity == null){
            userEntity = userRepository.findByEmail(id);
        }
        if( userEntity == null || !passwordEncoder.matches(password, userEntity.getPassword())){
           throw new BadCredentialsException("UserName/Email or Password is Incorrect");
        }
        String role = userEntity.getRole().getName();
        if(!role.startsWith("ROLE_")){
            role =  "ROLE_"+role;
        }
        var authorities = List.of(new SimpleGrantedAuthority(role));
         return new UsernamePasswordAuthenticationToken(id, password,authorities);
    }

    @Override /*Định nghĩa của tham số Authentication mà đang được truyền ở hàm chứng thực */
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
