package com.springreactsecurity.security;

import com.springreactsecurity.domain.member.Member;
import com.springreactsecurity.domain.member.MemberRepository;
import com.springreactsecurity.exception.AccountException;
import com.springreactsecurity.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserId(username).orElseThrow(() -> new AccountException(ErrorType.USER_NOT_EXISTS));
        return new UserMember(member);
    }

}
