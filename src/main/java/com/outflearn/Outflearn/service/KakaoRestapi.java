package com.outflearn.Outflearn.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.outflearn.Outflearn.LoginController;
import com.outflearn.Outflearn.dto.ClassInfoDto;
@Service
public class KakaoRestapi {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private static final String HOST = "https://kapi.kakao.com";
	private static final String READY_URI = "/v1/payment/ready";
	private static final String APPROVE_URI	= "/v1/payment/approve";
	
	private KakaoPayReadyVo kakaoPayReadyVo;
	private KakaoPayApprovalVO kakaoPayApprovalVO;
	
		public String kakaoPayReady(String class_title, int class_price) {
			
		RestTemplate restTemplate = new RestTemplate();
		
		//서버로 요청할 header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK b15d82b32192d3cef54f5135b6013832");
		headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		System.out.println(headers);
		
		//서버로 요청할 body
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("partner_order_id", "partner_order_id");
		params.add("partner_user_id", "partner_user_id");
		params.add("item_name", class_title);
		params.add("quantity", "1");
		params.add("total_amount", String.valueOf(class_price));
		params.add("tax_free_amount", "100");
		params.add("approval_url", "http://localhost:8787/Outflearn/kakaoPaySuccess");
		params.add("cancel_url", "http://localhost:8787/Outflearn/kakaoPayCancel");
		params.add("fail_url", "http://localhost:8787/kakaoPaySuccessFail");
		
		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
	
		try {
			kakaoPayReadyVo = restTemplate.postForObject(new URI(HOST + READY_URI), body, KakaoPayReadyVo.class);
			
			logger.info(""+ kakaoPayReadyVo);
			return kakaoPayReadyVo.getNext_redirect_pc_url();
			
			
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return "kakaoPaySuccess";
		
	}
		
	public KakaoPayApprovalVO kakaoPayInfo(String pg_token) {
		
		logger.info("KakaoPayInfoVO");
		
		RestTemplate restTemplate = new RestTemplate();
		
		//header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK b15d82b32192d3cef54f5135b6013832");
		headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//body
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("tid", kakaoPayReadyVo.getTid());
		params.add("partner_order_id", "partner_order_id");
		params.add("partmer_user_id", "partner_user_id");
		params.add("pg_token", pg_token);
		params.add("total_amount", "2000");
		
		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		try {
			kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + APPROVE_URI), body, KakaoPayApprovalVO.class);
						
			logger.info(""+kakaoPayApprovalVO);
			
			return kakaoPayApprovalVO;
			
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
