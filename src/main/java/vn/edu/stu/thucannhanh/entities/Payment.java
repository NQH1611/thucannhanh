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
@Table(name = "thanh_toan")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "so_tien")
    private BigDecimal total;
    @Column(name = "ngay_thanh_toan")
    private Date createDate;
    @Column(name = "ma_don_hang")
    private int order;
    @Column(name = "loai_thanh_toan")
    private String paymentType;
    public Payment() {
    }
    public Payment(Integer id, BigDecimal total, Date createDate, int order, String paymentType) {
        this.id = id;
        this.total = total;
        this.createDate = createDate;
        this.order = order;
        this.paymentType = paymentType;
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
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }
    public String getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    } 
    
}
