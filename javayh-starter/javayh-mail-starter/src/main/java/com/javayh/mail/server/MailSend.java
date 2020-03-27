package com.javayh.mail.server;

import com.javayh.mail.conf.EmailProperties;
import com.javayh.mail.entity.MailDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-08 14:45
 */
@Slf4j
@EnableConfigurationProperties(value = EmailProperties.class)
public class MailSend {

    @Autowired(required = false)
    private EmailProperties emailProperties;

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Autowired(required = false)
    private TemplateEngine templateEngine;

    /**
     * <p>
     *       发送带有模板的邮件
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/8
     * @param mailDO
     * @return void
     */
    public void sendTemplateMail(MailDO mailDO) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
            // 发送人的邮箱
            messageHelper.setFrom(emailProperties.getUsername());
            //发给谁  对方邮箱
            messageHelper.setTo(mailDO.getEmail());
            //标题
            messageHelper.setSubject(mailDO.getTitle());
            //使用模板thymeleaf
            Context context = new Context(LocaleContextHolder.getLocale());
            //定义模板数据
            context.setVariables(mailDO.getAttachment());
            //获取thymeleaf的html模板 指定模板路径
            String emailContent = templateEngine.process(mailDO.getTemplateName(),context);
            messageHelper.setText(emailContent,true);
            //发送邮件
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("模板邮件发送失败->message:{}",e.getMessage());
            throw new RuntimeException("邮件发送失败");
        }
    }

    /**
     * <p>
     *       发送简单纯文本邮件
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/8
     * @param mail
     * @return void
     */
    public void sendTextMail(MailDO mail){
        //建立邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        // 发送人的邮箱
        message.setFrom(emailProperties.getUsername());
        //标题
        message.setSubject(mail.getTitle());
        //发给谁  对方邮箱
        message.setTo(mail.getEmail());
        //内容
        message.setText(mail.getContent());
        try {//发送
            mailSender.send(message);
        } catch (MailException e) {
            log.error("纯文本邮件发送失败->message:{}",e.getMessage());
            throw new RuntimeException("邮件发送失败");
        }
    }

}
