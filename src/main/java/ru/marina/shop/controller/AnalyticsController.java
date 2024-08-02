package ru.marina.shop.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.marina.shop.service.OrderService;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AnalyticsController {
    private final OrderService orderService;

    @RequestMapping(value="/get-analytic", method=RequestMethod.GET, produces="application/json")
    public List<Object[]> getAnalytic() {
        return orderService.getStatistic();
    }
}
