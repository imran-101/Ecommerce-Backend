package com.userimran.service;

import com.userimran.entity.Role;

public interface RoleService {
    public Role createRole(Role role);
    public <list>Role getAllRoles();
    public Role getRoleById(Integer id);
    public Role deleteRoleById(Integer id);
}
