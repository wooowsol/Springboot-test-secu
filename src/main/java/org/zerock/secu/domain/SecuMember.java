package org.zerock.secu.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SecuMember {
    
    @Id
    private String mid;

    private String mpw;

    private String mname;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "secuMember")
    private Set<SecuMemberRole> roleSets;

    public void addRole(SecuMemberRole role){
        if(roleSets == null){
            roleSets = new HashSet<>();
        }
        roleSets.add(role);
    }
}