package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.RoleColor;
import springbootcamp.mainfinalproject.repository.RoleColorRepository;
import springbootcamp.mainfinalproject.service.RoleColorService;

@Service
@RequiredArgsConstructor
public class RoleColorServiceImpl implements RoleColorService {
    private final RoleColorRepository roleColorRepository;

    @Override
    public RoleColor addColor(RoleColor roleColor) {
        if (roleColor.getRoleColorId() == null) {
            roleColorRepository.save(roleColor);
        }
        return null;
    }

    @Override
    public void deleteColor(Long roleColorId) {
        RoleColor roleColor = roleColorRepository.findById(roleColorId).orElse(null);
        if (roleColor != null) {
            roleColorRepository.delete(roleColor);
        }
    }
}
