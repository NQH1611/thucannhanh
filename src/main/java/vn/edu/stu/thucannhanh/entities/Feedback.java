package vn.edu.stu.thucannhanh.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phan_hoi")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ngay_danh_gia")
    private Date createDate;
    @Column(name = "ma_khach_hang")
    private int customer;
    @Column(name = "ma_thuc_an")
    private int food;
    @Column(name = "noi_dung_phan_hoi")
    private String description;
    @Column(name = "diem_danh_gia")
    private int rating;
    public Feedback() {
    }
    public Feedback(Integer id, Date createDate, int customer, int food, String description, int rating) {
        this.id = id;
        this.createDate = createDate;
        this.customer = customer;
        this.food = food;
        this.description = description;
        this.rating = rating;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public int getFood() {
        return food;
    }
    public void setFood(int food) {
        this.food = food;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    
}
