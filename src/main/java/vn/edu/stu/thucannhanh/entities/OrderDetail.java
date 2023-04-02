package vn.edu.stu.thucannhanh.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chi_tiet_don_dat_hang")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ma_thuc_an")
    private int food;
    @Column(name = "so_luong")
    private int quantity;
    @Column(name = "ma_don_dat_hang")
    private int order;
    public OrderDetail() {
    }
    public OrderDetail(Integer id, int food, int quantity, int order) {
        this.id = id;
        this.food = food;
        this.quantity = quantity;
        this.order = order;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public int getFood() {
        return food;
    }
    public void setFood(int food) {
        this.food = food;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }
    
}
