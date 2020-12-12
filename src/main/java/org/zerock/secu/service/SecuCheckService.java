package org.zerock.secu.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.zerock.secu.domain.SecuMember;
import org.zerock.secu.dto.SecuMemberDTO;
import org.zerock.secu.repository.SecuMemberRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SecuCheckService implements UserDetailsService {

    @Autowired
    private SecuMemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        log.info("loadUser.." + username);

        Optional<SecuMember> result = repository.findById(username);

        if(result.isPresent()){

            SecuMember member = result.get();
            log.info(member);

            List<SimpleGrantedAuthority> list = member.getRoleSets().stream()
            .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
            .collect(Collectors.toList());

            SecuMemberDTO dto = new SecuMemberDTO(member.getMid()
            ,member.getMpw(), member.getMname(), list);

            return dto;

        }

        return null;
    }
    
}