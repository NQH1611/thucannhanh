package vn.edu.stu.thucannhanh.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "don_dat_hang")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "tong_tien")
    private BigDecimal total;
    @Column(name = "trang_thai")
    private int status;
    @Column(name = "ngay_dat_hang")
    private Date createDate;
    @Column(name = "khach_hang")
    private int customer;
    @Column(name = "quan_ly")
    private int admin;
    
    public Order(Integer id, BigDecimal total, int status, Date createDate, int customer, int admin) {
        this.id = id;
        this.total = total;
        this.status = status;
        this.createDate = createDate;
        this.customer = customer;
        this.admin = admin;
    }

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
    
    

}
