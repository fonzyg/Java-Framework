# Create standard Maven directories
New-Item -ItemType Directory -Force -Path src\main\java\com\example\inventory | Out-Null
New-Item -ItemType Directory -Force -Path src\main\resources\templates | Out-Null

# Create the Spring Boot application class
@'
package com.example.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }
}
'@ | Out-File -Encoding UTF8 src\main\java\com\example\inventory\InventoryApplication.java

# Create a minimal mainscreen controller (temporary) so /mainscreen returns something
@'
package com.example.inventory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainscreenController {
    @GetMapping("/mainscreen")
    @ResponseBody
    public String mainscreen() {
        return "App is running. Replace this with your Thymeleaf view.";
    }
}
'@ | Out-File -Encoding UTF8 src\main\java\com\example\inventory\MainscreenController.java
