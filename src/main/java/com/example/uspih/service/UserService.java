package com.example.uspih.service;

import com.example.uspih.domain.Role;
import com.example.uspih.domain.User;
import com.example.uspih.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

// Описываем логику
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        return user;
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString()); // Устанавливаем код активации
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Шифрование пароля при регистрации

        userRepo.save(user);

        sendMessage(user);

        return true;
    }

    private void sendMessage(User user) {
        // Если у пользователя есть почта
        if (!user.getEmail().isEmpty()) {
            String message = String.format(
                    "Доброго дня, перейдіть за посиланням, щоб активувати обліковий запис: " +
                            "http://192.168.0.109:8080/registration/activate/%s",
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Код активации", message);
        }
    }

    // Описываем логику активации пользователя
    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        // Если пользователь не был найден
        if (user == null) {
            return false;
        }

        user.setActivationCode(null); // Если пользователь подтвердил свой почтовый ящик

        // Устанавливаем роль, что пользователь активирован
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        user.getRoles().add(Role.USER);
        user.getRoles().add(Role.ACTIVATE);

        userRepo.save(user);

        return true;
    }
}