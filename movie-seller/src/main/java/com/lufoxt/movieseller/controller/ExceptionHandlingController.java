package com.lufoxt.movieseller.controller;

import com.lufoxt.movieseller.exception.MovieNotFoundException;
import com.lufoxt.movieseller.exception.OrderNotFoundException;
import com.lufoxt.movieseller.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionHandlingController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND,
            reason = "No such Order")  // 404
    @ExceptionHandler(OrderNotFoundException.class)
    public void orderNotFound() {
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND,
            reason = "No such Movie")  // 404
    @ExceptionHandler(MovieNotFoundException.class)
    public void movieNotFound() {
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND,
            reason = "No such Customer")  // 404
    @ExceptionHandler(CustomerNotFoundException.class)
    public void customerNotFound() {
    }
    
}
