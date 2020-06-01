package services;

import domain.models.Group;
import domain.models.User;
import exceptions.EntityNotFoundException;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private Group group;

    public UserServiceImpl(Group group){
        this.group = group;
    }

    @Override
    public User getById(String userId) {
        Optional<User> userOptional = group.getUsers().stream().
                filter(u -> u.getUserId().equalsIgnoreCase(userId)).findFirst();
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        throw new EntityNotFoundException("no user found with id: " + userId);
    }
}
