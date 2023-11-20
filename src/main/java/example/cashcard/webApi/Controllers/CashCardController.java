package example.cashcard.webApi.Controllers;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import example.cashcard.business.abstracts.CashCardService;
import example.cashcard.entities.CashCard;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cashcards")
@AllArgsConstructor
public class CashCardController {
	private CashCardService cashCardService;
	
	@GetMapping("/{requestedId}")
	public ResponseEntity<CashCard> findById(@PathVariable Long requestedId){
		return this.cashCardService.findById(requestedId);
	}
	
	@PostMapping
	public ResponseEntity<Void> createCashCard(@RequestBody CashCard newCashCardRequest, UriComponentsBuilder ucb){
		return this.cashCardService.createCashCard(newCashCardRequest, ucb);
	}
	
	@GetMapping
	public ResponseEntity<List<CashCard>> findAll(Pageable pageable){
		return this.cashCardService.findAll(pageable);
	}
}