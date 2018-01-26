package com.example.model;

import org.codehaus.jackson.annotate.JsonProperty;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "GetListUserRole")
public class GetListUserRoleModel extends Model {

	// @Column(name = "UserName", unique = true, onUniqueConflict =
	// Column.ConflictAction.REPLACE)
	@Column(name = "UserName")
	@JsonProperty("UserName")
	private String UserName;

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserDomain() {
		return UserDomain;
	}

	public void setUserDomain(String userDomain) {
		UserDomain = userDomain;
	}

	public int getRoleID() {
		return RoleID;
	}

	public void setRoleID(int roleID) {
		RoleID = roleID;
	}

	public String getRoleCode() {
		return RoleCode;
	}

	public void setRoleCode(String roleCode) {
		RoleCode = roleCode;
	}

	public String getRoleName() {
		return RoleName;
	}

	public void setRoleName(String roleName) {
		RoleName = roleName;
	}

	@Column(name = "UserDomain")
	@JsonProperty("UserDomain")
	private String UserDomain;

	@Column(name = "RoleID")
	@JsonProperty("RoleID")
	private int RoleID;

	@Column(name = "RoleCode")
	@JsonProperty("RoleCode")
	private String RoleCode;

	@Column(name = "RoleName")
	@JsonProperty("RoleName")
	private String RoleName;

}
