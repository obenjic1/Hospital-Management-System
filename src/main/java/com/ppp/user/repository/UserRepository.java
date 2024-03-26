package com.ppp.user.repository;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Page<User> findAll(Pageable pageable);
	User findByUsernameOrEmail(String email, String username);
	User findByEmail(String email);
	User findByUsername(String username);
	void deleteByUsername(String username);
	User findByResetPasswordToken(String token);
}
