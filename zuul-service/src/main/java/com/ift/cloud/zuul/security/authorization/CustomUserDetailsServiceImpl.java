package com.ift.cloud.zuul.security.authorization;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 身份认证
 *
 * @author liufei
 * @date 2020/6/21
 */
@Component
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin".equals(username)) {
            return new User(username, passwordEncoder.encode("admin"),
                    true, true, true, true,
                    AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
        } else if ("user".equals(username)) {
            return new User(username, passwordEncoder.encode("user"),
                    true, true, true, true,
                    AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
        }
        throw new UsernameNotFoundException("未查询到用户信息！");
    }
}
