package byfayzullayev.person.controller;
import byfayzullayev.person.entity.User;
import byfayzullayev.person.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //CREATE

    @PostMapping("/adduser")
    public String addUser(@RequestBody User user) {
        User addUser = new User();
        User byName = userRepository.findByLastname(user.getLastname());
        if (byName != null) {
            return "Bu user mavjud";
        }
        addUser.setLastname(user.getLastname());
        userRepository.save(addUser);
        return "User qo`shildi";
    }

    //READ
    @GetMapping("/userlist")
    public List<User> getUserList() {
        List<User> users = userRepository.findAll();
        return users;
    }

    //UPDATE
    @PutMapping("/edit/{id}")
    public String editUser(@PathVariable Integer id, @RequestBody User user) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User editUser = byId.get();
            User byLastName = userRepository.findByLastname(user.getLastname());

            if (byLastName != null) {
                return " Bu user nomi mavjud";
            }
            editUser.setLastname(user.getLastname());
            userRepository.save(editUser);
            return "saqlandi";
        }
        return "Qayta urunib ko`ring";
    }

    //DELETE
    @DeleteMapping("/delete")
    public String deleteUser(@PathVariable Integer id) {
        Optional<User> byid = userRepository.findById(id);
        if (byid.isPresent()) {
            userRepository.deleteById(id);
            return "O`chirildi";

        }
        return "Qaytadan urining";

    }

}
