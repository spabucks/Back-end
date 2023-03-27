package sphabucks.domain.payments.gifticons.service;

import sphabucks.domain.payments.gifticons.model.GiftIconList;
import sphabucks.domain.payments.gifticons.vo.RequestGiftIconList;

import java.util.List;

public interface IGiftIconListService {
    void addGiftIconList(RequestGiftIconList requestGiftIconList);
    List<GiftIconList> getGiftIconList(Long id);
}