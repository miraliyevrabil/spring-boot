package com.rabilmiraliyev.bookstore.security.spring;


import com.rabilmiraliyev.bookstore.model.Role;
import com.rabilmiraliyev.bookstore.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesService {

    @Autowired
    RoleRepository rolesRepository;

    public Role findOne(Long id)
    {
        return rolesRepository.findByIdAndStatusIsTrue(id);
    }

    public Role findByName(String name)
    {
        return rolesRepository.findByName(name);
    }
}
