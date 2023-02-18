package com.gachon.board.oauth;

import com.gachon.board.entity.UserEntity;
import com.gachon.board.entity.role.Role;
import com.gachon.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    //DefaultOAuth2UserService를 상속받은 PrincipalOauth2UserService: 구글로그인을 한 후 loadUser가 구글로부터 받은 OAuth2UserRequest를 통해 후처리


//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private UserRepository userRepository;
    @Override

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        BCryptPasswordEncoder bCryptPasswordEncoder1 = new BCryptPasswordEncoder();
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("sub");
        String username = provider+"_"+providerId;
        String uuid = UUID.randomUUID().toString();
        String password = bCryptPasswordEncoder1.encode("패스워드"+uuid);
        String email = oAuth2User.getAttribute("email");
        Role role = Role.USER;
        //UserEntity byUsername = userRepository.findByEmail(email).get();
        if (userRepository.findByEmail(oAuth2User.getAttributes().get("email").toString()).isEmpty()) {
            userRepository.save(
                    UserEntity.builder()
                            .username(oAuth2User.getAttributes().get("name").toString())
                            .email(oAuth2User.getAttributes().get("email").toString())
                            .role(role).password(password).build()
            );
        }
        return super.loadUser(userRequest);


    }
}
