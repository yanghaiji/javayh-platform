package com.javayh.mail.web;

import com.javayh.common.result.ResultData;
import com.javayh.mail.entity.MailDO;
import com.javayh.mail.server.MailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-0:14
 */
@RestController
@RequestMapping(value = "/mail/")
public class MailController {

    @Autowired(required = false)
    private MailSend mailSenderUtil;

    /**
     * <p>
     *
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/8
     * @param mailDO
     * @return com.javayh.common.result.ResultData
     */
    @PostMapping(value = "send")
    public ResultData send(@RequestBody MailDO mailDO){
        Map<String,Object> map = new HashMap<>();
        map.put("username","Yang haiji");
        mailDO.setAttachment(map);
        mailDO.setTemplateName("HelloMail");
        mailSenderUtil.sendTemplateMail(mailDO);
       return ResultData.success();
    }

}
