package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.RoleColor;

public interface RoleColorService {
    RoleColor addColor(RoleColor roleColor);
    void deleteColor(Long roleColorId);
}
