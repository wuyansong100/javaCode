package com.mashibing.serviceverificationcode.controller;

import com.mashibing.internalcommon.dto.ResponseResult;
import com.mashibing.internalcommon.response.NumberCodeResponse;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {
    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") Integer size){

        System.out.println("size:"+size);

        double mathRandom = (Math.random()*9+1)*Math.pow(10,size-1);
        System.out.println(mathRandom);
        Integer resultInt = (int) mathRandom;
        System.out.println("generator src code :"+resultInt);

//        JSONObject result = new JSONObject();
//        result.put("code",1);
//        result.put("message","success");
//
//        JSONObject data = new JSONObject();
//        data.put("numberCode",resultInt);
//        result.put("data",data);
//        return result.toString();

        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(resultInt);

        return ResponseResult.<NumberCodeResponse>success(response);

    }
}
