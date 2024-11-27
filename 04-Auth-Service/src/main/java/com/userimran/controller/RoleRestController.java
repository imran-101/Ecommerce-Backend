package com.userimran.controller;

import com.userimran.entity.Role;
import com.userimran.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role/api")
public class RoleRestController {

    public final RoleService roleService;

    @PostMapping("/register")
    public ResponseEntity<Role> addRole(@RequestBody Role role) throws Exception {
        Role fetchedRole = roleService.createRole(role);
        if (fetchedRole != null) {
            return new ResponseEntity<>(fetchedRole, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles(){
        List<Role> allRoles = roleService.getAllRoles();
        if (allRoles != null)
            return ResponseEntity.ok(allRoles);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<Role> getRoleById(@PathVariable("roleId") Integer roleId){
        Role roleById = roleService.getRoleById(roleId);
        if (roleById != null)
            return ResponseEntity.ok(roleById);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<String> deleteRoleById(@PathVariable("roleId") Integer roleId){
        String s = roleService.deleteRoleById(roleId);
        if(s != null)
            return ResponseEntity.ok(s);
        return ResponseEntity.noContent().build();
    }
}
