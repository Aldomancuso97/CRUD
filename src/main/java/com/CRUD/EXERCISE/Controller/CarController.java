package com.CRUD.EXERCISE.Controller;

import com.CRUD.EXERCISE.Entities.Car;
import com.CRUD.EXERCISE.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarRepository carRepository;

    List<Car> carList = new ArrayList<>();




    @PostMapping("/create")
    public ResponseEntity createCar(@RequestBody Car car) {
       Car carNew = carRepository.save(car);
       carList.add(car);
       return ResponseEntity.status(HttpStatus.CREATED).body("car has been created");

    }

    @GetMapping("/list")
    public List<Car> getCars() {
        carList = carRepository.findAll();
        return carList;

    }

    @GetMapping("/getCar/{id}")
    public Object getCarById(@PathVariable long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            return carOptional;
        } return ResponseEntity.status(HttpStatus.NOT_FOUND).body("car not found");


    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCar(@PathVariable long id,@RequestParam String type) {
        Car updateCar = carRepository.findById(id).orElseThrow();
        updateCar.setType(type);
        carRepository.save(updateCar);
        return ResponseEntity.status(HttpStatus.OK).body("car update");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCarById(@PathVariable long id){
        Optional<Car> optionalCar = carRepository.findById(id);
        if(optionalCar.isPresent()){
            carRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("car has been eliminated");
        }
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("car not found");
    }


    @DeleteMapping("/delete/all")
    public ResponseEntity deleteAllCars(){
        carRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("all cars has been eliminated");
    }

}




