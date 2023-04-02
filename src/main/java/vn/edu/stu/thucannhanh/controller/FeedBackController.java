package vn.edu.stu.thucannhanh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.stu.thucannhanh.entities.Feedback;
import vn.edu.stu.thucannhanh.repository.FeedbackRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class FeedBackController {
    @Autowired
    private FeedbackRepository gFeedbackRepository;

    @GetMapping("/feedback")
    public ResponseEntity<List<Feedback>> getAllFeedback(){
        List<Feedback> lstFeedbacks = new ArrayList<>();
        gFeedbackRepository.findAll().forEach(lstFeedbacks::add);
        return new ResponseEntity<>(lstFeedbacks, HttpStatus.OK);
    }
    @GetMapping("/feedback/{id}")
    public ResponseEntity<Feedback> getFeedbackByID(@PathVariable("id") Integer id){
        Optional<Feedback> feedback = gFeedbackRepository.findById(id);
        return new ResponseEntity<>(feedback.get(), HttpStatus.OK);
    }
    @GetMapping("/feedback/customer/{id}")
    public ResponseEntity<List<Feedback>> getFeedbackByCustomerId(@PathVariable("id") Integer id){
        List<Feedback> lstFeedbacks = new ArrayList<>();
        gFeedbackRepository.findFeedbackByCustomer(id).forEach(lstFeedbacks::add);
        return new ResponseEntity<>(lstFeedbacks, HttpStatus.OK);
    }
    @GetMapping("/feedback/food/{id}")
    public ResponseEntity<List<Feedback>> getFeedbackByFoodId(@PathVariable("id") Integer id){
        List<Feedback> lstFeedbacks = new ArrayList<>();
        gFeedbackRepository.findFeedbackByFoodId(id).forEach(lstFeedbacks::add);
        return new ResponseEntity<>(lstFeedbacks, HttpStatus.OK);
    }
    @PostMapping("/feedback")
    public ResponseEntity<?> createFeedback(@RequestBody Feedback feedback){
        try {
            Feedback newFeedback = new Feedback();
            newFeedback.setCustomer(feedback.getCustomer());
            newFeedback.setFood(feedback.getFood());
            newFeedback.setDescription(feedback.getDescription());
            newFeedback.setRating(feedback.getRating());
            newFeedback.setCreateDate(new Date());
            Feedback save = gFeedbackRepository.save(newFeedback);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Create specified Feedback: " + e.getCause().getCause().getMessage());
        }
    }
}
