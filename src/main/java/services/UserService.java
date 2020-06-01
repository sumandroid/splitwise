package services;

import domain.models.User;

public interface UserService {

    User getById(String userId);
}
