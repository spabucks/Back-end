package sphabucks.payments.cards.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sphabucks.payments.cards.model.Card;
import sphabucks.payments.cards.repository.ICardRepo;
import sphabucks.payments.cards.vo.ResponseCard;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements ICardService{

    private final ICardRepo iCardRepo;
    @Override
    public void addCard(Card card) {
        iCardRepo.save(card);
    }

    @Override
    public ResponseCard getCard(Long id) {
        Card card = iCardRepo.findById(id).get();

        return ResponseCard.builder()
                .name(card.getName())
                .image(card.getImage())
                .money(card.getMoney())
                .build();
    }
}
