package com.example.model;

import org.codehaus.jackson.annotate.JsonProperty;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "GetBookOnline")
public class GetBookOnlineModel extends Model {

	@Column(name = "BookOnlineID", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
	@JsonProperty("BookOnlineID")
	private String BookOnlineID;

	public String getBookOnlineID() {
		return BookOnlineID;
	}

	public void setBookOnlineID(String bookOnlineID) {
		BookOnlineID = bookOnlineID;
	}

	public String getBookDate() {
		return BookDate;
	}

	public void setBookDate(String bookDate) {
		BookDate = bookDate;
	}

	public String getUVerificationCode() {
		return VerificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		VerificationCode = verificationCode;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getHPNo() {
		return HPNo;
	}

	public void setHPNo(String hPNo) {
		HPNo = hPNo;
	}

	public String getCodeUsed() {
		return CodeUsed;
	}

	public void setCodeUsed(String codeUsed) {
		CodeUsed = codeUsed;
	}

	@Column(name = "BookDate")
	@JsonProperty("BookDate")
	private String BookDate;

	@Column(name = "VerificationCode")
	@JsonProperty("VerificationCode")
	private String VerificationCode;

	@Column(name = "CustomerNamev")
	@JsonProperty("CustomerName")
	private String CustomerName;

	@Column(name = "HPNo")
	@JsonProperty("HPNo")
	private String HPNo;

	@Column(name = "CodeUsed")
	@JsonProperty("CodeUsed")
	private String CodeUsed;

}
