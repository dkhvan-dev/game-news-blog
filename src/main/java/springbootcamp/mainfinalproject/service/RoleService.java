package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long roleId);
    Role getRoleByName(String roleName);
}
