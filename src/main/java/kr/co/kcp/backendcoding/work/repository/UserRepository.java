package kr.co.kcp.backendcoding.work.repository;

import jakarta.persistence.EntityManager;
import kr.co.kcp.backendcoding.work.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public List<kr.co.kcp.backendcoding.work.domain.entity.User> findAll() {
        return em.createQuery("select s from User s ", User.class)
                .getResultList();
    }
}
