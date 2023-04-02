package vn.edu.stu.thucannhanh.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "thuc_an")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ten_thuc_an")
    private String name;
    @Column(name = "hinh_anh")
    private String image;
    @Column(name = "thanh_phan")
    private String ingredient;
    @Column(name = "gia")
    private BigDecimal price;
    @Column(name = "trang_thai")
    private int status;
    @Column(name = "ma_loai_thuc_an")
    private int foodType;
    
    public Food() {
    }

    public Food(Integer id, String name, String image, String ingredient, BigDecimal price, int status, int foodType) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.ingredient = ingredient;
        this.price = price;
        this.status = status;
        this.foodType = foodType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public int getFoodType() {
        return foodType;
    }



    public void setFoodType(int foodType) {
        this.foodType = foodType;
    }
    

}
