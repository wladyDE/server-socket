package org.example.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.dao.impl.RoleDAOImpl;
import org.example.domain.Role;
import org.example.service.CRUDService;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleServiceImpl implements CRUDService<Role> {
    final RoleDAOImpl roleDAO = new RoleDAOImpl();
    
    @Override
    public void save(Role role) {
        roleDAO.insert(role);
    }

    @Override
    public void update(Role role) {
        roleDAO.update(role);
    }

    @Override
    public Role findById(int id) {
        return roleDAO.findById(id);
    }

    @Override
    public void deleteById(int id) {
        roleDAO.deleteById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleDAO.findAll();
    }
}
