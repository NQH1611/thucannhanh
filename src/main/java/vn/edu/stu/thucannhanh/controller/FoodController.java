package vn.edu.stu.thucannhanh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.stu.thucannhanh.entities.Food;
import vn.edu.stu.thucannhanh.repository.FoodRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(maxAge = 3600)
public class FoodController {
    @Autowired
    private FoodRepository gFoodRepository;

    @GetMapping("/")
    public String hello(){
        return "Welcome To Fast Food";
    }
    @GetMapping("/food")
    public ResponseEntity<List<Food>> getAllFood(){
        List<Food> lstFood = new ArrayList<>();
        gFoodRepository.findAllStatus1().forEach(lstFood::add);
        return new ResponseEntity<>(lstFood, HttpStatus.OK);
    }
    @GetMapping("/food/{id}")
    public ResponseEntity<Food> getFoodByID(@PathVariable("id") int id){
        Optional<Food> food = gFoodRepository.findById(id);
        return new ResponseEntity<>(food.get(), HttpStatus.OK);
    }

    @PostMapping("/food")
    public ResponseEntity<?> createFood(@RequestBody Food food) {
        try {
            Food newFood = new Food();
            newFood.setName(food.getName());
            newFood.setImage(food.getImage());
            newFood.setIngredient(food.getIngredient());
            newFood.setPrice(food.getPrice());
            newFood.setStatus(1);
            newFood.setFoodType(food.getFoodType());
            Food save = gFoodRepository.save(newFood);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Create specified Food: " + e.getCause().getCause().getMessage());
        }
    }
    @PutMapping("/food/{id}")
    public ResponseEntity<?> updateFood(@RequestBody Food food, @PathVariable("id") int id){
        try {
            Optional<Food> result = gFoodRepository.findById(id);
            if(result.isPresent()){
                Food newFood = result.get();
                newFood.setName(food.getName());
                newFood.setImage(food.getImage());
                newFood.setIngredient(food.getIngredient());
                newFood.setPrice(food.getPrice());
                Food save = gFoodRepository.save(newFood);
                return new ResponseEntity<>(save, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Update specified Food: " + e.getCause().getCause().getMessage());
        }
    }
    @PutMapping("/food/delete/{id}")
    public ResponseEntity<?> deleteFood( @PathVariable("id") int id){
        Optional<Food> food = gFoodRepository.findById(id);
        if(food.isPresent()){
            Food newFood = food.get();
            newFood.setStatus(0);
            Food save = gFoodRepository.save(newFood);
            return new ResponseEntity<>(save, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    
}
