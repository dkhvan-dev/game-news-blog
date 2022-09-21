package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.Role;
import springbootcamp.mainfinalproject.model.RoleColor;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long roleId);
    Role getRoleByName(String roleName);
    Role addRole(Role role, RoleColor roleColor);
    void deleteRole(String roleName);
}
