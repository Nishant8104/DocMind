package com.itvedant.docmind.dto;

import java.util.Set;

import com.itvedant.docmind.models.User;

public class UserResponseDto {

    private Long id;
    private String username;
    private String email;
    private String mobile;
    private Set<String> roles;

    public UserResponseDto() {
    }

    public static UserResponseDto from(User user) {
        UserResponseDto response = new UserResponseDto();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setMobile(user.getMobile());
        response.setRoles(Set.copyOf(user.getRoles()));
        return response;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }
}
