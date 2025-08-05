package com.virtuallibrary.VirtualLibrary.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.virtuallibrary.VirtualLibrary.dto.UserDTO;
import com.virtuallibrary.VirtualLibrary.exceptions.User.EmailAlreadyUsedException;
import com.virtuallibrary.VirtualLibrary.exceptions.User.MissingFieldsException;
import com.virtuallibrary.VirtualLibrary.exceptions.User.UserIdNotFoundException;
import com.virtuallibrary.VirtualLibrary.model.User;
import com.virtuallibrary.VirtualLibrary.repository.UserRepository;

import io.micrometer.common.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserIdNotFoundException(id));
    }

    public User save(UserDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyUsedException(dto.getEmail());
        }

        User user = User.builder()
            .email(dto.getEmail().trim())
            .password(passwordEncoder.encode(dto.getPassword()))
            .build();

        return userRepository.save(user);
    }

    public User update(Long id, UserDTO dto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserIdNotFoundException(id));

        if (dto.getEmail().isEmpty() || dto.getPassword().isEmpty()) {
            throw new MissingFieldsException();
        }

        existingUser.setEmail(dto.getEmail().trim());
        existingUser.setPassword(passwordEncoder.encode(dto.getPassword().trim()));
        
        return userRepository.save(existingUser);
    }

    public User updatePartial(Long id, UserDTO dto) {
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new UserIdNotFoundException(id));
    
        Optional.ofNullable(dto.getEmail())
            .map(String::trim)
            .filter(StringUtils::isNotBlank)
            .ifPresent(existingUser::setEmail);

        Optional.ofNullable(dto.getPassword())
            .map(String::trim)
            .filter(StringUtils::isNotBlank)
            .map(passwordEncoder::encode)
            .ifPresent(existingUser::setPassword);

        return userRepository.save(existingUser);
    }

    public void deleteById(Long id) {
        if (!userRepository.existsById(id))
            throw new UserIdNotFoundException(id);

        userRepository.deleteById(id);
    }
}
