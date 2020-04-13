package br.com.meetups.funcional.resource;

import br.com.meetups.funcional.model.User;
import br.com.meetups.funcional.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@RequiredArgsConstructor
public class UserResource {

    private final UserRepository userRepository;

    @Bean
    RouterFunction<ServerResponse> getAllUsersRoute() {
        return RouterFunctions.route(
                GET("/users"),
                request -> ServerResponse.ok().body(BodyInserters.fromValue(userRepository.findAllUsers()))
        );
    }

    @Bean
    RouterFunction<ServerResponse> getUserById() {
        return RouterFunctions.route(
                GET("/users/{id}"),
                request -> ServerResponse.ok().body(userRepository.findUser(request.pathVariable("id")), User.class)
        );
    }

}
