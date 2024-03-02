package devangelistesecurity.SpringSecurity.service.impl;

import devangelistesecurity.SpringSecurity.model.entity.UserEntity;
import devangelistesecurity.SpringSecurity.repository.UserRepository;
import devangelistesecurity.SpringSecurity.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }
}
