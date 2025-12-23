package io.awportfoiioapi.security.context;

import io.awportfoiioapi.member.entrity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class MemberContext implements UserDetails {
    
    private Member member;
    private List<GrantedAuthority> authorities;
    private Boolean isNewMember;
    
    public MemberContext(Member member, List<GrantedAuthority> authorities) {
        this.member = member;
        this.authorities = authorities;
    }
    
    public MemberContext(Member member, List<GrantedAuthority> authorities, Boolean isNewMember) {
        this.member = member;
        this.authorities = authorities;
        this.isNewMember = isNewMember;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    
    @Override
    public String getPassword() {
        return member.getPassword();
    }
    
    @Override
    public String getUsername() {
        return member.getLoginId();
    }
    
    public Boolean getNewMember() {
        return isNewMember;
    }
}
