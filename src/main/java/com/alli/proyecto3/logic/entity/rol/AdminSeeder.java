package com.alli.proyecto3.logic.entity.rol;

import com.alli.proyecto3.logic.entity.user.User;
import com.alli.proyecto3.logic.entity.user.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Order(2)
@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(RoleRepository roleRepository,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.crearSuperAdmin();
        this.crearUsuarioRegular();
    }

    private void crearSuperAdmin() {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.SUPER_ADMIN_ROLE);
        Optional<User> optionalUser = userRepository.findByEmail("superadmin@proyecto3.com");

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        User superAdmin = new User();
        superAdmin.setName("Super");
        superAdmin.setLastname("Admin");
        superAdmin.setEmail("superadmin@proyecto3.com");
        superAdmin.setPassword(passwordEncoder.encode("superadmin123"));
        superAdmin.setRole(optionalRole.get());

        userRepository.save(superAdmin);
    }

    private void crearUsuarioRegular() {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
        Optional<User> optionalUser = userRepository.findByEmail("user@proyecto3.com");

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        User user = new User();
        user.setName("Usuario");
        user.setLastname("Regular");
        user.setEmail("user@proyecto3.com");
        user.setPassword(passwordEncoder.encode("user123"));
        user.setRole(optionalRole.get());

        userRepository.save(user);
    }
}