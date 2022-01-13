package com.springreactsecurity.security;

import com.springreactsecurity.domain.member.Member;
import com.springreactsecurity.exception.AccountException;
import com.springreactsecurity.exception.ErrorType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUserInfoUtil {

    public static Member getCurrentUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new AccountException(ErrorType.UNAUTHENTICATED);
        }

        UserMember userMember = (UserMember) authentication.getPrincipal();
        Member member = userMember.getMember();
        if (member == null) {
            throw new AccountException(ErrorType.UNAUTHENTICATED);
        }

        return member;
    }

}
