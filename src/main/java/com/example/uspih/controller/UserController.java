package com.example.uspih.controller;

// Редактируем роли пользователей

import com.example.uspih.domain.Role;
import com.example.uspih.domain.User;
import com.example.uspih.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user") // Указываем, с чем будем работать. Аннотации будут в каждом из методе
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')") // Проверяем, является ли пользователь админом
    @GetMapping
    public String userList(Model model) { // Пропихиваем список пользователей в виде модели
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')") // Проверяем, является ли пользователь админом
    // Добавляем Mapping для редактора пользователей (/user/{<идентификатор>})
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) { // Получаем пользователя из БД
        // Модель получаем, чтобы потом её передать пользователю
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    // Добавляем метод сохранения ролей
    @PreAuthorize("hasAuthority('ADMIN')") // Проверяем, является ли пользователь админом
    @PostMapping
    public String userSave(
            @RequestParam String username, // Получаем имя пользователя
            // Получаем список полей которые передаются в этой форме, их количество динамическо изменяемо
            @RequestParam Map<String, String> form,
            // Чтобы сохранить данные, нужно взять некоторые параметы с сервера. По параметру userId получаем пользователя из БД
            @RequestParam("userId") User user
    ) {

        userService.saveUser(user, username, form);

        return "redirect:/user";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email
    ) {
        userService.updateProfile(user, password, email);

        return "redirect:/user/profile";
    }

}
