package org.zerock.secu.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class SecuMemberDTO extends User {

    private String mname;

    public SecuMemberDTO(String username, String password, String mname, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.mname = mname;
    }
    
}