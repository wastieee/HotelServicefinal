package com.service.hotel.Service;
import com.service.hotel.DTO.Converter.User2DTOConverter;
import com.service.hotel.DTO.UserDTO;
import com.service.hotel.DTO.UserRequestDTO;
import com.service.hotel.Entity.User;
import com.service.hotel.Exceptions.NotFoundException;
import com.service.hotel.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final User2DTOConverter user2DTOConverter;

    @Transactional(readOnly = true)
    public UserDTO getOne(final Long id) {
        return userRepository.findById(id).map(user2DTOConverter::convert).orElseThrow(() -> new NotFoundException("Пользователь с таким айди " + id + " не найден!"));
    }
    @Transactional(readOnly = true)
    public void delete(final long id) {
        userRepository.deleteById(id);
    }
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(user2DTOConverter::convert).toList();
    }
    @Transactional
    public UserDTO create(final UserRequestDTO request) throws BadRequestException {
        if (userRepository.existsByLogin(request.getLogin())) {
            throw new BadRequestException("User already exists");
        }
        User user = fillOrUpdateUserEntity(request, new User());
        return user2DTOConverter.convert(userRepository.save(user));
    }
    @Transactional
    public UserDTO update(final Long id, final UserRequestDTO request) {
        User existingUser = fillOrUpdateUserEntity(request, userRepository.getReferenceById(id));

        return user2DTOConverter.convert(userRepository.save(existingUser));
    }
    private static User fillOrUpdateUserEntity(UserRequestDTO request, User user){
        user.setAge(request.getAge());
        user.setLogin(request.getLogin());
        user.setRole(request.getRole());
        user.setLogin(request.getLogin());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());

        return user;
    }


}
