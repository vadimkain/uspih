package com.example.uspih.controller;

import com.example.uspih.domain.Message;
import com.example.uspih.domain.User;
import com.example.uspih.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    // Указываем спрингу, что хотим получить переменную. Сопоставляем пусть из appl.properties и подставляем переменную
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main") // RequestParam(required = false, defaultValue = "") - настройки GET запроса
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages = messageRepo.findAll();

        // Фильтр сообщений
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            // Получаем список аргументов и сообщений ошибок валидаций (Всегда должен идти перед Model model)
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        message.setAuthor(user);

        // Если есть ошибки валидации
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = com.example.uspih.controller.ControllerUtils.getErrors(bindingResult);

            // Отображаем список ошибок валидаций
            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);

        } else {
            // Проверяем, отправили ли файл
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                // Проверяем, существует ли директория для сохранения файлов
                File uploadDir = new File(uploadPath);
                if (uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                // Обезопасим себя от коллизий. Создаём уникальное имя файла
                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename)); // Загружаем файл

                message.setFilename(resultFilename);
            }
            // В случае если валидация прошла успеша - удаляем из модели наш меседж
            // иначе после добавления получим открытую форму, в которой будет выведено сообщение,
            // которое только что попытались добавить
            model.addAttribute("message", null);

            messageRepo.save(message);
        }

        Iterable<Message> messages = messageRepo.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }

}

