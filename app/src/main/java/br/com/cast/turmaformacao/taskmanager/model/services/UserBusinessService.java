package br.com.cast.turmaformacao.taskmanager.model.services;


import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.User;
import br.com.cast.turmaformacao.taskmanager.model.persistence.UserRepository;

public final class UserBusinessService {
    private UserBusinessService() {
        super();
    }

    public static List<User> findAll(){
        return UserRepository.getAll();
    }

    public static void save(User user){
        UserRepository.save(user);
    }

    public static void delete(User selectedUser){
        UserRepository.delete(selectedUser.getId());
    }
}
