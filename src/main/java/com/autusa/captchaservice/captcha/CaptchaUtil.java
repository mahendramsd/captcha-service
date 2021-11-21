package com.autusa.captchaservice.captcha;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import cn.apiclub.captcha.text.renderer.DefaultWordRenderer;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

@Component
public  class CaptchaUtil {

	//Creating Captcha Object
	public Captcha createCaptcha(Integer width, Integer height) {

		return new Captcha.Builder(width, height)
				.addBackground(new GradiatedBackgroundProducer())
				.addText(new DefaultTextProducer(), new DefaultWordRenderer())
				.addNoise(new CurvedLineNoiseProducer())
				.build();
	}

	//Converting to binary String
	public String encodeCaptcha(Captcha captcha) {
		String image = null;
		try {
			ByteArrayOutputStream bos= new ByteArrayOutputStream();
			ImageIO.write(captcha.getImage(),"jpg", bos);
			byte[] byteArray= Base64.getEncoder().encode(bos.toByteArray());
			image = new String(byteArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}

//	private void getCaptcha(User user) {
//		Captcha captcha = CaptchaUtil.createCaptcha(240, 70);
//		user.setHiddenCaptcha(captcha.getAnswer());
//		user.setCaptcha(""); // value entered by the User
//		user.setRealCaptcha(CaptchaUtil.encodeCaptcha(captcha));
//
//	}
}
