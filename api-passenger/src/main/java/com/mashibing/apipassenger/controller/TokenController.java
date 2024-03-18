package com.mashibing.apipassenger.controller;

import com.mashibing.apipassenger.service.TokenService;
import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.response.TokenResponse;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
    @Autowired
    private TokenService tokenService;
@PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){

    String refreshTokenSrc= tokenResponse.getRefreshToken();
    return tokenService.refreshToken(refreshTokenSrc);
}

}
