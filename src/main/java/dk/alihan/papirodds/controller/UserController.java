package dk.alihan.papirodds.controller;

import dk.alihan.papirodds.models.UserOddsDTO;
import dk.alihan.papirodds.entity.User;
import dk.alihan.papirodds.request.UserValidationRequest;
import dk.alihan.papirodds.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<User> validateUser(@RequestBody UserValidationRequest request) {

        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userService.validateCredentials(request));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("/odds/{id}")
    @ResponseBody
    public ResponseEntity<List<UserOddsDTO>> getOddsByUserId(@PathVariable Long id) {

        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userService.getOddsByUserId(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}