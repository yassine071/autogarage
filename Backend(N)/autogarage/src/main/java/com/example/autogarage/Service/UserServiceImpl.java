package com.example.autogarage.Service;


import com.example.autogarage.exceptions.ForbiddenException;
import com.example.autogarage.model.User;
import com.example.autogarage.Utils.RandomStringGenerator;
import com.example.autogarage.exceptions.RecordNotFoundException;
import com.example.autogarage.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.autogarage.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    private Authentication getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication;
        }
        else {
            return null;
        }
    }

    private boolean currentUserHasFullAccessToAllUsers() {
        Authentication currentUser = getCurrentUser();
        if (currentUser == null) return false;
        return currentUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
    private boolean currentUserHasAccessToUser(String username) {
        Authentication currentUser = getCurrentUser();
        if (currentUser == null) return false;
        if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return true;
        }
        else {
            return currentUser.getName().equalsIgnoreCase(username);
        }
    }

    @Override
    public Collection<User> getUsers() {
        if (currentUserHasFullAccessToAllUsers()) {
            return userRepository.findAll();
        } else {
            if (getCurrentUser() == null) {
                return new ArrayList<>();
            }
            return userRepository.findAllByUsername(getCurrentUser().getName());
        }
    }
    @Override
    public Optional<User> getUser(String username){
        return userRepository.findById(username);
    }

    @Override
    public boolean userExists (String username){
        return userRepository.existsById(username);
    }

    @Override
    public String createUser(User user){
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        user.setApikey(randomString);

        String password = user.getPassword();
        String encoded = passwordEncoder.encode(password);
        user.setPassword(encoded);

        User newUser = userRepository.save(user);
        return newUser.getUsername();
    }

    @Override
    public void deleteUser (String username){
        if (!currentUserHasAccessToUser(username)) {
            throw new ForbiddenException();
        }
        userRepository.deleteById(username);
    }

    @Override
    public void updateUser (String username, User newUser){
        if (!currentUserHasAccessToUser(username)) {
            throw new ForbiddenException();
        }
        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
        User user = userRepository.findById(username).get();
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Set<Authority> getAuthorities (String username){
        if (!currentUserHasAccessToUser(username)) {
            throw new ForbiddenException();
        }
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        return user.getAuthorities();
    }

    @Override
    public void addAuthority (String username, String authority){
        if (!currentUserHasAccessToUser(username)) {
            throw new ForbiddenException();
        }
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    @Override
    public void removeAuthority (String username, String authority){
        if (!currentUserHasAccessToUser(username)) {
            throw new ForbiddenException();
        }
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

}