package com.userimran.service;

import com.userimran.entity.Role;
import com.userimran.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        Role fetchedRole = roleRepository.findByRoleName(role.getRoleName());
        if (fetchedRole == null) {
            return roleRepository.save(role);
        }
        return fetchedRole;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).orElseThrow();
    }

    @Override
    public String deleteRoleById(Integer id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role != null) {
            roleRepository.delete(role);
            return "Role deleted successfully";
        }
        return null;
    }
}
