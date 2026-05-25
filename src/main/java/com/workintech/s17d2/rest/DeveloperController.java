package com.workintech.s17d2.rest;
import org.springframework.http.HttpStatus;
import com.workintech.s17d2.model.*;
import com.workintech.s17d2.tax.Taxable;

import jakarta.annotation.PostConstruct;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    // Map tanımı
    public Map<Integer, Developer> developers;

    // Dependency Injection
    private final Taxable taxable;

    public DeveloperController(Taxable taxable) {
        this.taxable = taxable;
    }

    // Map initialize
    @PostConstruct
    public void init() {

        developers = new HashMap<>();

        developers.put(
                1,
                new JuniorDeveloper(1, "Ahmet", 25000)
        );

        developers.put(
                2,
                new MidDeveloper(2, "Mehmet", 40000)
        );

        developers.put(
                3,
                new SeniorDeveloper(3, "Ayşe", 70000)
        );
    }

    // GET ALL
    @GetMapping
    public List<Developer> getDevelopers() {

        return new ArrayList<>(developers.values());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Developer getDeveloperById(@PathVariable Integer id) {

        return developers.get(id);
    }

    // POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Developer addDeveloper(@RequestBody Developer developer) {

        Developer newDeveloper = null;

        double salary = developer.getSalary();

        if (developer.getExperience() == Experience.JUNIOR) {

            salary = salary -
                    (salary * taxable.getSimpleTaxRate() / 100);

            newDeveloper = new JuniorDeveloper(
                    developer.getId(),
                    developer.getName(),
                    salary
            );

        } else if (developer.getExperience() == Experience.MID) {

            salary = salary -
                    (salary * taxable.getMiddleTaxRate() / 100);

            newDeveloper = new MidDeveloper(
                    developer.getId(),
                    developer.getName(),
                    salary
            );

        } else if (developer.getExperience() == Experience.SENIOR) {

            salary = salary -
                    (salary * taxable.getUpperTaxRate() / 100);

            newDeveloper = new SeniorDeveloper(
                    developer.getId(),
                    developer.getName(),
                    salary
            );
        }

        developers.put(newDeveloper.getId(), newDeveloper);

        return newDeveloper;
    }

    // PUT
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Developer updateDeveloper(@PathVariable Integer id,
                                     @RequestBody Developer developer) {

        developers.put(id, developer);

        return developer;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public Developer deleteDeveloper(@PathVariable Integer id) {

        return developers.remove(id);
    }
}