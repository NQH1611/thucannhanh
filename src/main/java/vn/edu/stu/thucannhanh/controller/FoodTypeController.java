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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.stu.thucannhanh.entities.FoodType;
import vn.edu.stu.thucannhanh.repository.FoodTypeRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class FoodTypeController {
    @Autowired
    private FoodTypeRepository gFoodTypeRepository;
    @GetMapping("/foodtype")
    public ResponseEntity<List<FoodType>> getAllFood(){
        List<FoodType> lstFood = new ArrayList<>();
        gFoodTypeRepository.findAll().forEach(lstFood::add);
        return new ResponseEntity<>(lstFood, HttpStatus.OK);
    }
    @GetMapping("/foodtype/{id}")
    public ResponseEntity<FoodType> getFoodByID(@PathVariable("id") int id){
        Optional<FoodType> food = gFoodTypeRepository.findById(id);
        return new ResponseEntity<>(food.get(), HttpStatus.OK);
    }

    @PostMapping("/foodtype")
    public ResponseEntity<?> createFood(@RequestBody FoodType foodType) {
        try {
            FoodType newFoodType = new FoodType();
            newFoodType.setName(foodType.getName());
            newFoodType.setDescription(foodType.getDescription());
            FoodType save = gFoodTypeRepository.save(newFoodType);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Create specified FoodType: " + e.getCause().getCause().getMessage());
        }
    }
    @PutMapping("/foodtype/{id}")
    public ResponseEntity<?> updateFood(@RequestBody FoodType foodType, @PathVariable("id") int id){
        try {
            Optional<FoodType> result = gFoodTypeRepository.findById(id);
            if(result.isPresent()){
                FoodType newFoodType = result.get();
                newFoodType.setName(foodType.getName());
                newFoodType.setDescription(foodType.getDescription());
                FoodType save = gFoodTypeRepository.save(newFoodType);
                return new ResponseEntity<>(save, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Update specified Food: " + e.getCause().getCause().getMessage());
        }
    }
}
