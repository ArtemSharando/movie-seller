package com.lufoxt.movieseller.service.impl;

import com.lufoxt.movieseller.entity.Customer;
import com.lufoxt.movieseller.entity.Movie;
import com.lufoxt.movieseller.entity.Order;
import com.lufoxt.movieseller.entity.dto.OrderDto;
import com.lufoxt.movieseller.exception.MovieNotFoundException;
import com.lufoxt.movieseller.exception.CustomerNotFoundException;
import com.lufoxt.movieseller.repository.MovieRepository;
import com.lufoxt.movieseller.repository.OrderRepository;
import com.lufoxt.movieseller.repository.CustomerRepository;
import com.lufoxt.movieseller.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

import static com.lufoxt.movieseller.util.config.MovieConfig.MOVIE_ANALYZER_URL;
import static com.lufoxt.movieseller.util.converter.OrderConverter.convertOrderDtoToOrderEntity;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final MovieRepository movieRepository;
    private final RestTemplate restTemplate;


    @Override
    @Transactional
    public void createOrder(OrderDto orderDto) {
        Long customerId = orderDto.getCustomerId();
        Long purchasedMovie = orderDto.getPurchasedMovie();

        log.info("User with id {} wants to buy movie {}", customerId, purchasedMovie);

        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        Movie movie = movieRepository.findById(purchasedMovie).orElseThrow(MovieNotFoundException::new);

        Boolean isAvailableMovieForBuying = restTemplate.getForEntity(
                        MOVIE_ANALYZER_URL + "/{movieId}?customerId={customerId}",
                        Boolean.class,
                        movie.getId(), customer.getId())
                .getBody();

        if (Boolean.TRUE.equals(isAvailableMovieForBuying)) {
            Order order = convertOrderDtoToOrderEntity(orderDto, movie, customer);
            orderRepository.save(order);

            customer.getPurchasedMovies().add(movie);
            customerRepository.save(customer);

            log.info("User {} successfully bought the movie: {}", customer.getUsername(), movie.getTitle());
        }
    }
}
