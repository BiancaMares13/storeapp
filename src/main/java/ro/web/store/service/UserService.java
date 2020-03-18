package ro.web.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.web.store.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
}
