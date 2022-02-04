package com.outflearn.Outflearn.service;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class KakaoPayApprovalVO {

	private String aid, tid, cid, sid;
	private String partner_order_id, partner_user_id, payment_method_type;
	private AmountVO amout;
	private CardVO card_info;
	private String item_name, item_code, payload;
	private Integer quantity, tax_free_amout, vat_amout;
	private Date created_at, approved_at;
	
	public KakaoPayApprovalVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KakaoPayApprovalVO(String aid, String tid, String cid, String sid, String partner_order_id,
			String partner_user_id, String payment_method_type, AmountVO amout, CardVO card_info, String item_name,
			String item_code, String payload, Integer quantity, Integer tax_free_amout, Integer vat_amout,
			Date created_at, Date approved_at) {
		super();
		this.aid = aid;
		this.tid = tid;
		this.cid = cid;
		this.sid = sid;
		this.partner_order_id = partner_order_id;
		this.partner_user_id = partner_user_id;
		this.payment_method_type = payment_method_type;
		this.amout = amout;
		this.card_info = card_info;
		this.item_name = item_name;
		this.item_code = item_code;
		this.payload = payload;
		this.quantity = quantity;
		this.tax_free_amout = tax_free_amout;
		this.vat_amout = vat_amout;
		this.created_at = created_at;
		this.approved_at = approved_at;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getPartner_order_id() {
		return partner_order_id;
	}
	public void setPartner_order_id(String partner_order_id) {
		this.partner_order_id = partner_order_id;
	}
	public String getPartner_user_id() {
		return partner_user_id;
	}
	public void setPartner_user_id(String partner_user_id) {
		this.partner_user_id = partner_user_id;
	}
	public String getPayment_method_type() {
		return payment_method_type;
	}
	public void setPayment_method_type(String payment_method_type) {
		this.payment_method_type = payment_method_type;
	}
	public AmountVO getAmout() {
		return amout;
	}
	public void setAmout(AmountVO amout) {
		this.amout = amout;
	}
	public CardVO getCard_info() {
		return card_info;
	}
	public void setCard_info(CardVO card_info) {
		this.card_info = card_info;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getTax_free_amout() {
		return tax_free_amout;
	}
	public void setTax_free_amout(Integer tax_free_amout) {
		this.tax_free_amout = tax_free_amout;
	}
	public Integer getVat_amout() {
		return vat_amout;
	}
	public void setVat_amout(Integer vat_amout) {
		this.vat_amout = vat_amout;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getApproved_at() {
		return approved_at;
	}
	public void setApproved_at(Date approved_at) {
		this.approved_at = approved_at;
	}
	
}

