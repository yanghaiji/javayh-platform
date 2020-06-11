package com.javayh.demo.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.javayh.common.result.ResultData;
import com.javayh.common.util.json.JsonUtils;
import com.javayh.common.util.log.Log;
import com.javayh.log.log.LogError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-06 9:48
 */
@Api("测试 API")
@RestController
@RequestMapping(value = "/demo/")
public class Demo2Controller {

	@Autowired(required = false)
	private LogError logError;

	@ApiOperation(value = "swagger测试", notes = "测试")
	@GetMapping(value = "swagger")
	public ResultData getSwagger() {
		try {
			Integer.valueOf("22m");
			return ResultData.success();
		}
		catch (NumberFormatException e) {
			logError.logPrint("swagger", e.getStackTrace());
			return ResultData.fail();
		}
	}

    @GetMapping(value = "json")
    public void testJson(){
        User user = new User("123", "123",13);
        System.out.println(JsonUtils.beanToJson(user));
        String jsonString = "[{\"name\":\"haiji\", \"age\":21},{\"name\":\"java\", \"age\":21}]";
        System.out.println(JsonUtils.jsonToBean(jsonString, new TypeReference<List<User>>() {}));

    }

}

class User implements Serializable {

    private String name;
    private String pwd;
    private Integer age;

    public User() {
    }

    public User(String name, String pwd, Integer age) {
        this.name = name;
        this.pwd = pwd;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", age=" + age +
                '}';
    }
}
