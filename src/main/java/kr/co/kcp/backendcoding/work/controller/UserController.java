package kr.co.kcp.backendcoding.work.controller;

import kr.co.kcp.backendcoding.work.domain.entity.User;
import kr.co.kcp.backendcoding.work.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    final private UserService userService;

    @GetMapping(value = "/user/find/all")
    public List<User> getFindAll() {
        return userService.findAll();
    }

}
