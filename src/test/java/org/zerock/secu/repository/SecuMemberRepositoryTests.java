package org.zerock.secu.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.secu.domain.SecuMember;
import org.zerock.secu.domain.SecuMemberRole;

@SpringBootTest
public class SecuMemberRepositoryTests {
    
    @Autowired
    private PasswordEncoder pwencoder;

    @Autowired
    private SecuMemberRepository secuMemberRepository;

    @Commit
    @Transactional
    @Test
    public void insertDummies() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            String str = "M"+i;
            String pw = pwencoder.encode(str);

            SecuMember member = SecuMember.builder()
            .mid(str).mpw(pw).mname("MEMBER"+i).build();
            
            
            SecuMemberRole role = 
                SecuMemberRole.builder().roleName("ROLE_MEMBER").build();
            member.addRole(role);
            
            if(i >= 80){
                member.addRole(SecuMemberRole.builder().roleName("ROLE_ADMIN").build());
            }
            secuMemberRepository.save(member);

        });


    }


}