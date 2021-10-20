package com.example.uspih.controller;

import com.example.uspih.domain.User;
import com.example.uspih.domain.dto.CaptchaResponseDto;
import com.example.uspih.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Autowired
    private UserService userService;

    @Value("${recaptcha.secret}")
    private String secret;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2") String passwordConfirm,
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @Valid User user,
            BindingResult bindingResult,
            Model model) {

        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        // Если проверка капчи неуспешна
        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Каптча введена неверно или она пустая");
        }

        boolean isConfirmEmpty = passwordConfirm.isEmpty();

        if (isConfirmEmpty) {
            model.addAttribute("password2Error", "Поле с подтверждением пароля не может быть пустым");
        }

        // Проверяем, сходится ли два пароля, которые пользователь ввёл для проверки
        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Пароли не совпадают");
        }

        // Проверяем ошибки валидации
        if (isConfirmEmpty || bindingResult.hasErrors() || !response.isSuccess()) {
            // Получаем ошибки валидации
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "Такой пользователь уже существует!");
            return "registration";
        }
        return "redirect:/login";
    }

    // Подтверждение аккаунта пользователем
    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivate = userService.activateUser(code);

        if (isActivate) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "Вы успешно активировали учетную запись");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Код активации не найден");
        }

        return "login";
    }
}