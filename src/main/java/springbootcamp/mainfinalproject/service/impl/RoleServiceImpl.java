package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.Role;
import springbootcamp.mainfinalproject.repository.RoleRepository;
import springbootcamp.mainfinalproject.service.RoleService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long roleId) {
        Role role = roleRepository.findById(roleId).orElse(null);
        if (role != null) {
            return role;
        }
        return null;
    }
}
