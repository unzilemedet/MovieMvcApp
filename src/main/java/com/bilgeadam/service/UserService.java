package com.bilgeadam.service;

import com.bilgeadam.dto.request.UserRegisterRequestDto;
import com.bilgeadam.dto.request.UserUpdateRequestDto;
import com.bilgeadam.dto.response.UserLoginResponseDto;
import com.bilgeadam.entity.User;

import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.exception.custom.UserEmailExistsException;
import com.bilgeadam.exception.custom.UserNameNotFoundException;
import com.bilgeadam.exception.custom.UserWrongPasswordException;
import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.repository.IUserRepository;
import com.bilgeadam.utility.ICrudService;
import com.bilgeadam.utility.enums.ECustomEnum;
import com.bilgeadam.utility.enums.EStatus;
import com.bilgeadam.utility.enums.EUserType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService implements ICrudService<User, Integer> {
    private final IUserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Iterable<User> saveAll(Iterable<User> t) {
        return userRepository.saveAll(t);
    }

    @Override
    public User update(User user) {
        return null;
    }

    //update-dto --> bu şekilde kullanılmamalıdır
    public User updateDto(UserUpdateRequestDto dto){
        Optional<User> optionalUser = userRepository.findById(dto.getId());
        if (optionalUser.isPresent()){
            optionalUser.get().setName(dto.getName());
            optionalUser.get().setSurname(dto.getSurname());
            optionalUser.get().setEmail(dto.getEmail());
            optionalUser.get().setPhone(dto.getPhone());
            return userRepository.save(optionalUser.get());
        }else {
            throw new NotFoundException("Kullanıcı bulunamadı");
        }
    }

    public User updateMapper(UserUpdateRequestDto dto){
        Optional<User> user = userRepository.findById(dto.getId());
        IUserMapper.INSTANCE.updateUserFromDto(dto, user.get());
        return userRepository.save(user.get());
    }

    //Sadece admin rolüne sahip kişiler bu işlemi gerçekleştirebilir.
    @Override
    public User delete(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            optionalUser.get().setStatus(EStatus.INACTIVE);
            userRepository.save(optionalUser.get());
            return optionalUser.get();
        }else {
            throw new NullPointerException("Böyle bir kullanıcı yok.");
        }
    }

    @Override
    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()){
            throw new NullPointerException("Liste boş");
        }
        return userList;
    }

    @Override
    public Optional<User> findById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            return optionalUser;
        }else {
            throw new NullPointerException("BÖyle bir kullanıcı yok");
        }
    }

    //basic builder register
    public User register(String name, String surname, String email, String password, String repassword){
        User user = User.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .repassword(repassword)
                .build();
        if (!password.equals(repassword) || password.isBlank() || repassword.isBlank()){
            throw new RuntimeException("Şifreler aynı değildir.");
        }else {
            return userRepository.save(user);
        }
    }

    //dto-register
    public UserRegisterRequestDto registerDto(UserRegisterRequestDto dto){
        User user = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .repassword(dto.getRepassword())
                .build();
        if (!dto.getPassword().equals(dto.getRepassword())
                || dto.getPassword().isBlank() || dto.getRepassword().isBlank()){
            throw new RuntimeException("Şifreler aynı değildir.");
        }else {
            userRepository.save(user);
        }
        return dto;
    }
    
    //mapper-register
    //Aynı email ile ikinci defa kayıt işlemi yapılmamalıdır. Eğer kayıt olan kişi superadmin@mail.com ise
    //UserType=ADMIN ve Status=ACTIVE olmalıdır.

    //metot imzası nedir --> bir metodun dönüş tipini ve parametresini belirtir
    //sorulduğunda --> "UserRegisterRequestDto dönüş tipinde ve UserRegisterRequestDto tipinde dto parametresi alan bir metottur"
    //demelisiniz
    public UserRegisterRequestDto registerMapper(UserRegisterRequestDto dto) {
        User user = IUserMapper.INSTANCE.toUserRegisterDto(dto);
        if (userRepository.findByEmailEqualsIgnoreCase(dto.getEmail()).isPresent()){
            throw new UserEmailExistsException(EErrorType.REGISTER_ERROR_EMAIL_EXISTS);
        } else if (!dto.getPassword().equals(dto.getRepassword())
                || dto.getPassword().isBlank() || dto.getRepassword().isBlank()) {
            throw new UserWrongPasswordException(EErrorType.LOGIN_ERROR_WRONG_PASSWORD);
        } else if (dto.getEmail().equals("superadmin@mail.com")) {
            user.setUserType(EUserType.ADMIN);
            user.setStatus(EStatus.ACTIVE);
        }
        userRepository.save(user);
        return dto;
    }
    //basic login
    public String login(String email, String password){
        Optional<User> optionalUser = userRepository.findByEmailAndPassword(email, password);
        if (optionalUser.isEmpty()){
            throw new RuntimeException("Böyle bir kullanıcı bulunamadı");
        }
        return "Giriş başarılı";
    }

    //dto-login
    public String loginDto(UserLoginResponseDto dto){
        Optional<User> optionalUser = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (optionalUser.isEmpty()){
            throw new NotFoundException("Email veya şifre bilgisi hatalı");
        }else {
            return "Giriş başarılı \n" + dto.getEmail();
        }
    }

    //mapper-login
    public UserLoginResponseDto loginMapper(UserLoginResponseDto dto){
        Optional<User> optionalUser = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (optionalUser.isEmpty()) {
            throw new UserNameNotFoundException(EErrorType.LOGIN_USER_AND_PASSWORD_EXCEPTION);
        } else {
            return IUserMapper.INSTANCE.toUserLoginDto(optionalUser.get());
        }
    }

    //custom login --> Arda
    public ResponseEntity customLogin(UserLoginResponseDto dto){
        Map<Object, Object> hm = new HashMap<>();
        Optional<User> optionalUser = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (optionalUser.isEmpty()){
            hm.put(ECustomEnum.status, false);
            hm.put(ECustomEnum.message, "Email veya şifre hatalıdır.");
            return new ResponseEntity(hm, HttpStatus.UNAUTHORIZED);
        }else {
            hm.put(ECustomEnum.status, true);
            hm.put(ECustomEnum.result, dto.getEmail());
            hm.put(ECustomEnum.message, "Giriş başarılı");
            return new ResponseEntity(hm, HttpStatus.OK);
        }
    }

    //kullanıcıları ismine göre sırala
    public List<User> findByOrderByName(){
        return userRepository.findAllByOrderByName();
    }

    public List<User> findAllByNameContainsIgnoreCase(String value){
        List<User> users = userRepository.findAllByNameContainsIgnoreCase(value);
        if (users.isEmpty()){
            throw new NotFoundException("Liste boş");
        }
        return users;
    }

    public Boolean existsByNameIgnoreCase(String value){
        return userRepository.existsByNameIgnoreCase(value);
    }

    public List<User> findByEmailIgnoreCase(String email){
        List<User> users = userRepository.findByEmailIgnoreCase(email);
        if (users.isEmpty()){
            throw new NotFoundException("Liste boş");
        }
        return users;
    }

    public List<User> passwordLongerThan(int num){
        return userRepository.passwordLongerThan(num);
    }

    public List<User> passwordLongerThan2(int num){
        return userRepository.passwordLongerThan2(num);
    }

    public List<User> findByEmailEndsWithIgnoreCase(String email){
        return userRepository.findByEmailEndsWithIgnoreCase(email);
    }
}
