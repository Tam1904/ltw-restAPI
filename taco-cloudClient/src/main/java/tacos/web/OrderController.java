package tacos.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

//import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.RestTemplate;

//import lombok.extern.slf4j.Slf4j;
//import tacos.Ingredient;
import tacos.Order;
import tacos.Taco;

//@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
	private RestTemplate rest = new RestTemplate();
	
	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", new Order());
		return "orderForm";
	}

//	@PostMapping
//	public String processOrder(Order order) {
//		log.info("Order submitted: " + order);
//		return "redirect:/";
//
//	}

	@PostMapping
	public String processOrder(@SessionAttribute("tacos")List<Taco> taco, HttpServletRequest request,Model model) {
		String name = (String ) request.getParameter("name");
		String street = (String ) request.getParameter("street");
		String city = (String ) request.getParameter("city");
		String state = (String ) request.getParameter("state");
		String zip = (String ) request.getParameter("zip");
		String ccNumber = (String ) request.getParameter("ccNumber");
		String ccExpiration = (String ) request.getParameter("ccExpiration");
		String ccCVV = (String ) request.getParameter("ccCVV");
		Order order = new Order();
		order.setName(name);
		order.setStreet(street);
		order.setZip(zip);
		order.setCity(city);
		order.setState(state);
		order.setCcNumber(ccNumber);
		order.setCcExpiration(ccExpiration);
		order.setCcCVV(ccCVV);
		order.setTacos(taco);
		rest.postForObject("http://localhost:8080/orders", order, Order.class);
		return "redirect:/";
	}

}