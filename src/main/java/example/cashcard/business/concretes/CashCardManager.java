package example.cashcard.business.concretes;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import example.cashcard.business.abstracts.CashCardService;
import example.cashcard.dataAccess.CashCardRepository;
import example.cashcard.entities.CashCard;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CashCardManager implements CashCardService {
	private CashCardRepository cashCardRepository;

	@Override
	public ResponseEntity<CashCard> findById(Long requestedId) {
		Optional<CashCard> cashCardOptional = cashCardRepository.findById(requestedId);
		if (cashCardOptional.isPresent()) {
			return ResponseEntity.ok(cashCardOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<List<CashCard>> findAll(Pageable pageable) {
		Page<CashCard> page  = cashCardRepository.findAll(
				PageRequest.of(
						pageable.getPageNumber(),
						pageable.getPageSize(),
						pageable.getSortOr(Sort.by(Sort.Direction.ASC,"amount"))
						));
		return ResponseEntity.ok(page.getContent());
	}

	@Override
	public ResponseEntity<Void> createCashCard(CashCard newCashCardRequest, UriComponentsBuilder ucb) {
		CashCard savedCashCard = cashCardRepository.save(newCashCardRequest);
		URI locationofNewCashCard = ucb
				.path("cashcards/{id}")
				.buildAndExpand(savedCashCard.getId())
				.toUri();
		return ResponseEntity.created(locationofNewCashCard).build();
	}
}