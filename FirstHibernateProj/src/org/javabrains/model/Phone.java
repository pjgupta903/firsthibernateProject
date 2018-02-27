package org.javabrains.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="phone_details")
public class Phone {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="phone_id")
	private Long phoneId;
	
	@Column(name="phone_name")
	private String phoneName;
	
	@ManyToMany(mappedBy="phones")
	private List<UserDetails> userDetailList;

	public List<UserDetails> getUserDetailList() {
		return userDetailList;
	}

	public void setUserDetailList(List<UserDetails> userDetailList) {
		this.userDetailList = userDetailList;
	}

	public Long getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(Long phoneId) {
		this.phoneId = phoneId;
	}

	public String getPhoneName() {
		return phoneName;
	}

	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}

	@Override
	public String toString() {
		return "Phone [phoneId=" + phoneId + ", phoneName=" + phoneName + "]";
	}
	
	
}
