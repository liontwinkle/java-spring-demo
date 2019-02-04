package com.codesample.demo.controller;

import com.codesample.demo.request.LoginRequest;
import com.codesample.demo.request.UserPrincipal;
import com.codesample.demo.service.SecurityService;
import com.codesample.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

import static com.codesample.demo.constant.ApplicationConstants.AUTH_HEADER;
import static com.codesample.demo.constant.ApplicationConstants.AUTH_HEADER_PREFIX;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<Void> login(@RequestBody @Valid LoginRequest loginRequestDto) {
        String token = securityService.generateUserToken(
                userService.getUserByLoginAndPass(loginRequestDto.getEmail(), loginRequestDto.getPassword()));

        return ResponseEntity.ok().header(AUTH_HEADER, AUTH_HEADER_PREFIX + "" +token).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/me")
    public ResponseEntity<UserPrincipal> me(@AuthenticationPrincipal UserPrincipal user) {
        return ResponseEntity.ok(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public ResponseEntity<Void> logout(@RequestHeader(AUTH_HEADER) String token) {
        if(Objects.nonNull(token) && !token.isEmpty()) {
            securityService.invalidateToken(token.replace(AUTH_HEADER_PREFIX,""));
        }
        return ResponseEntity.ok().build();
    }
}
