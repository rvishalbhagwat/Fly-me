package com.vishal.flyme.util;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Autowired
	JavaMailSender mailSender;

	public void sendEmail(String toAddress, String filePath) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(toAddress);
			helper.setSubject("Your Flight Ticket");
			helper.setText(
					"This is your booked ticked ,Please reach the airport at least 1 hour before scheduled time to avoid any last minute hurry");
			helper.addAttachment("ticket", new File(filePath));
			helper.addBcc("vishalbhagwat020@gmail.com", filePath);
			mailSender.send(message);
		} catch (MessagingException e) {

			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
