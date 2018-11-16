package com.boot.SpringBoot.service.itf;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "springclient")
public interface UserFegin
{
	@RequestMapping(method = RequestMethod.GET, value = "/user/findByUserId/{userId}")
	public Object findByUserId(@PathVariable("userId") String userId);
}
