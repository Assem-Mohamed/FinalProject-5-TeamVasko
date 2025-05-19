package Scalable.Clients;

import org.example.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/by-username/{username}")
    Optional<UserDTO> getUserByUsername(@PathVariable("username") String username);
}
