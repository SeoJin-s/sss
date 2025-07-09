package com.example.ss.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ss.domain.UserDomain;

public class CustomerUserDetails implements UserDetails {
	private final UserDomain userDomain;

	public CustomerUserDetails(UserDomain userDomain) {
		this.userDomain = userDomain;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();

		String role = userDomain.getRole();
		if (role != null && !role.isBlank()) {
			authorities.add(() -> role);
		} else {
			throw new IllegalStateException("❌ 사용자 권한이 설정되어 있지 않습니다: " + userDomain.getUsername());
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return userDomain.getPassword();
	}

	@Override
	public String getUsername() {
		return userDomain.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
