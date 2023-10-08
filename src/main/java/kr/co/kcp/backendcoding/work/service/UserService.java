package kr.co.kcp.backendcoding.work.service;

import kr.co.kcp.backendcoding.work.domain.entity.User;
import kr.co.kcp.backendcoding.work.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
