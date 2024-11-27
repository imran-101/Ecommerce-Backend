package com.userimran.service;

import com.userimran.entity.Role;

import java.util.List;

public interface RoleService {
    public Role createRole(Role role);

    public List<Role> getAllRoles();

    public Role getRoleById(Integer id);

    public String deleteRoleById(Integer id);
}
