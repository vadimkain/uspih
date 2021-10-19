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

    // Метод по добавлению пользователя
    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername()); // Поиск пользователя

        // В случае, если нашли похожего пользователя в БД, уведомляем регистранта об этом
        if (userFromDb != null) {
            // Если пользователь найден в БД, то возвращаем false, что означает что пользователь не добавлен
            return false;
        }

        // Сохранение пользователя
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
                    "Привет, %s! \n" +
                            "Добро пожаловать в Sweater! Для активации перейди по ссылке: http://192.168.0.109:8080/activate/%s",
                    user.getUsername(), user.getActivationCode()
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

        userRepo.save(user);

        return true;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username); // Возьмем пользователя и установим ему имя

        // Получаем список ролей, переводим из Enum в String
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear(); // Очищаем список ролей для пользователя

        // Теперь проверяем, что форма содержит роли для нашего пользователя
        for (String key : form.keySet()) {
            if (roles.contains(key)) { // Проверяем, что роли содержат данный ключ
                // В этом случае нашему пользователю в список ролей добавляем такую роль, которую получаем через valueOf
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user); // Сохраняем пользователя
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();

        // Проверяем, что почта изменилась
        boolean isEmailChanged = (email != null && !email.equals(userEmail)) || (userEmail != null && !userEmail.equals(email));

        // Если почта обновилась, то обновляем его у пользователя
        if (isEmailChanged) {
            user.setEmail(email);

            if (!email.isEmpty()) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        // Проверяем, что пользователь установил новый пароль
        if (!password.isEmpty()) {
            user.setPassword(password);
        }

        userRepo.save(user);

        // Отправляем код тогда, когда почта была изменена
        if (isEmailChanged) {
            sendMessage(user);
        }
    }
}