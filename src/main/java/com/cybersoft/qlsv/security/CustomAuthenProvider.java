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
        String userName = String.valueOf(authentication.getPrincipal());
        String password = String.valueOf(authentication.getCredentials());
        
        UserEntity userEntity = userRepository.findByUserName(userName);
        if(userEntity == null || !passwordEncoder.matches(password, userEntity.getPassword())){
            throw new BadCredentialsException("Username hoặc mật khẩu không đúng");
        }
               Collection<? extends GrantedAuthority> authorities = List.of( new SimpleGrantedAuthority(userEntity.getRole().getName())) // ví dụ: "ROLE_USER"
                ;

        // TRẢ VỀ token mới đã authenticated = true
        return new UsernamePasswordAuthenticationToken(userName, null, authorities);
    }

    @Override /*Định nghĩa của tham số Authentication mà đang được truyền ở hàm chứng thực */
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
