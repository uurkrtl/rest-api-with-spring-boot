package example.cashcard.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import example.cashcard.entities.CashCard;

public interface CashCardService {
	public ResponseEntity<CashCard> findById(Long requestedId);
	public ResponseEntity<List<CashCard>> findAll(Pageable pageable);
	public ResponseEntity<Void> createCashCard(CashCard newCashCardRequest, UriComponentsBuilder ucb);
}