package vn.edu.stu.thucannhanh.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "token")
@Getter
@Setter
public class Token extends BaseEntity{
    @Column(length = 1000)
    private String token;

    private Date tokenExpDate;

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenExpDate() {
		return tokenExpDate;
	}

	public void setTokenExpDate(Date tokenExpDate) {
		this.tokenExpDate = tokenExpDate;
	}
}
