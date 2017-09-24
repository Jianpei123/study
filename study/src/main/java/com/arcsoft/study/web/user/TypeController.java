package com.arcsoft.study.web.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcsoft.study.service.ITypeService;

@RequestMapping("user")
@RestController("userTypeController")
public class TypeController {

	@Resource
    private ITypeService typeSercie;
	
	@RequestMapping("/getCascadedType")
    public List<Map<String, Object>> getCascadedType() {
        return typeSercie.getCascadedType();
    }
}
