package devangelistesecurity.SpringSecurity.service;

import devangelistesecurity.SpringSecurity.model.entity.UserEntity;

import java.util.List;

public interface IUserService {
    List<UserEntity> findAll();
}
