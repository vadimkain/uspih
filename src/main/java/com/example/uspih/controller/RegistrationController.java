package com.example.uspih.controller;

import com.example.uspih.domain.Role;
import com.example.uspih.domain.User;
import com.example.uspih.domain.dto.CaptchaResponseDto;
import com.example.uspih.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
            model.addAttribute("captchaError", "Капча введена невірно або вона порожня");
        }

        boolean isConfirmEmpty = passwordConfirm.isEmpty();

        if (isConfirmEmpty) {
            model.addAttribute("password2Error", "Поле з підтвердженням пароля не може бути порожнім");
        }

        // Проверяем, сходится ли два пароля, которые пользователь ввёл для проверки
        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Паролі не співпадають");
        }

        // Проверяем ошибки валидации
        if (isConfirmEmpty || bindingResult.hasErrors() || !response.isSuccess()) {
            // Получаем ошибки валидации
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "Такий користувач вже існує");
            return "registration";
        }

        model.addAttribute("message", "Код активації відправлено на пошту, перейдіть по ньому");
        model.addAttribute("messageType", "success");

        return "login";
    }

    // Подтверждение аккаунта пользователем
    @GetMapping("/registration/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivate = userService.activateUser(code);

        if (isActivate) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "Ви успішно активували обліковий запис");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Код активації не найден");
        }

        return "login";
    }
}