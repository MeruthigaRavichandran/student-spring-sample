package com.itech.itechdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ItechController {
    @RequestMapping(value = "/test-json" )
    public Map testJson() {
        Map<String,Object> map = new HashMap<>();
        map.put("name","Meru");
        map.put("course","btech");
        map.put("branch","cse");
        return map;
    }
}

